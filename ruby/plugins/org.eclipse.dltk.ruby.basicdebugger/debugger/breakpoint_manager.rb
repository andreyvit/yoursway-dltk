###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'thread'

require 'dbgp/logger'

module XoredDebugger

    class Breakpoint
		include Logger
	
        def initialize(id, info)
            @id = id
            @info = info
            @hit_count = 0
        end

        attr_reader :id
        attr_reader :info
        attr_reader :hit_count

        def state
            @info.state
        end

        def temporary
            @info.temporary
        end

        def expression
            @info.expression
        end

        def hit_value
            @info.hit_value
        end

        def hit_condition
            @info.hit_condition
        end

        def update(info)
            @info.update(info)
        end

        def hit(stack)
            @hit_count += 1
			
			# State
            if not state
                return false # Breakpoint disabled
            end
			
            # Expression
            unless expression.nil?
                begin
                    if not stack.eval(expression.to_s)
                        return false # Expression evaluated to false
                    end
                rescue Exception
                    return false # Expression can not be evaluated
                end
            end

            # Hit condition
            case hit_condition
                when BreakpointInfo::COND_GREATER_OR_EQUALS
                    @hit_count >= hit_value
                when BreakpointInfo::COND_EQUALS
                    @hit_count == hit_value
                when BreakpointInfo::COND_MULTIPLE
                    (@hit_count % hit_value) == 0
                else
                    false
            end
        end
        protected :hit
    end # class Breakpoint

    class LineBreakpoint < Breakpoint
        def initialize(id, info)
            super(id, info)
        end

        def file
            info.file
        end

        def line
            info.line
        end

        def hit(s, f, l)
            file == f and line == l and super(s)
        end
    end # class LineBreakpoint

    class ExceptionBreakpoint < Breakpoint
        def initialize(id, info)
            super(id, info)
        end

        def exception
            info.exception
        end

        def hit(s, e)
            exception == e and super(s)
        end
    end # class ExceptionBreakpoint

    class BreakpointManager
        @@id = 0

		class << self
			def next_id
				@@id += 1
			end
		end

        def initialize
            @mutex = Mutex.new

            @line_bps = {}
            @exception_bps = {}
        end

        # Interface methods
        def add(info)
            @mutex.synchronize do
                id = BreakpointManager.next_id

                # Add breakpoint to correct list
                type = info.class
                if info.class == LineBreakpointInfo
                    @line_bps[id] = LineBreakpoint.new(id, info)
                elsif info.class == ExceptionBreakpointInfo
                    # TODO:
                end

                id
            end
        end

        def update(id, info)
            @mutex.synchronize do
                old_info = self[id]
                unless old_info.nil?
                    old_info.update(info)
                end
            end
        end

        def remove(id)
            @mutex.synchronize do
                (not @line_bps.delete(id).nil?) or
                (not @exception_bps.delete(id).nil?)
            end
        end

        def [] (id)
            # TODO: handle all breakpoint types
            bp = @line_bps[id]

            info = bp.info
            info.breakpoint_id = bp.id
            info.hit_count = bp.hit_count
            info
        end

        # Manager specific methods
        def line_break?(stack, file, line)
            @mutex.synchronize do
                result = false
                for bp in @line_bps.values do
                    if bp.hit(stack, file, line)
                        result = true
                    end
                end
                result
            end
        end

        def exception_break?(stack, exception)
            @mutex.synchronize do
                # TODO:
                false
            end
        end
    end # class BreakpointManager
end # module XoredDebugger

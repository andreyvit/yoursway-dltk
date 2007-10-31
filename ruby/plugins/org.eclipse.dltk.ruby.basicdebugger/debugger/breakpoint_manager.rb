###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'monitor'

require 'dbgp/logger'
require 'dbgp/managers/breakpoint'


module XoredDebugger

    class BreakpointInfo	
        def hit(stack)
            if (@hit_count.nil?)
                @hit_count = 0
            end
            @hit_count = @hit_count + 1
			
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

    class LineBreakpointInfo < BreakpointInfo
        def hit(s, f, l)
            file == f and line == l and super(s)
        end
    end # class LineBreakpoint

    class ExceptionBreakpointInfo < BreakpointInfo
        def hit(s, e)
            exception == e and super(s)
        end
    end # class ExceptionBreakpoint

    class BreakpointManager < AbstractBreakpointManager
        @@id = 0
		def BreakpointManager.next_id
		    @@id += 1
		end

        def initialize
            @monitor = Monitor.new

            @line_bps = {}
            @exception_bps = {}
        end

        # Interface methods
        def add(info)
            @monitor.synchronize do
                id = BreakpointManager.next_id
                info.breakpoint_id = id

                # Add breakpoint to correct list
                type = info.class
                if info.class == LineBreakpointInfo
                    @line_bps[id] = info
                elsif info.class == ExceptionBreakpointInfo
                    @exception_bps[id] = info
                else
                    raise NotImplementedError('Support for this type of exceptions is not implemented')
                end

                id
            end
        end

        def update(id, info)
            @monitor.synchronize do
                old_info = self[id]
                unless old_info.nil?
                    old_info.update(info)
                end
            end
        end

        def remove(id)
            @monitor.synchronize do
                (not @line_bps.delete(id).nil?) or
                (not @exception_bps.delete(id).nil?)
            end
        end

        def [] (id)
            # TODO: handle all breakpoint types
            bp = @line_bps[id]
            bp.clone
        end

        # Manager specific methods
        def get_line_break(stack, file, line)
            @monitor.synchronize do
                result = nil
                for bp in @line_bps.values do
                    if bp.hit(stack, file, line)
                        result = bp
                    end
                end
                result
            end
        end

        def exception_break?(stack, exception)
            @monitor.synchronize do
                # TODO:
                false
            end
        end
    end # class BreakpointManager
end # module XoredDebugger

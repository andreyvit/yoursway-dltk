###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'thread'

module XoredDebugger

    class Breakpoint
        COND_GREATER_OR_EQUALS = '>='
        COND_EQUALS            = '=='   
        COND_MULTIPLE          = '%'
    
        def initialize(id, state = true, temporary = false, expression = nil, hit_value = 1, hit_condition = COND_GREATER_OR_EQUALS)
            @id = id                       # Unique identifier

            @state = state                 # Enabled or disabled

            @temporary = temporary         # Breakpoint should be removed after first hit
			
			@expression = expression

            @hit_count = 0                 # Number of breakpoint hits
            @hit_value = hit_value         # Special value used with hit_condition
            @hit_condition = hit_condition # The relation between hit_value and hit_count
        end

        attr_reader :id
        attr_reader :state
        attr_writer :state
        attr_reader :hit_count, :hit_value, :hit_condition
		
		def update(state, temporary, expression, hit_value, hit_condition)
			@state = state
            @temporary = temporary
			@expression = expression
            @hit_value = hit_value
            @hit_condition = hit_condition
		end
		        
        def hit(stack)
            @hit_count += 1
						
			# Expression
			unless @expression.nil? 
				begin 
					if not stack.eval(expression)
						return false # Expression evaluated to false
					end
				rescue Exception
					return false # Expression can not be evaluated
				end
			end
			
			# State
			if not @state
				return false # Breakpoint disabled
			end

			# Hit condition
            case @hit_condition
                when COND_GREATER_OR_EQUALS
                    @hit_count >= @hit_value
				when COND_EQUALS
					@hit_count == @hit_value
				when COND_MULTIPLE
                    (@hit_count % @hit_value) == 0
                else
                    false
            end
        end

        def exists
            @temporary ? @hit_count == 0 : true
        end

        protected :hit
    end # class Breakpoint
    
    #
    # N.B. Stores absolute path names
    #
    class LineBreakpoint < Breakpoint
      	def initialize(id, file, line, state = true, temporary = false, expression = nil, hit_value = 1, hit_condition = COND_GREATER_OR_EQUALS)
			super(id, state, temporary, expression, hit_value, hit_condition)
			@file = file
            @line = line
		end
		
		attr_reader :line, :file

        def hit(stack, file, line)
            @file == file and @line == line and super(stack)
        end

        attr_reader :file, :line
    end # class LineBreakpoint

    class ExceptionBreakpoint < Breakpoint
        def initialize(id, exception, state = true, temporary = false)
            super(id, state, temporary)
            @exception = exception
        end

        def hit(stack, exception)
            @exception == exception and super(stack)
        end
    end # class ExceptionBreakpoint
                   

    class Breakpoints
    private        
        @@id = 0

        def Breakpoints.next_id
            @@id += 1
        end

    public 
        def initialize
            @mutex = Mutex.new

            @line_bps = {}
            @exception_bps = {}
        end
		
		def [] (id)
			 @line_bps[id]
		end

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
            end
        end

        def set_line_bpt(file, line, state, temporary, expression, hit_value, hit_condition)
            @mutex.synchronize do
                id = Breakpoints.next_id
                @line_bps[id] = LineBreakpoint.new(id, file, line, state, temporary, expression, hit_value, hit_condition)
                id
            end
        end

        def set_exception_bpt(exception, state)
            @mutex.synchronize do
                id = Breakpoints.next_id
                # TODO:
                id
            end
        end
               
		def update(id, state, temporary, expression, hit_value, hit_condition)
			@mutex.synchronize do
				@line_bps[id].update(state, temporary, expression, hit_value, hit_condition)
			end
		end

        def remove(id)
            @mutex.synchronize do
                (not @line_bps.delete(id).nil?) or (not @exception_bps.delete(id).nil?)
            end
        end
    end # class Breakpoints

end # module XoredDebugger

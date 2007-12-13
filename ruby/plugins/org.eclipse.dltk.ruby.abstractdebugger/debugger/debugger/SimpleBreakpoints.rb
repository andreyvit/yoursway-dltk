
module XoredDebugger    
	class Breakpoint
	    include BreakpointContract
	    attr_reader :breakpoint_id
	    attr_reader :temporary            
	    attr_accessor :state                    
	    attr_accessor :expression            
	    attr_accessor :hit_value
	    attr_accessor :hit_condition
	    attr_reader :hit_count
	    
	    def initialize(id, temporary)
	        @breakpoint_id = id
	        @temporary = temporary
	        @state = true                               
	        @expression = nil
	        @hit_value = 0
	        @hit_condition = '>='
	        @hit_count = 0            
	    end
	    
	    def hit(context)
	        if (@hit_count.nil?)
	            @hit_count = 0
	        end
	        @hit_count = @hit_count + 1
	        
	        # State
	        if not state
	            return false
	        end
	        
	        # Expression
	        unless expression.nil?
	            begin
	                if not context.eval(expression.to_s)
	                    return false # Expression evaluated to false
	                end
	            rescue Exception
	                return false # Expression can not be evaluated
	            end
	        end
	
	        # Hit condition
	        case hit_condition
	            when BreakpointContract::COND_GREATER_OR_EQUALS
	                return @hit_count >= hit_value
	            when BreakpointContract::COND_EQUALS
	                return @hit_count == hit_value
	            when BreakpointContract::COND_MULTIPLE
	                return (@hit_count % hit_value) == 0
	            else
	                return false
	        end            
	    end        
	end
	
	class ExceptionBreakpoint < Breakpoint
	    include ExceptionBreakpointContract
	    attr_reader :exception
	    
	    def initialize(id, exception, temporary)
	        super(id, temporary)
	        @exception = exception
	    end
	    
	    def hit(context, expt)
	        expt.is_a? exception and super(context)
	    end        
	end
end

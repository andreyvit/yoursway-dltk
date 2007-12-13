require 'debugger/AbstractBreakpointManager'

module XoredDebugger
    class MockLineBreakpoint
        include LineBreakpointContract
        attr_reader :breakpoint_id
        attr_reader :filename            
        attr_accessor :lineno
        attr_reader :temporary            
        attr_accessor :state                    
        attr_accessor :expression            
        attr_accessor :hit_value
        attr_accessor :hit_condition
        attr_reader :hit_count
        
        def initialize(id, file, line, temporary)
            @breakpoint_id = id
            @filename = file
            @lineno = line
            @temporary = temporary
            @state = true                    
            @expression = nil            
            @hit_value = 0
            @hit_condition = '>='
            @hit_count = 0            
        end
    end
    
	class MockBreakpointManager < AbstractBreakpointManager
	    def clear()
	       @breakpoints.clear 
	    end
        
    protected
        def add_line_breakpoint_impl(id, file, line, temporary = false)
            MockLineBreakpoint.new(id, file, line, temporary)
        end
    
        def remove_line_breakpoint_impl(bp)
            # Nothing to do
        end       
	end
end

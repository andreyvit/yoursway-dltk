require 'debugger/AbstractDebugger'
require 'test/MockContext'
require 'test/MockBreakpointManager'

module XoredDebugger    
    class MockDebugger < AbstractDebugger
                
        def initialize
            super
        end      
                   
        def create_context_impl(thread)            
            return MockContext.new()
        end
        
        def terminate
            super
            Thread.list.each do |thread|
                thread[ :mock_debug_thread ] = nil
            end                 
        end
        
        def create_breakpoint_manager
            MockBreakpointManager.new
        end     
        
        def create_debug_thread(*args, &block)
            t = Thread.new(*args, &block)
            t[ :mock_debug_thread ] = true
            t
        end     
        
        def is_debug_thread?(thread)
            thread[ :mock_debug_thread ] == true
        end     
	end
end

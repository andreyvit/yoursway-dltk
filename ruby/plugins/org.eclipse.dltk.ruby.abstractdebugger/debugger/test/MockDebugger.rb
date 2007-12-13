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
        
        def create_breakpoint_manager
            MockBreakpointManager.new
        end     
        
        def create_debug_thread(*args, &block)
            Thread.new(*args, &block)
        end     
        
        def is_debug_thread?(thread)
            false
        end     
	end
end

require 'debugger/AbstractDebugger'
require 'test/MockContext'
require 'test/MockBreakpointManager'

module XoredDebugger    
    class MockDebugger < AbstractDebugger
                
        def initialize
            super
            @monitor = Monitor.new
        end      
                   
        def create_context_impl(thread)            
            return MockContext.new()
        end
        
        def terminate
            super
            @monitor.synchronize {
                Thread.list.each do |thread|
                    thread[ :mock_debug_thread ] = nil
                end
            }                
        end
        
        def create_breakpoint_manager
            MockBreakpointManager.new
        end     
        
        def create_debug_thread(*args, &block)
            @monitor.synchronize {
                thread = Thread.new(*args, &block)
                thread[ :mock_debug_thread ] = true
                thread
            }
        end     
        
        def is_debug_thread?(thread)
            @monitor.synchronize {
                thread[ :mock_debug_thread ] == true
            }
        end     
	end
end

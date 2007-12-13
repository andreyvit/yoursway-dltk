require 'test/unit'
require 'basic/BasicDebugger'

module XoredDebugger    
    class BasicDebuggerTest < Test::Unit::TestCase
        include DebugEventHandler         

        def test_catch
            @break_called = false
            @line_called = false
            @catch_called = false
            debugger = BasicDebugger.new            
            debugger.handler = self
            debugger.start
            bp_manager = debugger.breakpoint_manager
            bp_manager.add_exception_breakpoint('RuntimeError', true)
            begin
                raise RuntimeError, 'Shoud be catched'
            rescue RuntimeError
            end
            debugger.terminate
            assert(!@break_called)
            assert(@line_called)
            assert(@catch_called)
        end   
        
        def test_not_catch
            @break_called = false
            @line_called = false
            @catch_called = false
            debugger = BasicDebugger.new            
            debugger.handler = self
            debugger.start
            bp_manager = debugger.breakpoint_manager
            bp_manager.add_exception_breakpoint('ScriptError', true)
            begin
                raise RuntimeError, 'Shoud not be catched'
            rescue RuntimeError
            end
            debugger.terminate
            assert(!@break_called)
            assert(!@line_called)
            assert(!@catch_called)
        end  
                
        def test_line
            @break_called = false
            @line_called = false
            @catch_called = false
            debugger = BasicDebugger.new            
            debugger.handler = self
            debugger.start
            bp_manager = debugger.breakpoint_manager
            bp_manager.add_line_breakpoint(__FILE__, __LINE__ + 1, true)
            a = 0 # At this line breakpoint handler will be called
            debugger.terminate
            assert(@break_called)
            assert(@line_called)
            assert(!@catch_called)
        end  
        
                
        def at_breakpoint(context)
            @break_called = true
        end
 
        def at_catchpoint(context, excpt)
            @catch_called = true
        end
 
        def at_line(context, file, line)
            @line_called = true
        end        	    	                 
    end
end

require 'test/unit'
require 'fast/FastBreakpointManager'
require 'test/MockContext'
require 'ruby-debug-base'

class MockRuntimeError < RuntimeError
end

module XoredDebugger    
    class FastBreakpointManagerTest < Test::Unit::TestCase
        def setup()
            Debugger.start
            @breakpoint_manager = FastBreakpointManager.new                        
        end

        def teardown()
            Debugger.stop
        end
        
        def test_line_breakpoint
            assert_equal(true,  Debugger.breakpoints.empty?)
            @breakpoint_manager.add_line_breakpoint('test.rb', 100, true)
            assert_equal(false,  Debugger.breakpoints.empty?)
            assert_equal(false, @breakpoint_manager.check_line_breakpoint(41234))
            assert_equal(false,  Debugger.breakpoints.empty?)
            assert_equal(true,  @breakpoint_manager.check_line_breakpoint(Debugger.breakpoints[0].id))
            assert_equal(true,  Debugger.breakpoints.empty?)

            bp = @breakpoint_manager.add_line_breakpoint('test.rb', 100, false)
            assert_equal(true,  @breakpoint_manager.check_line_breakpoint(Debugger.breakpoints[0].id))
            assert_equal(true,  @breakpoint_manager.check_line_breakpoint(Debugger.breakpoints[0].id))
            @breakpoint_manager.remove_breakpoint(bp.breakpoint_id)            
            assert_equal(true,  Debugger.breakpoints.empty?)
        end
        
        def test_exception_breakpoint
            context = MockContext.new
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)
            @breakpoint_manager.add_exception_breakpoint('RuntimeError', true)
            assert_equal(false, @breakpoint_manager.breakpoints.empty?)
            assert_equal(false, @breakpoint_manager.check_exception_breakpoint(Exception.new, context))
            assert_equal(false, @breakpoint_manager.breakpoints.empty?)
            assert_equal(true, @breakpoint_manager.check_exception_breakpoint(RuntimeError.new, context))
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)

            bp = @breakpoint_manager.add_exception_breakpoint('RuntimeError', false)
            assert_equal(true, @breakpoint_manager.check_exception_breakpoint(RuntimeError.new, context))
            assert_equal(true, @breakpoint_manager.check_exception_breakpoint(MockRuntimeError.new, context))
            @breakpoint_manager.remove_breakpoint(bp.breakpoint_id)            
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)
        end        
	end
end

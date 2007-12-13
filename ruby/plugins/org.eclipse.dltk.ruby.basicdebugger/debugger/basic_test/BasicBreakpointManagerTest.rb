require 'test/unit'
require 'basic/BasicBreakpointManager'
require 'test/MockContext'

class MockRuntimeError < RuntimeError
end

module XoredDebugger    
    class BasicBreakpointManagerTest < Test::Unit::TestCase
        def setup()
            @breakpoint_manager = BasicBreakpointManager.new
            @context = MockContext.new                                    
        end

        def teardown()
        end
        
        def test_line_breakpoint
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)
            @breakpoint_manager.add_line_breakpoint('test.rb', 100, true)
            assert_equal(false,  @breakpoint_manager .breakpoints.empty?)
            assert_equal(false, @breakpoint_manager.check_line_breakpoint(__FILE__, __LINE__, @context))
            assert_equal(false,  @breakpoint_manager.breakpoints.empty?)
            assert_equal(true,  @breakpoint_manager.check_line_breakpoint('test.rb', 100, @context))
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)

            bp = @breakpoint_manager.add_line_breakpoint('test.rb', 100, false)
            assert_equal(true,  @breakpoint_manager.check_line_breakpoint('test.rb', 100, @context))
            assert_equal(true,  @breakpoint_manager.check_line_breakpoint('test.rb', 100, @context))
            @breakpoint_manager.remove_breakpoint(bp.breakpoint_id)            
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)

            bp = @breakpoint_manager.add_line_breakpoint(__FILE__, __LINE__ + 1, false)
            assert_equal(true,  @breakpoint_manager.check_line_breakpoint(__FILE__, __LINE__, @context))
            @breakpoint_manager.remove_breakpoint(bp.breakpoint_id)            
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)
        end
        
        def test_exception_breakpoint
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)
            @breakpoint_manager.add_exception_breakpoint('RuntimeError', true)
            assert_equal(false, @breakpoint_manager.breakpoints.empty?)
            assert_equal(false, @breakpoint_manager.check_exception_breakpoint(Exception.new, @context))
            assert_equal(false, @breakpoint_manager.breakpoints.empty?)
            assert_equal(true, @breakpoint_manager.check_exception_breakpoint(RuntimeError.new, @context))
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)

            bp = @breakpoint_manager.add_exception_breakpoint('RuntimeError', false)
            assert_equal(true, @breakpoint_manager.check_exception_breakpoint(RuntimeError.new, @context))
            assert_equal(true, @breakpoint_manager.check_exception_breakpoint(MockRuntimeError.new, @context))
            @breakpoint_manager.remove_breakpoint(bp.breakpoint_id)            
            assert_equal(true,  @breakpoint_manager.breakpoints.empty?)
        end        
	end
end

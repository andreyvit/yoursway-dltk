require 'test/unit'
require 'dbgp/ThreadEventHandler.rb'

module XoredDebugger    
    class ThreadEventHandlerTest < Test::Unit::TestCase
        include ThreadEventHandler
        
        def setup
           Thread.set_event_handler(self) 
        end
        
        def teardown
            Thread.set_event_handler(nil)
        end
        
        def test_events
            thread = Thread.new {}
            thread.join
            assert_equal(@last_started, thread)
            assert_equal(@last_exited, thread)
            assert_equal(@last_excpt, nil)

            excpt = nil
            thread = Thread.new {
                raise Exception
            }
            begin 
                thread.join
            rescue Exception
                excpt = $!
            end
            
            assert_equal(@last_started, thread)
            assert_equal(@last_exited, thread)
            assert_not_equal(@last_excpt, nil)
                        
            thread = Thread.new(1,2,3) {
               |*args| @args = args
            }            
            assert_equal([1,2,3], @args)
            assert_equal(@last_started, thread)
            assert_equal(@last_exited, thread)
            assert_equal(@last_excpt, nil)
        end
	            
        def started()
            @last_started = Thread.current
	    end
        
        def exited(excpt)
            @last_exited = Thread.current
            @last_excpt = excpt
        end	          
	end
end

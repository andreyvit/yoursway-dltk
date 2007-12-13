require 'test/unit'
require 'dbgp/CaptureManager'
require 'dbgp/ThreadManager'
require 'dbgp/StreamPacket'
require 'test/MockDebugger'

module XoredDebugger
    class CaptureManagerTest < Test::Unit::TestCase
        
        def test_capture
            assert(SimpleServer.started?)        
            
            #create debugger
            debugger = MockDebugger.new
            thread_manager = ThreadManager.new(debugger)
            capture_manager = thread_manager.capture_manager

            # flush received data
            SimpleServer.received

            # make some output
            ping = 'helllooo...'
            $stdout.write(ping)
            $stderr.write(ping)           
            
            # terminating 
            thread_manager.terminate
            debugger.terminate
            
            actual = SimpleServer.received
            stdout_ping = StreamPacket.new('stdout',ping).to_xml
            stderr_ping = StreamPacket.new('stderr',ping).to_xml
            assert(actual.index(stderr_ping) != nil)
            assert(actual.index(stdout_ping) != nil)
            assert_equal('', SimpleServer.received)
        end
        
    end
end

require 'test/unit/testsuite'

# disable logging
require 'common/Logger'
require 'common/NullLogManager'
require 'common/StdoutLogManager'
module XoredDebugger
    Logger.setup(StdoutLogManager.new)
end

# starting simple server
require 'test/FakeParams'
require 'test/SimpleServer'
XoredDebugger::SimpleServer.start
at_exit { XoredDebugger::SimpleServer.stop }

require 'test/CommandHandlerTest'
require 'test/ResponseTest'
require 'test/SourceManagerTest'
require 'test/InitPacketTest'
require 'test/PropertyElementTest'
require 'test/ThreadEventHandlerTest'
require 'test/CaptureManagerTest'   
    

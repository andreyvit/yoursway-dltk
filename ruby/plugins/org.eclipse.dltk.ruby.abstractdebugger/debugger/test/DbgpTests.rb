require 'test/unit/testsuite'

# disable logging
require 'common/Logger'
require 'common/NullLogManager'
require 'common/StdoutLogManager'
module XoredDebugger
    Logger.setup(StdoutLogManager.new)
end

require 'test/ResponseTest'
require 'test/SourceManagerTest'
require 'test/InitPacketTest'
require 'test/PropertyElementTest'
require 'test/ThreadEventHandlerTest'

# starting simple server

require 'test/CaptureManagerTest'   
require 'test/CommandHandlerTest'

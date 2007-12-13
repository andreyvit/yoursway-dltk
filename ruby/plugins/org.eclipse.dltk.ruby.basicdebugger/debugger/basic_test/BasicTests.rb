require 'test/unit/testsuite'

# disable logging
require 'common/Logger'
require 'common/NullLogManager'
require 'common/StdoutLogManager'
module XoredDebugger
    Logger.setup(StdoutLogManager.new)
end

require 'basic_test/BasicBreakpointManagerTest'
require 'basic_test/BasicDebuggerTest'

require 'test/unit/testsuite'

# disable logging
require 'common/Logger'
require 'common/NullLogManager'
require 'common/StdoutLogManager'
module XoredDebugger
    Logger.setup(NullLogManager.new)
end

require 'basic_test/BasicBreakpointManagerTest'
require 'basic_test/BasicDebuggerTest'

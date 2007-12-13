require 'test/unit/testsuite'

# disable logging
require 'common/Logger'
require 'common/NullLogManager'
require 'common/StdoutLogManager'
module XoredDebugger
    Logger.setup(StdoutLogManager.new)
end

require 'fast_test/FastBreakpointManagerTest'
require 'fast_test/FastDebuggerTest'

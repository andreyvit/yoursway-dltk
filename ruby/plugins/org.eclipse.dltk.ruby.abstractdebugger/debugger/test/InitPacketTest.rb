require 'test/unit'
require 'dbgp/InitPacket.rb'

module XoredDebugger    
    class InitPacketTest < Test::Unit::TestCase
        def test_initialize
            init = InitPacket.new('key', 'th', 'uri')
            assert_equal('ruby', init.get_attribute('language'))
            assert_equal('th', init.get_attribute('thread'))
            assert_equal('xored_debugger', init.get_attribute('appid'))
            assert_equal('uri', init.get_attribute('fileuri'))
            assert_equal('1.0', init.get_attribute('protocol_version'))
            assert_equal('', init.get_attribute('parent'))
            assert_equal('key', init.get_attribute('idekey'))
        end
	end
end

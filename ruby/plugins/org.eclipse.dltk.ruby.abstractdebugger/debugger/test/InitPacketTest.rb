require 'test/unit'
require 'dbgp/InitPacket.rb'

module XoredDebugger    
    class InitPacketTest < Test::Unit::TestCase
        def test_initialize
            init = InitPacket.new('key', 'th', 'uri')
            assert_equal("<init language=\"ruby\" thread=\"th\" appid=\"xored_debugger\" " +
                "fileuri=\"uri\" protocol_version=\"1.0\" parent=\"\" idekey=\"key\"/>", init.to_xml)
        end
	end
end

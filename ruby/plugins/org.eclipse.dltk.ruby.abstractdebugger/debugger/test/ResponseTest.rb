require 'test/unit'
require 'dbgp/Command.rb'
require 'dbgp/Response.rb'

module XoredDebugger    
    class ResponseTest < Test::Unit::TestCase
        def test_to_xml
            command = Command.new('cmd -i 5 -d 1')
            response = Response.new(command)
            response.add_attribute('test', 1)
            response.add_attribute('success', true)
            assert_equal('<response command="cmd" transaction_id="5" success="true" test="1"/>', response.to_xml)
            
            response = Response.new(command)
            response.set_error(1)
            assert_equal('<response command="cmd" transaction_id="5"><error code="1"><message>parse error in command</message></error></response>', response.to_xml)
            
            response = Response.new(command)
            response.add_attribute('test', 1)
            response.add_attribute('success', true)
            response.set_data('Hello', true)
            assert_equal("<response command=\"cmd\" transaction_id=\"5\" success=\"true\" test=\"1\"><![CDATA[SGVsbG8=\n]]></response>", response.to_xml)

            response = Response.new(command)
            response.add_attribute('test', 1)
            response.add_attribute('success', true)
            response.set_data('Hello')
            assert_equal("<response command=\"cmd\" transaction_id=\"5\" success=\"true\" test=\"1\">Hello</response>", response.to_xml)
        end
	end
end

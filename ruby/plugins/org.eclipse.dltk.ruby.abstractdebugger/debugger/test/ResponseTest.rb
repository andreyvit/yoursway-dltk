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
            response_xml = response.to_xml
            assert(response_xml.index('command="cmd"') != nil)
            assert(response_xml.index('transaction_id="5"') != nil)
            assert(response_xml.index('success="true"') != nil)
            assert(response_xml.index('test="1"') != nil)
            
            response = Response.new(command)
            response.set_error(1)
            response_xml = response.to_xml
            assert(response_xml.index('command="cmd"') != nil)
            assert(response_xml.index('transaction_id="5"') != nil)
            assert(response_xml.index('<error code="1"><message>parse error in command</message></error>') != nil)
            
            response = Response.new(command)
            response.add_attribute('test', 1)
            response.add_attribute('success', true)
            response.set_data('Hello', true)
            response_xml = response.to_xml
            assert(response_xml.index('command="cmd"') != nil)
            assert(response_xml.index('transaction_id="5"') != nil)
            assert(response_xml.index('success="true"') != nil)
            assert(response_xml.index('test="1"') != nil)
            assert(response_xml.index("<![CDATA[SGVsbG8=\n]]>") != nil)
            
            response = Response.new(command)
            response.add_attribute('test', 1)
            response.add_attribute('success', true)
            response.set_data('Hello')
            response_xml = response.to_xml
            assert(response_xml.index('command="cmd"') != nil)
            assert(response_xml.index('transaction_id="5"') != nil)
            assert(response_xml.index('success="true"') != nil)
            assert(response_xml.index('test="1"') != nil)
            assert(response_xml.index("Hello") != nil)
        end
	end
end

require 'test/unit'
require 'dbgp/PropertyElement'

module XoredDebugger
	class PropertyElementTest < Test::Unit::TestCase
	    def test_initialize
	        p = PropertyElement.new(['a', 'b'], 'test')            
            assert_equal('2', p.get_attribute('numchildren'))
            
            	        
	        p = PropertyElement.new({1=> 'a', 2=> 'b'}, 'test')            
            assert_equal('2', p.get_attribute('numchildren'))
	    end
	end
end

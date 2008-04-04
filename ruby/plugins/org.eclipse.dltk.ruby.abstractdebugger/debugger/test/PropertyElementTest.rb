require 'test/unit'
require 'dbgp/PropertyElement'

module XoredDebugger
	class PropertyElementTest < Test::Unit::TestCase
	    def test_initialize
	        p = PropertyElement.new(['a', 'b'], 'test')            
            assert_equal('1', p.get_attribute('children'))
            
            	        
	        p = PropertyElement.new({1=> 'a', 2=> 'b'}, 'test')            
            assert_equal('1', p.get_attribute('children'))
	    end
	end
end

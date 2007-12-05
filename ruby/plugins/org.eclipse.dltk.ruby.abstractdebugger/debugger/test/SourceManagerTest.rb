require 'test/unit'
require 'dbgp/managers/source.rb'

module XoredDebugger    
	class SourceManagerTest < Test::Unit::TestCase
	    def setup 
	        @manager = SourceManager.new
	    end

	    def test_source_for()
            lines = @manager.source_for('mocksource.rb')
            assert(!lines.nil?, "Source not found")
            assert_equal(4, lines.length)
            
            thisLines1 = @manager.source_for(__FILE__)
            thisLines2 = @manager.source_for("SourceManagerTest.rb")
            assert_equal(thisLines1, thisLines2)
	    end
	    
        def test_source_reload()
           @manager.source_reload
           test_source_for 
        end
        
	    def test_line_at()
	        line = @manager.line_at('mocksource.rb', 1)
            assert_equal("# comment\n", line)
	        line = @manager.line_at('mocksource.rb', 4)
            assert_equal("end\n", line)            
	    end
	end
end

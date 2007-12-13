module XoredDebugger 
    class StackLevelInfo
        def initialize(file, line, mtd)
	        @file = file
	        @line = line
	        @method = mtd
	        @type = 'file'
	    end
	    
	    attr_reader :file, :line, :method, :type
	end # class StackLevelInfo
end
   

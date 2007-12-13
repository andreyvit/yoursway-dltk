
module XoredDebugger
    module DebugEventHandler
        def at_breakpoint(context)
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
        end
 
        def at_catchpoint(context, excpt)
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
        end
 
        def at_line(context, file, line)
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
        end        	    	    
	end
end

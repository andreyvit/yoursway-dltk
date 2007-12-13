require 'debugger/SimpleBreakpoints'

module XoredDebugger	
    class BasicLineBreakpoint < Breakpoint
        include LineBreakpointContract
	    attr_reader :filename
        attr_accessor :lineno
	    
	    def initialize(id, file, line, temporary)
	        super(id, temporary)
	        @filename = File.expand_path(file)
            @lineno = line
	    end
	    
	    def hit(context, file, line)
	        File.expand_path(file) == @filename and line == @lineno and super(context)
	    end     
	end
end


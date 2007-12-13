require 'dbgp/Utils'
require 'dbgp/XmlElement'
require 'dbgp/SourceManager'

module XoredDebugger
	class StackLevelElement < XmlElement
	    include XoredDebuggerUtils
        
	    def initialize(depth, level)
	        super('stack')
            add_attribute('level', depth)
            add_attribute('type', level.type)
            add_attribute('filename', path_to_uri(level.file))
            add_attribute('lineno', level.line)
            
            where = level.method
            if where.nil?
				where = SourceManager.instance.line_at(level.file, level.line)
			end
            add_attribute('where', where)
	    end
	end
end

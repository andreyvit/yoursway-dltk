
###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

SCRIPT_LINES__ = {} unless defined? SCRIPT_LINES__
SCRIPT_TIMESTAMPS__ = {} unless defined? SCRIPT_TIMESTAMPS__

module XoredDebugger
	class SourceManager
		def source_for(file) # :nodoc:
	      finder = lambda do
	        if File.exists?(file)
	          if SCRIPT_LINES__[file].nil? || SCRIPT_LINES__[file] == true
	            SCRIPT_LINES__[file] = File.readlines(file)
	          end

	          change_time = test(?M, file)
	          SCRIPT_TIMESTAMPS__[file] ||= change_time
	          if @reload_source_on_change && SCRIPT_TIMESTAMPS__[file] < change_time
	            SCRIPT_LINES__[file] = File.readlines(file)
	          end

	          SCRIPT_LINES__[file]
	        end
	      end
	      
	      Dir.chdir(File.dirname($0)){finder.call} || finder.call ||
	        (SCRIPT_LINES__[file] == true ? nil : SCRIPT_LINES__[file])
	    end
	    
	    def source_reload
	      SCRIPT_LINES__.keys.each do |file|
	        next unless File.exists?(file)
	        SCRIPT_LINES__[file] = nil
	      end
	    end
	    
	    def line_at(file, line) # :nodoc:
	      lines = source_for(file)
	      if lines
	        line = lines[line-1]
	        return "\n" unless line
	        return "#{line.gsub(/^\s+/, '').chomp}\n"
	      end
	      return "\n"
	    end
	end # class SourceManager
end # module XoredDebugger


    

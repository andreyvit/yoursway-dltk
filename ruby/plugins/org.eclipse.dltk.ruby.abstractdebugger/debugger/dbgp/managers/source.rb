###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger
    class SourceManager
        def initialize() 
            @script_lines = {}
        end

        def source_for(file) # :nodoc:
            finder = lambda do
	            if File.exists?(file)
	                if @script_lines[file].nil?
	                    @script_lines[file] = File.readlines(file)
	                end
	                @script_lines[file]
	            end
            end	      
            Dir.chdir(File.dirname($0)){finder.call} || finder.call ||
                (@script_lines[file] == true ? nil : @script_lines[file])
        end

        def source_reload
            @script_lines.clear
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


    

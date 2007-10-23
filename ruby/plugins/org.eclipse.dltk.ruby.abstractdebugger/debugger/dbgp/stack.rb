###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

def get_function_name(binding)
	s = <<-EOS
			begin
				 raise ''
			rescue
				$!.backtrace[0] =~/.*\`(.*)\'/
				$1
			end
		EOS
	
	function = Kernel.eval(s, binding)
	object = Kernel.eval('self.class.name', binding)
		
	if function.nil? or function.empty?
		object
	else
		object + '::' + function
	end
end

module XoredDebugger
    class StackLevelInfo
        def initialize(file, line, method = nil)
            @file = file
            @line = line
			@method = method
			@type = :source
        end
        
        attr_reader :file, :line, :method, :type
    end # class StackLevelInfo
end # module XoredDebugger
###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger
	class FileLogManager
        def initialize(filename)
            @f = File.open(filename, 'w')
        end

        def puts(str)
            @f.puts(str)
            @f.flush
        end

        def close
            @f.close
        end
    end # class FileLogManager
end # module XoredDebugger
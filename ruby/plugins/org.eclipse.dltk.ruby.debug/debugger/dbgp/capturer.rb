###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger
   
    class StdoutCapturer
        def initialize(state = false)
            @saved_stdout = $stdout
            if state
                enable
            else
                reset
            end
        end

        def write(s)
            @output += s
        end

        def enable
            reset
            $stdout = self
        end

        def disable
            $stdout = @saved_stdout
        end

        def output
            @output
        end

        def reset
            @output = ""
        end
    end # class StdoutCapturer

end # module XoredDebugger
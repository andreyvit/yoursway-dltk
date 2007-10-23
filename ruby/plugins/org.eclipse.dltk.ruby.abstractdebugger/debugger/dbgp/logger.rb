###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger
    module Logger
        def Logger.setup(logger)
            @@logger = logger
        end

        def log(s)
           @@logger.puts(s)
        end
    end # module XoredDebugger
end # module XoredDebugger
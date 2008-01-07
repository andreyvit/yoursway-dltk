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

        def Logger.shutdownLogger
            @@logger.close
        end            

        def log(s)
            @@logger.puts("[" + Thread.current.object_id.to_s + "]\t" + s)
        end
        
        def logException(ex, where)
            @@logger.puts("[" + Thread.current.object_id.to_s + "]")
            @@logger.puts(ex.class.name.to_s + ' ' + where)
            @@logger.puts("\tMessage: " + $!.message)
            @@logger.puts("\tBacktrace: " + $!.backtrace.join("\n"))
        end                
    end # module XoredDebugger
end # module XoredDebugger

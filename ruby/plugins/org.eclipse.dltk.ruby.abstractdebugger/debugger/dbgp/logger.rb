###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################
require 'monitor'

module XoredDebugger
    class LoggerAdaptor
       def initialize(logger)
           @logger = logger
       end 
       def puts(s)
           @logger.log(s)
       end
    end
    
    module Logger
        # By default log to stdout
        @@logger = $stdout
        @@monitor = Monitor.new
        
        def Logger.setup(logger)
            @@monitor.synchronize do 
                @@logger = logger
            end
        end

        def log(s)
	        @@monitor.synchronize do 
	            @@logger.puts("[" + Thread.current.object_id.to_s + "]\t" + s)
	        end
        end
        
        def logger
            LoggerAdaptor.new(self)
        end
    end # module XoredDebugger
end # module XoredDebugger
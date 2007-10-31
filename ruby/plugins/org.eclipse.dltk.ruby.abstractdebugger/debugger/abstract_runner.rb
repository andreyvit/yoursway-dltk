require 'dbgp/logger'
require 'dbgp/params'

require 'dbgp/managers/log_null'
require 'dbgp/managers/log_file'
require 'dbgp/managers/log_stdout'

module XoredDebugger
    class AbstractRunner
        include Logger
        
        def run
            debugger = nil
            begin 
	            # setting up logger
	            setupLogger()
                
	            # printing params to log
                log('Debugger params:')
	            Params.instance.print(logger)
	            
	            unless Params.instance.valid?
	                logger.puts('Invalid debugger params')
	                return
	            end  
	           
                log('Creating debugger...')
                debugger = create_debugger
              
                log('Starting debugger...')
                debugger.debug(Params.instance.script)
                log('Debug finished')
	        rescue Exception
		        log('Exception during debugging:')
		        log("\tMessage: " + $!.message)
		        log("\tBacktrace: " + $!.backtrace.join("\n"))
            ensure
                unless debugger.nil?
                    log('Terminating debugger...')
                    debugger.terminate unless debugger.nil?
                end
                shutdownLogger()               
            end            
        end
        
        def create_debugger
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end

        def setupLogger
            log = Params.instance.log
            new_logger = case (log) 
                when nil: NullLogManager.new 
                when 'stdout': StdoutLogManager.new 
                else FileLogManager.new(log)
            end            
            Logger.setup(new_logger)            
        end
        
        def shutdownLogger
            unless logger.nil?
	            logger.close 
	            logger = nil
            end
        end
    end
end

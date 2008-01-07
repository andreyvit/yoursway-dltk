require 'common/Logger'
require 'common/Params'
require 'common/NullLogManager'
require 'common/FileLogManager'
require 'common/StdoutLogManager'
require 'dbgp/Utils'
require 'dbgp/ThreadManager'

module XoredDebugger
    class AbstractRunner
        include Logger
        
        def run
            debugger = nil
            begin 
	            # setting up logger
	            setupLogger()
                
	            # printing params to log
                log("Debugger params:\n" + Params.instance.to_s)
	            
	            
	            unless Params.instance.valid?
	                log('Invalid debugger params. Exitting...')
	                return
	            end  
	           
                #loading required libraries
                begin
                    load_libraries
                rescue LoadError
                    log('Error: ' + $!.message + '. Terminating...')
                    begin
                        require 'dbgp/Utils'
                        # 998 - An internal exception in the debugger occurred
                        InitError.new(998)
                    
                    rescue Exception    
                        logException($!, 'while terminating')
                    ensure
                        Kernel.exit
                    end
                end                       

                log('Creating debugger...')
                @debugger = create_debugger
                
                log('Setting exit handler...')                
                at_exit { handle_termination($!) }
                
                log('Starting debugger...')
                @debugger.start
                log('Creating thread manager')
                @thread_manager = ThreadManager.new(@debugger)
            
            rescue SystemExit
                Kernel.exit
	        
            rescue Exception
                logException($!, ' during startup')
            end            
        end
        
        def handle_termination(excpt)
            log('Debugger termination started:')
            # redirect exception to stderr if available
            unless excpt.nil?
                logException(excpt, ' was uncaught')
            end     

            begin 
                @thread_manager.terminate(excpt) unless @thread_manager.nil?
            rescue Exception
                logException($!, ' in at_exit')
            end            

            begin                 
                @debugger.terminate unless @debugger.nil?
            rescue Exception
                logException($!, ' in at_exit')
            end            

            log('Debugger terminated')                    
            Logger.shutdownLogger()                                                                   
        end
        
        def create_debugger
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
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
        
        def load_libraries()
            #nothing to load by default
        end
    end
end

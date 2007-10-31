###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'monitor'
require 'dbgp/logger'

module XoredDebugger
    class ThreadManager
        include Logger
        
        def initialize(debugger)
            @debugger = debugger
            @creator = lambda { |thread| debugger.create_thread_wrapper(thread) }
            @monitor = Monitor.new
            @threads = {}      
                                           
            @thread_manager_cleanup = debugger.create_debug_thread do
                begin
	                log('Thread manager cleanup started')
	                loop do
	                    cleanup           
	                    sleep 1
	                end
                rescue Exception
                    log('Exception in thread manager cleanup:')
                    log("\tMessage: " + $!.message)
                    log("\tBacktrace: " + $!.backtrace.join("\n"))                
                ensure log('Thread manager cleanup terminated')
                end
            end                   
        end

        def terminate
            @thread_manager_cleanup.terminate()
            main = main_thread
			all = all_threads
            
            log('Terminating all threads...')
			(all - [main]).each { |th|
			    th.terminated
			}           
            log('Terminating main thread...')
            begin
			    main.terminated
            rescue Exception
                log('Exception')
		        log("\tMessage: " + $!.message)
		        log("\tBacktrace: " + $!.backtrace.join("\n"))                
            ensure
                log('Done')
            end
        end
        
        def current_thread
	        @monitor.synchronize do
	            @threads[Thread.current] 
	        end
        end
        
        def get_thread_wrapper(thread)
	        @monitor.synchronize do
	            @threads[thread] 
	        end
        end
        
	    def add_thread(thread)
	        @monitor.synchronize do
	            wrapper = @threads[thread] 
	            if (wrapper.nil?)
	                # if wrapper doesn't exist, creating new one
	                log('adding thread ' + thread.object_id.to_s)
	                wrapper = @threads[thread] = @creator.call(thread)
	                wrapper.created
	            end
	            wrapper
	        end         
	    end    
            
    private	    
        def cleanup
            @monitor.synchronize do
                ths = get_threads { |th| @debugger.is_debug_thread?(th) }
						
				# Removing
				removed = @threads.keys - ths
				removed.each { |t| 
					remove_thread(t) 
				}               
				nil			
            end
        end

	    def main_thread
	        @monitor.synchronize do
	            @threads[Thread.main]
	        end
	    end
	    
	    def all_threads
	        @monitor.synchronize do
	            @threads.values
	        end           
	    end
            	
        def get_threads(&filter)		
            list = Thread.list
            filter.nil? ? list : list.delete_if(&filter)
        end     
        
        def remove_thread(thread)
            wrapper = @threads.delete(thread)
            wrapper.terminated
        end                
    end # class ThreadManager
end # module XoredDebugger
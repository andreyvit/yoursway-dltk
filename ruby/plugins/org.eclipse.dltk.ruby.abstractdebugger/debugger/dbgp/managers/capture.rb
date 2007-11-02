###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'logger'
require 'monitor'

module XoredDebugger       
    DISABLE = 0
    COPY = 1
    REDIRECT = 2
    
    class Capturer
        attr_accessor :state
        
        def initialize(stream, state)
            @stream = stream 
            @redirected = Hash.new
            @state = state
            @monitor = Monitor.new
        end

        def write(s)
            # this is unsafe place which called directly from 
            # script code
            @monitor.synchronize do
	            if (@state != REDIRECT)
	                @stream.write(s)
	            end
	            if (@state != DISABLE)                                  	
		            if @redirected[Thread.current].nil?
		                @redirected[Thread.current] = s
		            else
		                @redirected[Thread.current] += s
		            end
	            end
            end
        end
        
        def redirected
            @monitor.synchronize do
                result = @redirected
                @redirected = Hash.new
                result
            end
        end 
        
        def method_missing(method, *args, &block)
          @stream.send(method, *args, &block)
        end               
    end # class Capturer

	
	class CaptureManager
	    include Logger
        
	    def initialize(debugger)	        
	        @stdout_capturer = Capturer.new($stdout, COPY)             
			@stderr_capturer = Capturer.new($stderr, COPY)
            $stdout = @stdout_capturer
            $stderr = @stderr_capturer
            
            @terminatig = false
            @flusher = debugger.create_debug_thread do
                begin
	                while (@terminatig == false)
	                    sleep 1
	                    @stdout_capturer.redirected.each_pair do |thread, message| 
	                        log('REDIRECT: ' + message)
	                        wrapper = debugger.thread_manager.get_thread_wrapper(thread)
	                        unless wrapper.nil?
	                            wrapper.io_manager.send("stdout_data", {:_data => message})
	                        end                    
	                    end
	                    @stderr_capturer.redirected.each_pair do |thread, message| 
	                        log('REDIRECT: ' + message)
	                        wrapper = debugger.thread_manager.get_thread_wrapper(thread)
	                        unless wrapper.nil?
	                            wrapper.io_manager.send("stderr_data", {:_data => message})
	                        end                    
	                    end                                        
	                end
                rescue Exception
			        log('Exception in capture flusher:')
			        log("\tMessage: " + $!.message)
			        log("\tBacktrace: " + $!.backtrace.join("\n"))                
                end
            end
		end
		
        def terminate()
            $stdout = STDOUT
            $stderr = STDERR
            @terminatig = true
            @flusher.join
        end
        
        attr_reader :stdout_capturer, :stderr_capturer
	end # class CaptureManager
end # module XoredDebugger
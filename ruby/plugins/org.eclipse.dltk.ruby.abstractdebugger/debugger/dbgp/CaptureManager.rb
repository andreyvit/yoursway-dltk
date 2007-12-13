###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'monitor'
require 'common/Logger'
require 'dbgp/StreamPacket'

module XoredDebugger           
    class Capturer
        DISABLE = 0
        COPY = 1
        REDIRECT = 2
                
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
            if (@state != REDIRECT)
                @stream.write(s)
            end
            @monitor.synchronize do
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
        
        attr_reader :stdout_capturer
        attr_reader :stderr_capturer
        
	    def initialize(thread_manager)
	        @thread_manager = thread_manager	        
	        @stdout_capturer = Capturer.new($stdout, Capturer::COPY)             
			@stderr_capturer = Capturer.new($stderr, Capturer::COPY)
            $stdout = @stdout_capturer
            $stderr = @stderr_capturer
            
            @terminating = false
            debugger = thread_manager.debugger
            @flusher = debugger.create_debug_thread do
                flush_redirected
            end
		end

        def flush_redirected
            begin
                log('Capture flusher started')
                while (@terminating == false)
                    sleep 1
                    @stdout_capturer.redirected.each_pair do |thread, message|
                        send(thread, 'stdout', message) 
                    end
                    @stderr_capturer.redirected.each_pair do |thread, message| 
                        send(thread, 'stderr', message) 
                    end                                        
                end
                log('Capture flusher terminated')
            rescue Exception
                puts $!.message
                logException($!, 'in capture flusher')      
            end            
        end
        
        def send(thread, stream, message)
            log('REDIRECT ' + stream + ': ' + message)
            dbgp_thread = @thread_manager.get_dbgp_thread(thread)
            if (!dbgp_thread.nil?)
                dbgp_thread.communicator.sendPacket(StreamPacket.new(stream, message))
            else
                # send through main thread's connection 
                dbgp_thread = @thread_manager.get_dbgp_thread(Thread.main)
                if (!dbgp_thread.nil?)
                    dbgp_thread.communicator.sendPacket(StreamPacket.new(stream, message))
                else
                    puts("Could not redirect message, cause communication links broken. Message:\n" + message)
                end
            end                                
        end
        
        def terminate()
            $stdout = STDOUT
            $stderr = STDERR
            @terminating = true
            @flusher.wakeup()
            @flusher.join
        end                
	end # class CaptureManager
end # module XoredDebugger

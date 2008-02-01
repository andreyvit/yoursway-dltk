###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'stringio'
require 'common/Logger'
require 'dbgp/StreamPacket'

module XoredDebugger
    class StdoutCapturer                     
        attr_accessor :state
        
        def initialize(&redirector)
            @redirector = redirector
            @state = CaptureManager::COPY
            @capture = StringIO.new
            $stdout = @capture           
        end
            
        def flush_captured
            temp_capturer = StringIO.new
            $stdout = temp_capturer
            @capture.rewind
            captured = @capture.read            
            @capture = temp_capturer       
            
            unless captured.empty?
                STDOUT.write(captured) if @state != CaptureManager::REDIRECT 
                @redirector.call(captured) if @state != CaptureManager::DISABLE
            end            
        end
        
        def terminate
            $stdout = STDOUT
        end   
    end # class StdoutCapturer    

    
    class StderrCapturer                     
        attr_accessor :state
        
        def initialize(&redirector)
            @redirector = redirector
            @state = CaptureManager::COPY
            @capture = StringIO.new
            $stderr = @capture           
        end
            
        def flush_captured
            temp_capturer = StringIO.new
            $stderr = temp_capturer
            @capture.rewind
            captured = @capture.read            
            @capture = temp_capturer       
            
            unless captured.empty?
                STDERR.write(captured) if @state != CaptureManager::REDIRECT 
                @redirector.call(captured) if @state != CaptureManager::DISABLE
            end            
        end
        
        def terminate
            $stderr = STDOUT
        end   
    end # class StderrCapturer    

	class CaptureManager
        DISABLE = 0
        COPY = 1
        REDIRECT = 2	    
	    include Logger
        
        attr_reader :stdout_capturer
        attr_reader :stderr_capturer
        
	    def initialize(thread_manager)
	        @thread_manager = thread_manager	        
	        @stdout_capturer = StdoutCapturer.new {
	            |message|
                # send through main thread's connection 
                dbgp_thread = @thread_manager.get_dbgp_thread(Thread.main)
                if (!dbgp_thread.nil?)
                    dbgp_thread.communicator.sendPacket(StreamPacket.new('stdout', message))
                else
                    puts("Could not redirect message, cause communication links broken. Message:\n" + message)
                end                
	        }
                         
			@stderr_capturer = StderrCapturer.new {
	            |message|
                # send through main thread's connection 
                dbgp_thread = @thread_manager.get_dbgp_thread(Thread.main)
                if (!dbgp_thread.nil?)
                    dbgp_thread.communicator.sendPacket(StreamPacket.new('stderr', message))
                else
                    puts("Could not redirect message, cause communication links broken. Message:\n" + message)
                end                
	        }
                        
            @terminating = false
            debugger = thread_manager.debugger
            @flusher = debugger.create_debug_thread do
                flusher_loop
            end
		end

        def flusher_loop 
            begin
                log('Capture flusher started')
                while (@terminating == false)
                    sleep 0.5
                    @stdout_capturer.flush_captured
                    @stderr_capturer.flush_captured                    
                end
                log('Capture flusher terminated')
            rescue Exception
                puts $!.message
                logException($!, 'in capture flusher')      
            end                        
        end       
        
        def terminate()
            @stdout_capturer.terminate
            @stderr_capturer.terminate
            @terminating = true
            @flusher.wakeup()
            @flusher.join
        end                
	end # class CaptureManager
end # module XoredDebugger

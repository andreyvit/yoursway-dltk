###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'logger'

module XoredDebugger       
    DISABLE = 0
    COPY = 1
    REDIRECT = 2
    
    class Capturer
        include Logger
        attr_accessor :state
        
        def initialize(stream, state, &redirector)
            @stream = stream 
            @redirector = redirector
            @state = state
        end

        def write(s)	
            log("REDIRECT: " + s)		
            if (@state != REDIRECT)
                @stream.write(s)
            end
            if (@state != DISABLE)          
	            @redirector.call(s)
            end
        end
    end # class Capturer

	
	class CaptureManager
        
	    def initialize(thread_manager)	        
	        @stdout_capturer = Capturer.new($stdout, COPY) do |message|
	            wrapper = thread_manager.get_thread_wrapper(Thread.current)
                unless wrapper.nil?
                    wrapper.io_manager.send("stdout_data", {:_data => message})
                end
	        end
            
			@stderr_capturer = Capturer.new($stderr, COPY) do |message|
                wrapper = thread_manager.get_thread_wrapper(Thread.current)
                unless wrapper.nil?
                    wrapper.io_manager.send("stderr_data", {:_data => message})
                end
            end
            $stdout = @stdout_capturer
            $stderr = @stderr_capturer
		end
		
        def terminate()
            $stdout = STDOUT
            $stderr = STDERR
        end
        
        attr_reader :stdout_capturer, :stderr_capturer
	end # class CaptureManager
end # module XoredDebugger
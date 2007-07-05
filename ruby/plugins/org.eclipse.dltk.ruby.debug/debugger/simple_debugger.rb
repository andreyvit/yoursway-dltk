###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'cgi'
require 'thread'

require 'dbgp/command'
require 'dbgp/command_printer'
require 'dbgp/thread_manager'

require 'dbgp/managers/io_socket'
require 'dbgp/managers/io_test'
require 'dbgp/managers/capture'
require 'dbgp/managers/feature'
require 'dbgp/managers/source'

require 'simple_thread'
require 'breakpoint_manager'

module XoredDebugger
    class RubyDebugger
		# Construction
        def initialize(host, port, key, script, logger, test)            
            @log_manager = logger
                        
            @stdout_capturer = StdoutCapturer.new(false)
            #@stderr_capturer = StderrCapturer.new(true)

            @breakpoint_manager = BreakpointManager.new
            @feature_manager = FeatureManager.new
			@source_manager = SourceManager.new

            @thread_manager = ThreadManager.new() { |thread|
                printer = Printer.new
                io = test ? TestIOManager.new(@logger, printer) : SocketIOManager.new(host, port, logger, printer)
                RubyThread.new(self, thread, io, key, script)
            }        
        end

		# Termination request
        def terminate
            log_manager.puts('Debugger terminating...')
			
			main = @thread_manager.main_thread					
			all = @thread_manager.all_threads
			
			(all - [main]).each { | th|
				th.terminate
			}
								
			main.terminate
        end

		# Common managers
        def breakpoint_manager
            @breakpoint_manager
        end
        
        def feature_manager
            @feature_manager
        end

        def log_manager
            @log_manager
        end
		
		def source_manager
			@source_manager
		end
        
        def capturer
            @stdout_capturer
        end
				
		# Tracing
        def trace(event, file, line, id, binding, klass)                        
            thread = @thread_manager.synchronize
            thread.trace(event, file, line, id, binding, klass)			
        end 
    end # class RubyDebugger

end # module

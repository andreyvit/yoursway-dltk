###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'rubygems'
require 'ruby-debug-base.rb'

require 'dbgp/logger'

require 'cgi'
require 'monitor'
require 'fast_thread'
require 'breakpoint_manager'

require 'abstract_debugger'
require 'fast_thread_manager'


module XoredDebugger
    class FastRubyDebugger < AbstractDebugger
		include Logger
        attr_reader :breakpoint_manager
        attr_reader :thread_manager
        attr_reader :capture_manager
        
        def initialize()
            super
            @breakpoint_manager = BreakpointManager.new
            @thread_manager = FastThreadManager.new(self) 
            @capture_manager = CaptureManager.new(self)        
        end
                
        def debug(script)
            Debugger.handler = self
            Debugger.start do
                # Creating main thread in ThreadManager                
                thread_manager.add_thread(Thread.current)
                
                # waiting while debugger negotate features with IDE                
                Debugger.current_context.suspend                     
                
                logger.puts('Loading script...')                    
                load script
            end            
        end
        
        def thread_context(thread)
            Debugger.thread_context(thread)
        end
        
        def current_context
            Debugger.current_context
        end

        def _create_thread_wrapper(thread, io)            
            params = Params.instance
            FastRubyThread.new(self, thread, io, params.key, params.script)            
        end
        
        def create_debug_thread(*args, &block)
            Debugger::DebugThread.new(*args, &block)
        end
        
        def is_debug_thread?(thread)
            thread.is_a? Debugger::DebugThread
        end

        # Event handlers
        def at_breakpoint(context, breakpoint)
            #check if breakpoint was disabled           
            info = @breakpoint_manager[breakpoint.id]       
            
            thread = thread_manager.current_thread
            thread.at_breakpoint(context, info)
        end

        def at_catchpoint(context, excpt)
            log(sprintf('=> at_catchpoint: %s', excpt.inspect))
            # TODO
        end

        def at_tracing(context, file, line)
            log(sprintf('=> at_tracing: %s: %d', file, line))
            # TODO
        end

        def at_line(context, file, line)
            thread = thread_manager.current_thread
            if (in_debugger_code?(thread))
                context.step_over(1)                
            else
                thread.at_line(context, file, line)
            end
        end        
    end #  class FastRubyDebugger
end # module

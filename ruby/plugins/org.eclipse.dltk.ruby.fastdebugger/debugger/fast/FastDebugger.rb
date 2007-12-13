###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
###############################################################################


require 'common/Logger'
require 'common/Params'  

require 'cgi'
require 'monitor'

require 'debugger/AbstractDebugger'
require 'fast/FastBreakpointManager'
require 'fast/FastContext'

module XoredDebugger
    
    class FastDebugger < AbstractDebugger
		include Logger
        
        def initialize()
            super
        end
                
        def get_debugger_id
            'org.eclipse.dltk.ruby.fastdebugger'
        end                
                     
        def start
            Debugger.handler = self
            Debugger.keep_frame_binding = true
            #Debugger.tracing = true
            Debugger.start
            Debugger.catchpoint = 'Object'
        end
        
        def terminate
            log('Terminating debugger')  
            begin 
                super
            ensure
                Debugger.stop
                log('Debugger state: ' + Debugger.started?.to_s)
            end
        end
        
        def create_breakpoint_manager()
            FastBreakpointManager.new
        end
                
        def create_context_impl(thread)
            return FastContext.new(thread)
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
            if (@breakpoint_manager.check_line_breakpoint(breakpoint.id))
                handler.at_breakpoint(current_context) unless handler.nil?
            else
                log('Line breakpoint doesn''t exist (raw id: ' + breakpoint.id + ')')
                current_context.skip_line = true
            end
        end

        def at_catchpoint(context, excpt)
            if (@breakpoint_manager.check_exception_breakpoint(excpt, current_context))
                handler.at_catchpoint(current_context, excpt) unless handler.nil?
            else 
                current_context.skip_line = true
            end            
        end

        def at_tracing(context, file, line)
            if (Thread.current != Thread.main)
                log('TRACE: ' + file + ':' + line.to_s)
            end
        end

        def at_line(context, file, line)       
            handler.at_line(current_context, file, line) unless handler.nil? || current_context.skip_line
            current_context.skip_line = false
        end        
    end #  class FastRubyDebugger
end # module

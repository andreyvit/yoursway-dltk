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
require 'basic/BasicBreakpointManager'
require 'basic/BasicContext'

module Kernel
    alias_method(:xored_debugger_set_trace_func, :set_trace_func)
    def set_trace_func(proc)
        raise "Cannot call 'set_trace_func' method during debugging session."
    end
end

module XoredDebugger
    class DebugThread < Thread
    end
    
    
    class BasicDebugger < AbstractDebugger
		include Logger
        
        def initialize()
            super
        end
                
        def get_debugger_id
            'org.eclipse.dltk.ruby.basicdebugger'
        end                
                     
        def start
            log('Setting tracing function')
            xored_debugger_set_trace_func proc { |event, file, line, id, binding, klass, *rest|
                trace(event, file, line, id, binding, klass)
            }
        end
        
        def terminate
            log('Terminating debugger')  
            begin 
                super
            ensure
                xored_debugger_set_trace_func nil
            end
        end
        
        def create_breakpoint_manager()
            BasicBreakpointManager.new
        end
                
        def create_context_impl(thread)
            return BasicContext.new(thread)
        end
       
        
        def create_debug_thread(*args, &block)
            DebugThread.new(*args, &block)
        end
        
        def is_debug_thread?(thread)
            thread.is_a? DebugThread
        end

            # Tracing
        def trace(event, file, line, id, binding, klass)            
            begin 	           
	            # Don't debug debugger intenal threads
	            # NOTE: To disable tracing of thread create it from tracing
	            # function (doesn't work with JRuby)
	            if (Thread.current.is_a? DebugThread)
	                return
	            end
                
                context = current_context
                
                # if thread was suspended, then stop execution
                context.check_suspended                
                                                                
                # Output handling
                case event
                    when 'line'                                                                                                                                    
                        # checking line breakpoint                               
                        context.stack_update(file, line, binding, get_label(binding, klass, id))                                                          
                        if (breakpoint_manager.check_line_breakpoint(file, line, context))
                            context.reset_stepping
                            handler.at_breakpoint(context) unless handler.nil?
                            handler.at_line(context, file, line) unless handler.nil?
                        else
                            if (context.check_stepping)
                                context.reset_stepping
                                handler.at_line(context, file, line) unless handler.nil?
                            end
                        end

                    when 'raise'
                        # checking exception breakpoint                       
                        context.stack_update(file, line, binding, get_label(binding, klass, id))                                              
                        if (breakpoint_manager.check_exception_breakpoint($!, context))
                            context.reset_stepping
                            handler.at_catchpoint(context, $!) unless handler.nil?   
                            handler.at_line(context, file, line) unless handler.nil?
                        end
                                            
                    when 'call', 'c-call', 'class'
                        context.stack_push(file, line, binding, get_label(binding, klass, id))
                        
                    when 'return', 'c-return', 'end'
                        context.stack_pop                                           
                        
                    else
	                    log('Warning: unknown event type "' + event + '"')    
                end
            rescue Exception
                logException($!, 'in tracing function')    	                
            end				
        end   
        
        def get_label(binding, klass, id)
            label = ''
 # Causes null pointer in JRuby          
            label += klass.to_s if klass != false            
            label += '::' +  id.id2name unless id.nil?
            label = get_module_name(binding) if label.empty?
            return label
        end     
        
        def get_module_name(binding)
            modules = Kernel.eval('Module.nesting', binding)
            if (!modules.empty?) 
                mod = modules[0].to_s
                if (mod.is_a? Class)
                    mod.name
                else
                    mod.to_s
                end
            else
                'toplevel'
            end
        end                            
    end #  class BasicDebugger
end # module

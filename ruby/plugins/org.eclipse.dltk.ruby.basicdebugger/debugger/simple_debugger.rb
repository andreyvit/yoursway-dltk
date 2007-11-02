###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'cgi'
require 'monitor'

require 'abstract_debugger'
require 'simple_thread'
require 'breakpoint_manager'
require 'dbgp/thread_manager'
require 'context'

module XoredDebugger 
    class DebuggerThread < Thread
    end    
    
    class RubyDebugger < AbstractDebugger
		include Logger
        
        attr_reader :breakpoint_manager, :thread_manager, :capture_manager
        
        def initialize()
            super
            @monitor = Monitor.new             
            @breakpoint_manager = BreakpointManager.new
            @thread_manager = ThreadManager.new(self)
            @capture_manager = CaptureManager.new(self)
        end	

        def get_debugger_id
            'org.eclipse.dltk.ruby.basicdebugger'
        end        
        
        def thread_context(thread)
            context = nil
            @monitor.synchronize do
                context = thread[ :debugger_context ]
	            if (context.nil?)
	                context = Context.new(thread)
	                thread[ :debugger_context ] = context
	            end
            end
            context
        end
        
        def current_context
            thread_context(Thread.current)
        end

        def _create_thread_wrapper(thread, io)
            params = Params.instance
            RubyThread.new(self, thread, io, params.key, params.script)            
        end
   
        def create_debug_thread(*args, &block)
            DebuggerThread.new(*args, &block)
	    end
        
        def is_debug_thread?(thread)
            thread.is_a? DebuggerThread
        end        


        def debug(script)
            @startup = true
            @shutdown = false
            @depth = 0
            catch(:done) do 
                begin                  
		            log("Adding main thread to thread manager...")
		            thread_manager.add_thread(Thread.current)
                    
   		            log("Setting trace function...")
		            set_trace_func proc { |event, file, line, id, binding, klass, *rest|
		                trace(event, file, line, id, binding, klass)
		            }
   		            load script
	            ensure 
	                set_trace_func nil
		            log("Trace function was unset")
	            end              
            end              
        end       
        
		# Tracing
        def trace(event, file, line, id, binding, klass)
            begin                
                # Don't debug debugger intenal threads
	            if (Thread.current.is_a? DebuggerThread)
	                return
	            end
                
                # adding current thread to thread manager
                # if it is already there, nothing happens
	            thread = thread_manager.add_thread(Thread.current)                
                
                # if thread was suspended, then stop execution
                current_context.check_suspended                
                                                                
                # Absolute path
                @file = File.expand_path(file) # Absolute file path
                @line = line
                
                # Output handling
                case event
                    when 'line'                        
                        # skipping all tracing while startup and shutdown
                        if (@startup || @shutdown)
                            return
                        end                  
                        
                        # Don't debug debugger :)                          
                        if (in_debugger_code?(thread))
                            return
                        end
                                                           
                        # checking line breakpoint                         
                        thread.stack_manager.stack.update(binding, @file, line)                                              
                        current_context.stop_reason = :none   
                        current_context.set_stack_manager(thread.stack_manager)
                                                                     
                        breakpoint = breakpoint_manager.get_line_break(thread.stack_manager.stack, @file, line)
                        unless (breakpoint.nil?)
                            current_context.reset_stepping
                            current_context.stop_reason = :breakpoint
                            thread.at_breakpoint(current_context, breakpoint)
                        end
                        
                        # check stepping
                        if (current_context.check_stepping)
                            current_context.stop_reason = :line
                        end
                        
                        unless (current_context.stop_reason == :none)
                            thread.at_line(current_context, @file, line)
                        end
                        
                    when 'call'                       
                        thread.stack_manager.stack.push(binding, @file, line)
    
                    when 'return' 
                        thread.stack_manager.stack.pop                                           

                    when 'c-call'
                        if (Thread.current == Thread.main)
	                        @depth += 1
	                        if (@startup)
	                            log('Entering script code...')
	                            @startup = false
	                        end
                        end

                    when 'c-return'
                        if (Thread.current == Thread.main)
	                        @depth -= 1
	                        if (@depth == 0 && @shutdown == false)
	                            log('Leaving script code...')
	                            @shutdown = true
	                        end
                        end
                        
                    when 'class'
                        #TODO: Do something useful

                    when 'end'
                        #TODO: Do something useful
    
                    when 'raise'
                        #TODO: Handle exception breakpoints here
                end
	        rescue Exception
                log('Exception in command loop:')
                log("\tMessage: " + $!.message)
                log("\tBacktrace: " + $!.backtrace.join("\n"))       	                
            end				
        end
    end # class RubyDebugger

end # module

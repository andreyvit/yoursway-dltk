require 'common/Logger'
require 'dbgp/DbgpThread'
require 'dbgp/ThreadEventHandler'
require 'dbgp/Utils'
require 'debugger/DebugEventHandler'

module XoredDebugger
    class ThreadManager
        include ThreadEventHandler
        include DebugEventHandler
        include Logger
        include XoredDebuggerUtils

        attr_reader :debugger
        attr_reader :capture_manager 
		attr_reader :source_manager
        
        def initialize(debugger)
            Thread.set_event_handler(self)  
            @debugger = debugger
            @debugger.handler = self
	        @capture_manager = CaptureManager.new(self)         		               
            @source_manager = SourceManager.instance

            # fake started event for main thread
            started()                                    
        end

        def terminate(excpt = nil)    
            log('Terminating thread manager')   
            Thread.set_event_handler(nil)
            Thread.list.each do |thread|
                dbgp_thread = thread[ :dbgp_thread_wrapper ]
                dbgp_thread.exited(nil) unless dbgp_thread.nil? || Thread.main == thread
            end     
            print_exception(excpt) unless excpt.nil? || excpt.is_a?(SystemExit)       
            @capture_manager.terminate     
            exited(excpt)                  
        end
        
        def get_dbgp_thread(thread)
            thread[ :dbgp_thread_wrapper ]
        end
        
        def started()
            if (@debugger.is_debug_thread?(Thread.current))
                return
            end

            log('Application thread started')            
            thread = Thread.current
            thread[ :dbgp_thread_wrapper ] = DbgpThread.new(thread, self)

            # suspending current context, till wrapper initialized
            @debugger.current_context.suspend                            
	    end
        
        def exited(excpt)
            if (@debugger.is_debug_thread?(Thread.current))
                return
            end
            
            log('Application thread exited')
            logException(excpt, 'at exited()') unless excpt.nil?                          
            dbgp_thread = Thread.current[ :dbgp_thread_wrapper ]
            Thread.current[ :dbgp_thread_wrapper ] = nil
            dbgp_thread.exited(excpt) unless dbgp_thread.nil?                      
        end	  
        
        def at_breakpoint(context)
            log('at_breakpoint')                        
            dbgp_thread = Thread.current[ :dbgp_thread_wrapper ]
            dbgp_thread.at_breakpoint(context) unless dbgp_thread.nil?
        end
 
        def at_catchpoint(context, excpt)
            log('at_catchpoint: ' + excpt.class.name)                        
            dbgp_thread = Thread.current[ :dbgp_thread_wrapper ]
            dbgp_thread.at_catchpoint(context, excpt) unless dbgp_thread.nil?
        end
 
        def at_line(context, file, line)
            log('at_line: ' + file + ':' + line.to_s)
            if (in_debugger_code?(context))
                log('stepover debugger code')
                context.suspend unless context.status == AbstractContext::BREAK
                context.step_over()                
            else                                    
	            dbgp_thread = Thread.current[ :dbgp_thread_wrapper ]
	            dbgp_thread.at_line(context, file, line) unless dbgp_thread.nil?
            end
        end    
        
        def in_debugger_code?(context)
            # Don't debug debugger :)
            
            depth = get_stack_depth(context)
            
            if (depth == 0)
                return true
            end
            
            depth.times { |index|
                file = context.stack_frame(index).file
                method = context.stack_frame(index).method.to_s
                if (method.index('XoredDebugger::') == 0 || !file.index(@debugger.get_debugger_id).nil?)
                    return true
                end
            }
            false     
        end       

        # TODO: Why this is not printed by VM
        def print_exception(ex)
            message = ex.backtrace.delete_if { |s| s.index(@debugger.get_debugger_id) != nil }
            message[0] += ': ' + ex.message + ' (' + ex.class.name + ')'
            message.each_index {
                |i| message[i] = (i==0 ? message[i] : "\t from " + message[i]) 
            }
            $stderr.write( message.join("\n")+"\n" )
        end                                                   
    end 
end

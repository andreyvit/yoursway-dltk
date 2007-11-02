require 'dbgp/logger'

require 'dbgp/command'
require 'dbgp/command_printer'

require 'dbgp/managers/io_socket'
require 'dbgp/managers/io_test'
require 'dbgp/managers/capture'
require 'dbgp/managers/feature'
require 'dbgp/managers/source'

module XoredDebugger
	class AbstractDebugger
	    include Logger
        
		attr_reader :feature_manager
		attr_reader :source_manager
        
	    def initialize()          		               
            @feature_manager = FeatureManager.new
            @source_manager = SourceManager.new                            
        end

        def terminate         
			capture_manager.terminate
            thread_manager.terminate                      
        end                              
        
        def create_thread_wrapper(thread)
            params = Params.instance	   
   	        io = if params.test
	            TestIOManager.new(Printer.new)
	        else 
	            SocketIOManager.new(params.host, params.port, Printer.new)
	        end
            _create_thread_wrapper(thread, io)
        end        

        def in_debugger_code?(thread)
            # Don't debug debugger :)
            thread.stack_manager.depth.times { |index|
                method = thread.stack_manager[index].method.to_s
                if (method.index('XoredDebugger::') == 0)
                    return true
                end
            }      
            false     
        end
        
        def handle_main_thread_exception(exception)   
            backtrace = exception.backtrace.delete_if { |s| s.index(get_debugger_id) != nil }
	        trace = backtrace.shift + ': ' + exception.message + "\n"
	        other_traces = backtrace.join("\n\t")
	        unless other_traces.empty?
	            trace += "\t" + other_traces
	        end
	        capture_manager.stderr_capturer.write(trace)
        end        
        
        # methods to override
        def get_debugger_id
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
        
        def capture_manager
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end        

        def thread_manager
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end        
        
        def current_context
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
        
        def thread_context(thread)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
        
        def breakpoint_manager
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
        
        def _create_thread_wrapper(thread, io)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
   
   	    def create_debug_thread(*args, &block)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
	    end
        
        def is_debug_thread?(thread)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end        
	end
end
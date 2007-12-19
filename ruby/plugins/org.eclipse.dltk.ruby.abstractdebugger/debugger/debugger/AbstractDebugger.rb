require 'common/Logger'

require 'dbgp/Command'
require 'dbgp/CaptureManager'
require 'dbgp/SourceManager'
require 'debugger/FeatureManager'
require 'debugger/DebugEventHandler'

module XoredDebugger
	class AbstractDebugger
	    include Logger
        
		attr_reader :feature_manager
        attr_reader :breakpoint_manager
        
	    def initialize()
            @feature_manager = FeatureManager.new                          
            @breakpoint_manager = create_breakpoint_manager()
        end
        
        def terminate
            Thread.list.each do |thread|
                thread[ :debugger_context ] = nil
            end               
        end                                  
        
        def handler
            @handler
        end
        
        def handler=(value)
            if (value.nil? || (value.is_a? DebugEventHandler))
                @handler = value
            else
                raise ArgumentError, 'Hanlder MUST inherit DebugEventHandler'
            end
        end
        
        def current_context
            thread_context(Thread.current)
        end              
        
        def thread_context(thread)
            context = thread[ :debugger_context ]
            if (context.nil?)
                context = create_context_impl(thread)
                thread[ :debugger_context ] = context
            end
            return context
        end                      
        
        # methods to override
        def get_debugger_id
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
        end

        def create_breakpoint_manager()        
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
        end
        
        def create_context_impl(thread)
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
        end              

   	    def create_debug_thread(*args, &block)
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
	    end
        
        def is_debug_thread?(thread)
            raise NotImplementedError, 'This method MUST be implemented in ancessors'
        end        
	end
end

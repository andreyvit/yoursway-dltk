

module XoredDebugger
	module ThreadEventHandler
	    def started()
	    	raise NotImplementedError.new('You MUST implement this method in ancessors') 	        
	    end
        
        def exited(excpt)
	    	raise NotImplementedError.new('You MUST implement this method in ancessors')             
        end	    
	end
end


class Thread
    class << self 
        alias __internal_ruby_debugger_new :new
        @@handler = nil
        
        def new(*args, &block)
            thread = __internal_ruby_debugger_new(*args) do
                begin
                    @@handler.started() unless @@handler.nil?
                    block.call(*args)
                ensure                  
                    @@handler.exited($!) unless @@handler.nil?
                end
            end 
            thread
        end
        
        def set_event_handler(handler)
            if (handler.nil? || (handler.is_a? XoredDebugger::ThreadEventHandler))
                @@handler = handler
            else
                throw ArgumentError, 'Handler MUST inherit ThreadEventHandler'
            end
        end
        
        alias start :new
        alias fork :new
    end
end

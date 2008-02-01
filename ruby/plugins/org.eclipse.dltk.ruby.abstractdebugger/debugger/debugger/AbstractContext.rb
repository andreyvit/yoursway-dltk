require 'debugger/StackLevelInfo'


module XoredDebugger 
	class AbstractContext
        # States 
        STARTING = 'starting' # prior to execution of any code
        STOPPING = 'stopping' # after completion of code execution
        STOPPED = 'stopped'   # IDE is detached from process, no further interaction is possible.
        RUNNING = 'running'   # code is currently executing
        BREAK = 'break'       # code execution is paused, for whatever reason
                
        def status()
            if dead?
                return STOPPED
            elsif suspended?
                return BREAK
            else
                return RUNNING
            end
        end
                       
        def dead?
	    	raise NotImplementedError.new('You MUST implement this method in ancessors')             
        end

        def suspended?
	    	raise NotImplementedError.new('You MUST implement this method in ancessors')             
        end
        
        def thread
	    	raise NotImplementedError.new('You MUST implement this method in ancessors')             
        end        	    	   	   
        
        def stack_manager
	    	raise NotImplementedError.new('You MUST implement this method in ancessors')             
        end
        
        # Continuation Commands
        def run()
	    	raise NotImplementedError.new('You MUST implement this method in ancessors') 
        end
        
        def step_into
	    	raise NotImplementedError.new('You MUST implement this method in ancessors') 
        end
        
        def step_over
	    	raise NotImplementedError.new('You MUST implement this method in ancessors') 
        end
        
        def step_out
	    	raise NotImplementedError.new('You MUST implement this method in ancessors') 
        end
		
        def stop
	    	raise NotImplementedError.new('You MUST implement this method in ancessors') 
        end 
        
        def suspend
            raise NotImplementedError.new('You MUST implement this method in ancessors') 
        end 

        # Evaluation
        def eval(text, index)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
    
        # Stack depth
        def stack_frames_num
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
        
        # Stack level
        def stack_frame(index)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end                     
	end
end

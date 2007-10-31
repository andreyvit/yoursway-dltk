require 'monitor'

module XoredDebugger
    class Context
        include Logger 
        attr_accessor :steps_to_stop, :stop_reason
        attr_writer   :stop_frame
        def initialize(thread)
            @thread = thread
            @suspend = false
            @monitor = Monitor.new
            @waitResumed = @monitor.new_cond
            reset_stepping
        end
        
        def set_stack_manager(stack_manager)
            @stack_manager = stack_manager
        end
        
        def dead?
            not @thread.alive?
        end
        
        def suspended?
            suspended
            @monitor.synchronize do
                suspended = @suspend
            end
            suspended
        end
        
        def suspend
            @monitor.synchronize do
                log('Requesting thread ' + @thread.object_id.to_s + ' to suspend')
                @suspend = true
            end
        end
        
        def resume
            @monitor.synchronize do
                log('Requesting thread ' + @thread.object_id.to_s + ' to resume')
                @suspend = false
                @waitResumed.signal
            end
        end
        
        def step(n)
            @steps_to_stop = n
            @stop_frame = 0
            @max_depth = -1            
        end
        
        def step_over(n)
            @steps_to_stop = n
            @stop_frame = 0
            @max_depth = @stack_manager.stack.depth
        end
        
        def interrupt
            @thread.terminate
        end   
        
        def reset_stepping
            @stop_reason = :none
            @steps_to_stop = -1
            @stop_frame = -1
            @max_depth = -1
        end
        
        def check_suspended
            if (Thread.current != @thread)
                log('Context.check_suspended called from invalid thread')
                raise ThreadError.new('Context.check_suspended called from invalid thread')
            end
            @monitor.synchronize do
	            while (@suspend)
                    log('Suspending thread ' + @thread.object_id.to_s)
	                @waitResumed.wait
                    log('Resuming thread ' + @thread.object_id.to_s)
	            end  
            end          
        end
        
        def check_stepping
            current_depth = @stack_manager.stack.depth
            if ((@max_depth == -1 || @max_depth >= current_depth) && @steps_to_stop > 0)
                @steps_to_stop -= 1
            end
                        
            if (@stop_frame == current_depth || @steps_to_stop == 0)
                reset_stepping
                true
            else
                false
            end
        end
    end
end

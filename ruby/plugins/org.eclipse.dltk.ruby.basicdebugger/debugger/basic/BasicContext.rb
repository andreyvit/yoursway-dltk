require 'monitor'
require 'common/Logger'
require 'debugger/AbstractContext'
require 'debugger/Exceptions'

module Kernel
    def xored_debugger_current_method
        caller[0] =~ /\d:in `([^']+)'/
        $1
    end 
end

module XoredDebugger    
    class BasicContext < AbstractContext
        include Logger
        STOP_AT_ANY_DEPTH = -1
        
        def initialize(thread)
            @levels = []
            @thread = thread
            @suspended = false
            @monitor = Monitor.new
            @waitResumed = @monitor.new_cond            
            reset_stepping
        end
        
        def thread
            @thread             
        end        
        
        def dead?
            not @thread.alive?             
        end

        def suspended?
	    	@suspended             
        end
        
        # Continuation Commands
        def run()
            if (@suspended)
                @monitor.synchronize do
	                log('Requesting thread ' + @thread.object_id.to_s + ' to resume')
	                @suspended = false
	                @waitResumed.signal
                end                
            else
                raise OperationNotAvailableError
            end
        end
        
        def step_into
            @stepping = true
            @stop_depth = STOP_AT_ANY_DEPTH
            run
        end
        
        def step_over
            @stepping = true
            @stop_depth = stack_frames_num
            run
        end
        
        def step_out
            @stepping = true
            @stop_depth = stack_frames_num - 1
            run                
        end
		
        def stop
            begin
                reset_stepping
                run if @suspended
            ensure 
                Kernel.exit!
            end
            nil            
        end     
        
        def suspend
            if (!@suspended)
                log('Requesting thread ' + @thread.object_id.to_s + ' to suspend')
                @suspended = true                                
            else
                raise OperationNotAvailableError
            end
        end
        
        # Evaluation
        def eval(text, index)
            frame = @levels[stack_frames_num - 1 - index]
            if frame.nil?
                log('frame ' + index.to_s + ' is nil ' + @levels.inspect)
                return nil
            end
            binding = frame[ :binding ]
            info = frame[ :info ]
            Kernel.eval(text, binding, info.file, info.line)            
        end
                    
        # Stack depth
        def stack_frames_num
            @levels.length
        end
        
        # Stack level
        def stack_frame(index)
            frame = @levels[stack_frames_num - 1 - index]
            return frame[ :info ]
        end  
                    
                
        # basic debugger specific methods
        def stack_update(file, line, binding, label)
            if (@levels.empty?)
                stack_push(file, line, binding, label)
            else
	            new_frame = {
	                :binding => binding,
	                :info => StackLevelInfo.new(file, line, label)
	            }           
	            @levels[stack_frames_num - 1] = new_frame
            end
        end           

        def stack_push(file, line, binding, label)
            frame = {
                :binding => binding,
                :info => StackLevelInfo.new(file, line, label)
            }
            @levels.push(frame)
        end           
        
        def stack_pop
            @levels.pop            
            if (@stepping && (@stop_depth >= stack_frames_num))
                @stop_depth = STOP_AT_ANY_DEPTH
            end
        end
        
        def check_suspended
            if (Thread.current != @thread)
                raise ThreadError, 'Context.check_suspended called from invalid thread'
            end
            
            @monitor.synchronize do
                while (@suspended)
                    log('Suspending thread ' + @thread.object_id.to_s)
	                @waitResumed.wait
                    log('Resuming thread ' + @thread.object_id.to_s)
	            end  
            end                                  
        end
        
        def check_stepping
            depth = stack_frames_num
            return (@stepping && (@stop_depth == STOP_AT_ANY_DEPTH || depth <= @stop_depth))                
        end
            
        def reset_stepping
            @stepping = false
            @stop_depth = STOP_AT_ANY_DEPTH
        end                 
    end
end

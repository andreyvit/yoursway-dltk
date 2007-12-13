require 'common/Logger'
require 'debugger/AbstractContext'
require 'debugger/Exceptions'

module XoredDebugger    
    class FastContext < AbstractContext
        include Logger
        attr_accessor :skip_line
                       
        def initialize(thread)
            @context = Debugger.thread_context(thread)
            @skip_line = false
        end
        
        def thread
            @context.thread
        end
                
        def status()
            if @context.dead?
                return STOPPED
            elsif @context.suspended?
                return BREAK
            else
                return RUNNING
            end
        end
        
        # Continuation Commands
        def run()
            begin
                log('Running thread ' + @context.thread.object_id.to_s)
                @context.resume
            rescue Exception
                logException($!, 'in context')
                raise OperationNotAvailableError 
            end 
            nil
        end
        
        def step_into
            begin
                log('Stepping into thread ' + @context.thread.object_id.to_s)
                @context.step(1)
                @context.resume
            rescue Exception
                logException($!, 'in context')
                raise OperationNotAvailableError 
            end 
            nil 
        end
        
        def step_over
            begin
                log('Stepping over thread ' + @context.thread.object_id.to_s)
                @context.step_over(1)
                @context.resume
            rescue Exception
                logException($!, 'in context')
                raise OperationNotAvailableError 
            end 
            nil
        end
        
        def step_out
            begin
                log('Stepping out thread ' + @context.thread.object_id.to_s)
                @context.stop_frame = 0
                @context.resume            
            rescue Exception
                logException($!, 'in context')
                raise OperationNotAvailableError 
            end 
            nil 
        end
		
        def stop
            begin
                log('Stopping thread ' + @context.thread.object_id.to_s)
	            if (@context.suspended?)
	                @context.resume
	            end            
            ensure 
	            Kernel.exit!
            end
            nil
        end     
        
        def suspend
            begin
                log('Suspending thread ' + @context.thread.object_id.to_s)
                @context.suspend
            rescue Exception
                logException($!, 'in context')
                raise OperationNotAvailableError 
            end 
            nil 
        end
        
        # Evaluation
        def eval(text, index)
            begin
                Kernel.eval(text, @context.frame_binding(index))
            rescue Exception
                logException($!, 'in context')
                raise OperationNotAvailableError 
            end 
        end
                    
        # Stack depth
        def stack_frames_num
            begin                
                @context.stack_size
                
            rescue Exception
                logException($!, 'in context')
                raise OperationNotAvailableError 
            end 
        end
        
        # Stack level
        def stack_frame(index)
            begin
                file = @context.frame_file(index)
                line = @context.frame_line(index)
                
                c = @context.frame_self(index).class.name
                m = @context.frame_method(index).to_s
                method = sprintf('%s::%s', c, m)
      
                StackLevelInfo.new(file, line, method)
            rescue Exception
                logException($!, 'in context')
                raise OperationNotAvailableError 
            end 
        end             
    end
end

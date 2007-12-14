require 'debugger/AbstractContext'
require 'debugger/Exceptions'

module XoredDebugger    
    class MockContext < AbstractContext
        include Logger
        
        attr_accessor :status
        attr_accessor :stack_frames        
                            
        def initialize()      
            @status = RUNNING
            @stack_frames = [StackLevelInfo.new('test.rb', 1, nil),
                StackLevelInfo.new('test.rb', 8, nil)]
        end
        
        # Continuation Commands
        def run()
            if (@status == BREAK)
                @status = RUNNING
            else
                raise OperationNotAvailableError 
            end 
        end
        
        def step_into
            if (@status == BREAK)
                @status = BREAK
            else
                raise OperationNotAvailableError 
            end 
        end
        
        def step_over
            if (@status == BREAK)
                @status = BREAK
            else
                raise OperationNotAvailableError 
            end 
        end
        
        def step_out
            if (@status == BREAK)
                @status = STOPPED
            else
                raise OperationNotAvailableError 
            end 
        end
		
        def stop
            @status = STOPPED
        end     
        
        def suspend
            if (@status == RUNNING)
                @status = BREAK
            else
                raise OperationNotAvailableError
            end 
        end
        
        # Evaluation
        def eval(text, index)
            if (@status == BREAK)
                Kernel.eval(text)
            else
                raise OperationNotAvailableError 
            end                         
        end
                    
        def thread
            Thread.current
        end
            
        
        # Stack depth
        def stack_frames_num
            if (@status == BREAK)
                @stack_frames.length
            else
                raise OperationNotAvailableError 
            end                         
        end
        
        # Stack level
        def stack_frame(index)
            if (@status == BREAK)
                @stack_frames[index]
            else
                raise OperationNotAvailableError 
            end                         
        end             
    end
end

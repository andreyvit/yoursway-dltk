

module XoredDebugger
    module BreakpointContract
        COND_GREATER_OR_EQUALS = '>='
        COND_EQUALS            = '=='   
        COND_MULTIPLE          = '%'
                
	    def breakpoint_id
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end	        
        
        def state
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end

        def state=
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end
                
        def temporary
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end
        
        def expression
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end
        
        def expression=
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end
        
        def hit_value
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end
        
        def hit_value=
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end

        def hit_condition
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end
             
        def hit_condition=
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end

        def hit_count
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end     
	end
    
    module LineBreakpointContract include BreakpointContract
        def filename
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end
        
        def lineno
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end                                

        def lineno=
	        raise NotImplementedError.new('This method MUST be implemented in ancessors')
        end                                
    end   
    
    module ExceptionBreakpointContract include BreakpointContract
       def exception
           raise NotImplementedError.new('This method MUST be implemented in ancessors')
       end 
    end
end 

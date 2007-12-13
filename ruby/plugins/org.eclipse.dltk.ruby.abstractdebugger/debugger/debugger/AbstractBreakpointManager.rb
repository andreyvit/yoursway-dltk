###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'monitor'
require 'debugger/BreakpointContracts'
require 'debugger/Exceptions'
require 'debugger/SimpleBreakpoints'

module XoredDebugger       
    class AbstractBreakpointManager
        @@id = 0
		def AbstractBreakpointManager.next_id
		    @@id += 1
		end
                
        def initialize()
            @breakpoints = {} 
            @monitor = Monitor.new
        end 

        def add_line_breakpoint(file, line, temporary = false)
            @monitor.synchronize do
	            id = AbstractBreakpointManager.next_id
	            bp = add_line_breakpoint_impl(id, file, line, temporary)
	            @breakpoints[id] = bp
	            return bp
            end
        end

        def add_exception_breakpoint(exceptionClazz, temporary = false)
            @monitor.synchronize do
	            id = AbstractBreakpointManager.next_id
	            bp = ExceptionBreakpoint.new(id, eval_class(exceptionClazz), temporary)
	            @breakpoints[id] = bp
	            return bp
            end
        end
        
        def breakpoints()
            @monitor.synchronize do
                @breakpoints.values
            end
        end
        
        def breakpoint(id)
            bp = @monitor.synchronize do
                @breakpoints[id]
            end
                        
            if (bp.nil?)
                raise NoSuchBreakpointError
            end
            return bp            
        end
        
        def remove_breakpoint(id)
            bp = nil
            
            @monitor.synchronize do
                bp = @breakpoints.delete(id)
            end

            if (bp.nil?)
                raise NoSuchBreakpointError
            end
                       
            if (bp.is_a? LineBreakpointContract)
                remove_line_breakpoint_impl(bp)
            elsif (bp.is_a? ExceptionBreakpointContract)
                # Nothing to do
            end
            
            nil
        end
        
        def check_exception_breakpoint(exception, context)
            @monitor.synchronize do
                result = false
                for bp in breakpoints do
                    if (bp.is_a? ExceptionBreakpoint) && bp.hit(context, exception)
                        if (bp.temporary)
                            remove_breakpoint(bp.breakpoint_id)
                        end
                        result = true
                    end
                end
                return result
            end
        end 
        
    protected
	    def add_line_breakpoint_impl(id, file, line, temporary = false)
	        raise NotImplementedError, 'This method MUST be implemented in ancessors'            
	    end
	
	    def remove_line_breakpoint_impl(bp)
	        raise NotImplementedError, 'This method MUST be implemented in ancessors'            
	    end     
        
    private    
        def eval_class(exceptionClazz)
            failed = false
            begin
                clazz = Kernel.eval(exceptionClazz)
            rescue Exception
                failed = true
            end 
            
            if (failed || !(clazz.ancestors.include?(Exception)))
                raise BreakpointCouldNotBeSetError
            end
            return clazz           
        end
    end # class AbstractBreakpointManager
end # module XoredDebugger

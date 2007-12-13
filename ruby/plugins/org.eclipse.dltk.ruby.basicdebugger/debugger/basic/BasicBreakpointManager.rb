require 'common/Logger'
require 'debugger/AbstractBreakpointManager'
require 'basic/BasicLineBreakpoint'

module XoredDebugger
	class BasicBreakpointManager < AbstractBreakpointManager
	    include Logger
        
        def initialize()
            super()
        end     
        
        def add_line_breakpoint_impl(id, file, line, temporary = false)
            BasicLineBreakpoint.new(id, file, line, temporary)
        end
    
        def remove_line_breakpoint_impl(bp)            
        end
        
        def check_line_breakpoint(file, line, context)
            result = false
            breakpoints.each() do |bp|
                if ((bp.is_a? BasicLineBreakpoint) && bp.hit(context, file, line))                    
                    if (bp.temporary)
                        remove_breakpoint(bp.breakpoint_id)
                    end
                    result = true
                end
            end
            return result
        end       
	end
end

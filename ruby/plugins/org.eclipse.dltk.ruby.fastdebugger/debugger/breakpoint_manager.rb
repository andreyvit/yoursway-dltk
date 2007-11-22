###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'dbgp/logger'
require 'dbgp/managers/breakpoint'


module XoredDebugger    
    class BreakpointManager < AbstractBreakpointManager
    	include Logger

        def initialize()
            super
            @line_bps = Hash.new
        end        
               
        def create_breakpoint(info)
            if info.class == LineBreakpointInfo
                b = Debugger.add_breakpoint(info.file, info.line)
                @line_bps[b.id] = info
                b
            elsif info.class == ExceptionBreakpointInfo
                info
            else
                nil
            end
        end

        def update_breakpoint(bp, info)
            if (info.class == LineBreakpointInfo.class)
	            bp.hit_value = info.hit_value
	            bp.hit_condition = BreakpointManager.convert_condition(info.hit_condition)
	            unless info.expression.nil?
	                bp.expr = info.expression
	            end
	        elsif info.class == ExceptionBreakpointInfo
                # Nothing to do ;)
	        end
            nil                        
        end

        def update_info(bp, info)
            info.hit_count = bp.hit_count
            nil
        end

        def remove_breakpoint(bp, info)
            if (info.class == LineBreakpointInfo.class)
                Debugger.remove_breakpoint(bp.id)
                @line_bps.remove(bp.id)
	        elsif info.class == ExceptionBreakpointInfo
                # Nothing to do ;)
	        end
            nil                        
        end        
        
        
        def get_line_bp(id)
            @line_bps[id]
        end
        
        def exception_break?(stack, exception)
            @monitor.synchronize do
                result = nil
                for bp in @infos.values do
                    if bp.class == ExceptionBreakpointInfo && bp.hit(stack, exception)
                        result = bp
                    end
                end
                result
            end
        end
             
    private
		def BreakpointManager.convert_condition(condition)
			if condition == BreakpointInfo::COND_GREATER_OR_EQUALS
				:ge
			elsif condition == BreakpointInfo::COND_EQUALS
				:eq
			elsif condition == BreakpointInfo::COND_MULTIPLE
				:mod
			end
		end   
        
        def debugger_get_breakpoint(id)
            Debugger.breakpoints.each do |b| 
                if (b.id == id)
                    return b
                end
            end
            nil
        end     
    end # class BreakpointManager
end # module XoredDebugger

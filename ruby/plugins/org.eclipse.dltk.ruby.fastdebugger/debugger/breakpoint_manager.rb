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
        
		def initialize
		    @breakpoints = Hash.new()
		end
        
        # adds breakpoint and returns its id
        def add(info)
            if info.class == LineBreakpointInfo
                b = Debugger.add_breakpoint(info.file, info.line)
                update(b.id, info)
                @breakpoints[b.id] = info
                b.id
            else # TODO: if info.class == ExceptionBreakpointInfo
                raise NotImplementedError('Support for this type of exceptions is not implemented')
            end
        end

        # udates breakpoint with id to info
        def update(id, info)
            b = debugger_get_breakpoint(id)
            unless (b.nil?)
	            b.hit_value = info.hit_value
	            b.hit_condition = BreakpointManager.convert_condition(info.hit_condition)
	            unless info.expression.nil?
	                info.expression.inspect
	                b.expr = info.expression
	            end                        

                oldInfo = @breakpoints[id]
                unless oldInfo.nil?
                    oldInfo.update(info)
                end 
            end
            nil
        end

        # removes breakpoint with id
        def remove(id)
            @breakpoints.delete(id)
            Debugger.remove_breakpoint(id)
            nil
        end

        # returns breakpoint info (breakpoint_id, hit_count - additional attributes)
        def [] (id)
            info = @breakpoints[id]
            b = debugger_get_breakpoint(id)
            unless b.nil?
                info.hit_count = b.hit_count
            end
            info
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

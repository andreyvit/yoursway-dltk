###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger

    class BreakpointInfo	
        def hit(stack)
            if (@hit_count.nil?)
                @hit_count = 0
            end
            @hit_count = @hit_count + 1
			
			# State
            if not state
                return false # Breakpoint disabled
            end
			
            # Expression
            unless expression.nil?
                begin
                    if not stack.eval(expression.to_s)
                        return false # Expression evaluated to false
                    end
                rescue Exception
                    return false # Expression can not be evaluated
                end
            end

            # Hit condition
            case hit_condition
                when BreakpointInfo::COND_GREATER_OR_EQUALS
                    @hit_count >= hit_value
                when BreakpointInfo::COND_EQUALS
                    @hit_count == hit_value
                when BreakpointInfo::COND_MULTIPLE
                    (@hit_count % hit_value) == 0
                else
                    false
            end
        end
        protected :hit
    end # class Breakpoint

    class ExceptionBreakpointInfo < BreakpointInfo
        def hit(s, e)
            e.is_a? exception and super(s)
        end
    end # class ExceptionBreakpoint
    
        
    class AbstractBreakpointManager
        @@id = 0
		def AbstractBreakpointManager.next_id
		    @@id += 1
		end
                
        def initialize() 
            @monitor = Monitor.new
            @infos = Hash.new
            @breakpoints = Hash.new
        end
        
        attr_reader :infos, :breakpoints
        
        # Interface methods
        def add(info)
            id = nil
            @monitor.synchronize do
                bp = create_breakpoint(info)
                unless bp.nil?
	                id = AbstractBreakpointManager.next_id
	                @infos[id] = info
	                @breakpoints[id] = bp
                end
            end
            id
        end

        def update(id, new_info)
            @monitor.synchronize do
                info = @infos[id]
                bp = @breakpoints[id]
                unless info.nil? || bp.nil?
                    info.update(new_info)
                    @breakpoints[id] = update_breakpoint(bp, info)
                end
            end
            nil
        end

        def remove(id)
            @monitor.synchronize do
                info = @infos.delete(id)
                bp = @breakpoints.delete(id)
                remove_breakpoint(bp, info)
            end
            nil
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
        
        def [] (id)
            info = @infos[id]
            bp = @breakpoints[id]
            if (info.nil?)
                nil
            else
                update_info(bp, info)
                info.clone
            end
        end
        
        def create_breakpoint(info)
            raise NotImplementedError, 'You MUST implement this method in ancessors'
        end
        
        def update_breakpoint(bp, info)
            raise NotImplementedError, 'You MUST implement this method in ancessors'
        end
        
        def update_info(bp, info)
            raise NotImplementedError, 'You MUST implement this method in ancessors'
        end
        
        def remove_breakpoint(bp, info)
            raise NotImplementedError, 'You MUST implement this method in ancessors'
        end
    end # class BreakpointManager
end # module XoredDebugger
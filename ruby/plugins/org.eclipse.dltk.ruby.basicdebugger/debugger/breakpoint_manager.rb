###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'monitor'

require 'dbgp/logger'
require 'dbgp/managers/breakpoint'


module XoredDebugger
    class LineBreakpointInfo < BreakpointInfo
        def hit(s, f, l)
            file == f and line == l and super(s)
        end
    end # class LineBreakpoint


    class BreakpointManager < AbstractBreakpointManager
        def create_breakpoint(info)
            info
        end
        
        def update_breakpoint(bp, info)
            nil
        end
        
        def update_info(bp, info)
            nil
        end
        
        def remove_breakpoint(bp, info)
            nil
        end
        
        
        # Manager specific methods
        def get_line_break(stack, file, line)
            @monitor.synchronize do
                result = nil
                for bp in @infos.values do
                    if bp.class == LineBreakpointInfo && bp.hit(stack, file, line)
                        result = bp
                    end
                end
                result
            end
        end
    end # class BreakpointManager
end # module XoredDebugger

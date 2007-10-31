###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger
    # BreakpointManager interface example
    class AbstractBreakpointManager
        # adds breakpoint and returns its id
        def add(info)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end

        # udates breakpoint with id to info
        def update(id, info)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end

        # removes breakpoint with id
        def remove(id)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
       
        # returns breakpoint info (breakpoint_id, hit_count - additional attributes)
        def [] (id)
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end 
    end # class BreakpointManager
end # module XoredDebugger
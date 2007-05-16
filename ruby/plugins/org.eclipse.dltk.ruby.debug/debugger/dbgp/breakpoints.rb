###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'thread'

module XoredDebugger

    #
    # N.B. Stores absolute path names
    #
    class Breakpoints
    private        
        @@id = 0

        def Breakpoints.next_id
            @@id += 1
        end

    public 
        def initialize
            @mutex = Mutex.new

            @line_bps = {}
            @exception_bps = {}
        end

        def line_break?(file, line)
            @mutex.synchronize do
                for bp in @line_bps.values do
                    if bp[:state] and bp[:line] == line and bp[:file] == file
                        return true
                    end
                end
                false
            end
        end

        def exception_break?(exception)
            @mutex.synchronize do
            end
        end

        def set_line_bpt(file, line, state)
            @mutex.synchronize do
                id = Breakpoints.next_id
                @line_bps[id] = {
                    :id    => id,
                    :line  => line,
                    :file  => file,
                    :state => state
                }
                id
            end
        end

        def set_exception_bpt(exception, state)
            @mutex.synchronize do
                id = Breakpoints.next_id
                # TODO:
                id
            end
        end
               
        def set_state(id, state)
            @mutex.synchronize do
                @line_bps[id][:state] = state
            end
        end

        def remove(id)
            @mutex.synchronize do
                (not @line_bps.delete(id).nil?) or (not @exception_bps.delete(id).nil?)
            end
        end

    end # class Breakpoints

end # module XoredDebugger

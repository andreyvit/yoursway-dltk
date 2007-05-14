###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger

    # DBGP stack model, depth = N, zero-index is for top-level frame
    #====================================
    # N - 1 |  ...  | 1 | 0 |   <=  push
    #====================================

    class VirtualStack
        def initialize
            @levels = []
        end

        def update(binding, file, line, where)
            unless empty?
                pop
            end

            push(binding, file, line, where)
        end

        def push(binding, file, line, where)
            level = { 'binding' => binding, 
                      'file'    => file, 
                      'line'    => line,
                      'where'   => where }

            @levels.push(level)
        end

        def pop
            @levels.pop
        end

        def get(index)
            @levels[depth - 1 - index]
        end
        
        def eval(text, index = 0)
            Kernel.eval(text, get(index)['binding'])
        end

        def depth
            @levels.length
        end

        def empty?
            @levels.empty?
        end

        def to_a
            @levels
        end

    end # VirtualStack

end # module
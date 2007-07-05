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
    class Stack
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
            level = { :binding => binding, 
                      :file    => file, 
                      :line    => line,
                      :where   => where }

            @levels.push(level)
        end

        def pop
            @levels.pop
        end

        def [] (index)
            @levels[depth - 1 - index]
        end
        
        def eval(text, index = 0)	
            level = self[index]
			Kernel.eval(text, level[:binding], level[:file], level[:line])
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

    end # Stack
	
	class FullStackManager
		def initialize()
			@stack = Stack.new
		end
		
		attr_reader :stack
			
		# Evaluation
		def eval(text, index = 0)
			@stack.eval(text, index)
		end
	
		# Stack depth
		def depth
			@stack.depth
		end
		
		# Stack level
		def [](index)
			level = @stack[index]
			StackLevelInfo.new(level[:file], level[:line], level[:where])
		end
	end # class FullStackManager

end # module
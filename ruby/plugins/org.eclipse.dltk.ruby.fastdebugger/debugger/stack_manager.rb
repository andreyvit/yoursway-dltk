###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'dbgp/managers/stack'
require 'dbgp/logger'

module XoredDebugger
    class StackManager < AbstractStackManager
        include Logger
        
        def initialize(context)
            @context = context
        end
            
        # Evaluation
        def eval(text, index)
            Kernel.eval(text, @context.frame_binding(index))
        end
    
        # Stack depth
        def depth
            @context.stack_size
        end
        
        # Stack level
        def [](index)
            file = @context.frame_file(index)
            line = @context.frame_line(index)
			
			c = @context.frame_self(index).class.name
			m = @context.frame_method(index).to_s
			
            StackLevelInfo.new(file, line, sprintf('%s::%s', c, m))
        end
    end # class StackManager
end # module
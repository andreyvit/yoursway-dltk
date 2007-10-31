###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'dbgp/managers/breakpoint'
require 'dbgp/command_handler'

require 'stack_manager'
						 
module XoredDebugger

    class RubyThread < CommandHandler
		# Events
        def created
            log('Thread created event!')
            @context.suspend                    
        end  
        

        def initialize(debugger, thread, io_manager, key, script) 
            @waitDepth = -1
            @stack_manager = FullStackManager.new            
            super(debugger, thread, io_manager, key, script)           
        end                
        
        def stack_manager
            @stack_manager
        end    
        
        def step_out
            @context.stop_frame = stack_manager.depth - 1
            @context.resume
            nil
        end                  
    end
end # module
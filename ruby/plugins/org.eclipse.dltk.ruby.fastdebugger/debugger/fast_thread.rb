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

require 'monitor'
                             
module XoredDebugger
    
    class FastRubyThread < CommandHandler
        # Event methods   
        def created
            log('Thread created event!')                    
        end     
        
        # Constructor
        def initialize(debugger, thread, io_manager, key, script)
            super(debugger, thread, io_manager, key, script)         
            @stack_manager = StackManager.new(@context)                        
            @skipBreakpoint = false             
        end
        
        def step_out
            @context.stop_frame = 0
            @context.resume
            nil
        end
        def stack_manager
            @stack_manager
        end                     
    end # class FastRubyThread
end # module XoredDebugger
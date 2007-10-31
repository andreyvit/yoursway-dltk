###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################    

require 'abstract_runner'
require 'simple_debugger'
                          
module XoredDebugger
    class SimpleRunner < AbstractRunner
        def create_debugger
            RubyDebugger.new()
        end
    end # class Runner

    SimpleRunner.new.run

end # XoredDebugger
###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
###############################################################################

require 'abstract_runner'
                       
module XoredDebugger
    class FastRunner < AbstractRunner
        def create_debugger
            require 'fast_debugger'
            FastRubyDebugger.new() 
        end
        
        def load_libraries()
            super
            require 'rubygems'
            require 'ruby-debug-base.rb'
        end        
    end # class FastRunner

    FastRunner.new.run
end # XoredDebugger
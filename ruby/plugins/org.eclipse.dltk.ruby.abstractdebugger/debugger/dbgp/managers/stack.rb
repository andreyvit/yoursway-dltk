###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger
    # StackManager interface
    class AbstractStackManager
	    # Evaluation
	    def eval(text, index)
	        raise NotImplementedError.new('You MUST implement this method in ancessors')
	    end
	
	    # Stack depth
	    def depth
	        raise NotImplementedError.new('You MUST implement this method in ancessors')
	    end
	    
	    # Stack level
	    def [](index)
	        raise NotImplementedError.new('You MUST implement this method in ancessors')
	    end
    end # class StackManager
end # module XoredDebugger
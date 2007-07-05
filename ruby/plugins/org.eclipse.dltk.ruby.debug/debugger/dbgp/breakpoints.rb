###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger    
    class BreakpointInfo
        COND_GREATER_OR_EQUALS = '>='
        COND_EQUALS            = '=='   
        COND_MULTIPLE          = '%'
    
        def initialize(state, temporary, expression, hit_value, hit_condition)
            @state = state
            @temporary = temporary
            @expression = expression
            @hit_value = hit_value
            @hit_condition = hit_condition
        end
        
        def update(info)
            @state         = info.state
            @temporary     = info.temporary
            @expression    = info.expression
            @hit_value     = info.hit_value
            @hit_condition = info.hit_condition             
        end
        
        def to_s
            "Breakpoint TODO"
        end
            
        attr_reader :state
        attr_reader :temporary
        attr_reader :expression
        attr_reader :hit_value, :hit_condition
        
        # Optional information
        attr_accessor :breakpoint_id
        attr_accessor :hit_count        
    end # class BreakpointInfo
    
    class LineBreakpointInfo < BreakpointInfo
        def initialize(file, line, state = true, temporary = false, expression = nil, hit_value = 1, hit_condition = COND_GREATER_OR_EQUALS)
            super(state, temporary, expression, hit_value, hit_condition)
            @file = file
            @line = line            
        end
        
        attr_reader :file, :line
    end # class LineBreakpointInfo
    
    class ExceptionBreakpointInfo < BreakpointInfo
        def initialize(exception, state = true, temporary = false, expression = nil, hit_value = 1, hit_condition = COND_GREATER_OR_EQUALS)
            super(state, temporary, expression, hit_value, hit_condition)
            @exception = exception  
        end
        
        attr_reader :exception
    end # class ExceptionBreakpointInfo
end # module XoredDebugger
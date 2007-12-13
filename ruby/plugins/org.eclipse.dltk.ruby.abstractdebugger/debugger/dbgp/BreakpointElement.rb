require 'dbgp/XmlElement'
require 'debugger/BreakpointContracts'

module XoredDebugger    
    class BreakpointElement < XmlElement
        def initialize(bp)
            super('breakpoint')
            add_attribute('id', bp.breakpoint_id)
            add_attribute('state', bp.state)
            add_attribute('hit_value',bp.hit_value)
            add_attribute('hit_condition',bp.hit_condition)
            add_attribute('hit_count',bp.hit_count)
            set_data(bp.expression, true)
            
            # Type specific information
            # TODO: Add other brealpoin types handling
            if bp.is_a? LineBreakpointContract
                add_attribute('type', 'line')
                add_attribute('filename', bp.filename)
                add_attribute('lineno', bp.lineno)
            elsif bp.is_a? ExceptionBreakpointContract
                add_attribute('type', 'exception')
                add_attribute('exception', bp.exception.name)
            end                        
        end
    end
end

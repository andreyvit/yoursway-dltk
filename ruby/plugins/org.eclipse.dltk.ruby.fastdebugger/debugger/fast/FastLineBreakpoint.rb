require 'debugger/BreakpointContracts'

module XoredDebugger	
	class FastLineBreakpoint
	     include LineBreakpointContract
	     attr_reader :breakpoint_id
	     attr_reader :temporary            
	     attr_accessor :state                    
	     attr_reader :raw_bp
	     
	     def initialize(id, raw_bp, temporary)
	         @breakpoint_id = id
	         @temporary = temporary
	         @raw_bp = raw_bp
	         @state = true                               
	     end
	     
	     def filename
	         @raw_bp.source
	     end
	     
	     def lineno
	         @raw_bp.pos         
	     end
	
	     def lineno=(line)
	         @raw_bp.pos = line
	         nil         
	     end
	     
	     def expression
	         @raw_bp.expr
	     end
	
	     def expression=(expr)
	         if (expr.nil?)
	             expr = ''
	         end
	         @raw_bp.expr = expr
	         nil            
	     end
	     
	     def hit_value
	         @raw_bp.hit_value
	     end
	
	     def hit_value=(value)
	         @raw_bp.hit_value = value
	     end
	     
	     def hit_condition
	         if @raw_bp.hit_condition == :ge
	             return COND_GREATER_OR_EQUALS
	         elsif @raw_bp.hit_condition == :eq
                 COND_EQUALS
	         elsif @raw_bp.hit_condition == :mod
                 COND_MULTIPLE
             else
                 nil
             end
	     end
	
	     def hit_condition=(condition)
	         if condition == COND_GREATER_OR_EQUALS
                 @raw_bp.hit_condition = :ge
             elsif condition == COND_EQUALS
                 @raw_bp.hit_condition = :eq
             elsif condition == COND_MULTIPLE
                 @raw_bp.hit_condition = :mod
             elsif condition.nil? || condition.empty?
                 @raw_bp.hit_condition = nil
             else 
                 raise ArgumentError, 'Invalid condition'
             end          
	     end
	     
	     def hit_count
	         @raw_bp.hit_count
	     end                    
	 end
end


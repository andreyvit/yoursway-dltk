#
# class Breakpoints
#

class Breakpoints

private
	@@id = 0

	def Breakpoints.next_id
		@@id += 1
		@@id
	end

public
	def initialize
		@line_bps = {}
	end

	def break?(file, line)
		for bp in @line_bps.values do
			if bp['line'] == line and bp['file'] == file
				return true
			end
		end

		false
	end

	def set_line(file, line)
		id = Breakpoints.next_id
		@line_bps[id] = {
			'id'   => id,
			'line' => line,
			'file' => file
		}
		id
	end

	def set_line_conditional(file, line, condition)

	end

	def set_call(function_name)
	end

	def set_return(function_name)
	end

	def set_exection(exception_name)
	end  	

	def remove(id)
		@line_bps.delete(id)
	end
end

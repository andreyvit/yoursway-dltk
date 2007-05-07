###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

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
			if bp['line'] == line and bp['file'] == file and  bp['state']
				return true
			end
		end

		false
	end

	def set_line(file, line, state)
		id = Breakpoints.next_id
		@line_bps[id] = {
			'id'      => id,
			'line'    => line,
			'file'    => file,
			'state' => state
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

	def update(id, state)
		@line_bps[id]['state'] = state
	end
end

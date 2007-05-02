#
# class VirtualStack
#

class VirtualStack
	def initialize
		@levels = []
	end

	def update(binding, file, line)
		pop
		push(binding, file, line)
	end

	def push(binding, file, line)
		level = { 'binding' => binding, 
				  'file'    => file, 
				  'line'    => line }
		@levels.push(level)
	end

	def pop
		@levels.pop
	end

	def get(index)
		@levels[index]
	end

	def eval(text)
		Kernel.eval(text, @levels.last['binding'])
	end

	def depth
		@levels.length
	end

	def empty?
		@levels.empty?
	end

	def to_a
		@levels
	end
end
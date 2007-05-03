#
#  class FileLogger
#

class FileLogger
	def initialize(filename)
		@f = File.open(filename, 'w')
	end

	def puts(str)
		@f.puts(str)
		@f.flush
#		Kernel.puts(str)
	end

	def close
		@f.close
	end
end

#
# class StdoutCapturer
#

class StdoutCapturer
	def initialize()
		@output = ""
		@saved_stdout = $stdout
	end

	def write(s)
		@output += s
	end

	def enable
		self.reset
		$stdout = self
	end

	def disable
		$stdout = @saved_stdout
	end

	def output
		@output
	end

	def reset
		@output = ""
	end
end
###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

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
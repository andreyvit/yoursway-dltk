#
# class TestIO
#

class TestIO
	def initialize(logger = nil)
		@logger = logger
		@n = 0

#		@commands = [
#			'feature_get -n language_name',        		
#			'step_into', 
#			'context_get',
#			'context_names',
#			'context_get',
#			'stack_depth',
#			'breakpoint_set -t line -f test.rb -n 2'
#		]

#		@commands = [
#			'status',	
#			'context_get',
#			'stdin -c 1', # redirect stdin
#			'feature_get -n supports_async',
#			'breakpoint_set -t line -f file:///C:%5Cprogramming%5Ceclipse%5Cruntime-New_configuration%5CRubyTest%5Ctest.rb -n 2',
#			'status',
#			'stack_depth',
#			'step_into',
#			'stack_get',
#			'stack_get -d 0',
#			'context_get',
#			'run',
#			'run'
#		]

		@commands = [
			'status',
			'step_over',
			'step_over',
			'step_over',
			'step_over',
			'step_over',
			'step_over',
			'stack_get',
			'context_get',
			'property_get -n tank -k 23455',
			'step_over',
			'status',
			'run'
		]


	end

	def receive
		cmd = if @n < @commands.length			
			command = @commands[@n] + " -i #@n"
			@n += 1
			command
		else
			"dummy"
		end
		
		unless @logger.nil?
			@logger.puts('<<< ' + command)
		end

		cmd
	end

	def send(response)
		unless @logger.nil?
			@logger.puts('>>> ' + response)
		end
	end

	def close
		puts "Closing..."
	end
end

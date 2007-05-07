###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'dbgp/breakpoints'
require 'dbgp/features'
require 'dbgp/stack'
require 'dbgp/capturer'
require 'dbgp/printer'
require 'dbgp/command'
require 'dbgp/logger'
require 'dbgp/socket_io'
require 'dbgp/test_io'
require 'dbgp/properties.rb'

require 'cgi'

def decode_uri(uri)
	CGI.unescape(uri).sub!('file:///', '')
end

SCRIPT_LINES__ = {} unless defined? SCRIPT_LINES__

class States
	#starting: State prior to execution of any code
	#stopping: State after completion of code execution. This typically happens at the end of code execution, allowing the IDE to further interact with the debugger engine (for example, to collect performance data, or use other extended commands).
	#stopped: IDE is detached from process, no further interaction is possible.
	#running: code is currently executing. Note that this state would only be seen with async support turned on, otherwise the typical state during IDE/debugger interaction would be 'break'
	#break: code execution is paused, for whatever reason (see below), and the IDE/debugger can pass information back and forth.

	def initialize
		@state = 'starting'
	end

	def break
		@state = 'break'
	end
   
	def run
		@state = 'running'
	end

	def stop
		@state = 'stopping'
	end
	    
	def to_s
		@state
	end
end

class Debugger
	def initialize(io, key, logger = nil)
		@io = io
		@key = key

		@logger = logger

		@capturer = StdoutCapturer.new
		@features = Features.new
	
		@stack = VirtualStack.new
		@stack.push(nil, nil, nil)

		@breakpoints = Breakpoints.new
		@states = States.new
		@printer = Printer.new

		@waitDepth = -1
        @thread = Thread.current
		@last_command = nil

		@stopped = false
		@detached = false

		# Start session		
		send('init', init)
	end

private
	def log(str)
		unless @logger.nil?
			@logger.puts(str)
		end
	end

	def Debugger.makeURI(file)
		'file:///' + File.join(Dir.pwd, file)
	end

	def receive
		@io.receive
	end

	def send(name, data)
		@io.send(@printer.print(name, data))
	end
                               
	def line_at(file, line)
		lines = SCRIPT_LINES__[file]
		if lines
			return "\n" if lines == true
			line = lines[line-1]
			return "\n" unless line
			return line
	    end	
		return "\n"
	end
    
	def printFile()
		#puts "Printing file: #{@file}..."
		#puts SCRIPT_LINES__
		#puts "=========================="

		lines = SCRIPT_LINES__[@file]

     	delta = 5;

		from = [1, @line - delta].max
		to = [@line + delta, lines.length].min

		#print("Range from #{from} to #{to}")
		#print("== Line: #{line} from #{file} (depth: #{@@depth}) ==\n")
		i = from;
		while i <= to
			str = ''
			if i == @line
				str = '=>'
			elsif !@breakpoints[i].nil?
				str = '*'
			end

			print("#{i}\t#{str}\t#{lines[i-1]}")
			i += 1;
		end
		print("\n")
	end

	# init
	def init
		{ 'app_id'   => 'test',
		  'ide_key'  => @key,
		  'session'  => 'session',
		  'thread'   => 'xxx',
 		  'parent'   => '',
		  'file_uri' => Debugger.makeURI('test.rb') }
	end
                         
	# Status command
	def status
		{ 'status' => @states.to_s, 
		  'reason' => 'ok' }
	end

	# Feature commands
	def feature_get(name)
		log("feature_get: #{name}")

		{ 'name'      => name, 
		  'supported' => 1, 
		  'value'     => @features.get(name).to_s }
	end

	def feature_set(name, value)
		log("feature_set: #{name} = #{value}")

		@features.set(name, value) # Check types!!! (string or int)
        { 'name'    => name, 
		  'success' => 1 }
	end

	# Continuation commands
	def run
		@waitDepth = -2
		nil
	end

	def step_into
		@waitDepth = -1
		nil
	end

	def step_over
		@waitDepth = @stack.depth
		nil
	end

	def step_out
		@waitDepth = @stack.depth - 1
		nil
	end

	def stop
		#@stopped = true

		#{ 'status' => 'stopped',
		#  'reason' => 'ok' }
		shutdown
	end

	def detach
		# Should set special variable
		log('--> detach <--')
		nil
	end

	# Context
	def make_property(name, obj) # TODO: move!!!
		type = obj.class

		if type == Hash
			prepare_hash(name, obj)
		elsif type == Array
			prepare_array(name, obj)
		else
			prepare_object(name, obj)
		end
	end


	def context_names(depth)
	    { 'contexts' => [
			{'name' => 'Local',  'id' => 0 },
			{'name' => 'Global', 'id' => 1 },
			{'name' => 'Class',  'id' => 2 }
		 ] }
	end

	def context_get(depth, context_id)
		properties = []

		vars = @stack.eval('local_variables')
	
		vars.each { |var|
			real_var = @stack.eval(var)

			unless real_var.nil?
				properties << make_property(var, real_var)				
			end
		}	

		#for var in @stack.eval('instance_variables') do
		#	properties << make_property(var)
		#end

		#for var in @stack.eval('global_variables') do
		#	properties << make_property(var)
		#end

		{ 'properties' => properties, 
		  'context_id' => context_id }
	end

	# Property commands
	def property_get(name, key)
		obj = ObjectSpace._id2ref(key)
		if obj.nil?
			obj = 'Invalid key :('
		end
	
		{ 'property' => make_property(name, obj) }
	end


	# Breakpoint commands
	def set_line_breakpoint(file, line, state)
		log("Setting line breakpoint, file: #{file}; line: #{line}; state: #{state}")

		id = @breakpoints.set_line(file, line, state)

		{ 'state' => state, 
		  'breakpoint_id' => id }
	end

	def breakpoint_remove(id)
		@breakpoints.remove(id)
		{}
	end

	def breakpoint_update(id, state)
		@breakpoints.update(id, state)
		{}
	end

	# Stack commands
	def stack_depth
		{'depth' => @stack.depth }
	end

	def stack_get(depth = nil)
		l = 0
		levels = []
        
		@stack.to_a.reverse.each { |level|
			levels << { 'level'    => l,
	 		 		    'type'     => 'source',
	  			 	    'filename' => 'file:///' + level['file'],
	    			    'lineno'   => level['line'],
		  	            'cmdbegin' => '0:0',
		    			'cmdend'   => '0:0' }
			l += 1

		}

		unless depth.nil?
			n = depth.to_i
			{ 'levels' => [levels[n]] }
		else

			{ 'levels' => levels }
		end		
	end
	
	
	# Context commands
	def stdin_data(data)
	end

	def stdin_configure(value)
		# 0 - disable
		# 1 - redirect

		if value == 0
			log("Disabling stdin")
		elsif value == 1
			log("Redirecting stdin")
		end

		{ 'success' => 1 }
	end

	def stdout_configure(value)
		# 0 - default
		# 1 - copy data
		# 2 - redirection

		log("Configure stdout: #{value}")

		{ 'success' => 1 }
	end

	def stderr_configure(value)
		# 0 - default
		# 1 - copy data
		# 2 - redirection

		log("Configure stderr: #{value}")

		{ 'success' => 1 }
	end

    def dispatch_command(command)
		log('Dispatching command: ' + command.name)

    	data = case command.name
    		# Status
    		when 'status': status

    		# FeatureCommands
    		when 'feature_get':
    			name = command.arg('-n')
    			feature_get(name)

    		when 'feature_set':
    			name = command.arg('-n')
    			value = command.arg('-v')
    			feature_set(name, value)

    		# Continuation commands
    		when 'run': run

    		when 'step_into': step_into

    		when 'step_over': step_over

    		when 'step_out': step_out

    		when 'stop': stop

    		when 'detach': detach

    		# Breakpoint commands
    		when 'breakpoint_set'
    			type = command.arg('-t')           # breakpoint type, see below for valid values [required]
				state = command.arg('-s') == 'enabled' ? true : false			

    			#state = command.arg('-s')         # breakpoint state [optional, defaults to "enabled"]    			
    			#function = command.arg('-m')      # function name [required for call or return breakpoint types]
    			#exception = command.arg('-x')     # exception name [required for exception breakpoint types]
    			#hit_value = command.arg('-h')     # hit value (hit_value) used with the hit condition to determine if should break; a value of zero indicates hit count processing is disabled for this breakpoint [optional, defaults to zero (i.e. disabled)]
    			#hit_condition = command.arg('-o') # hit condition string (hit_condition); see hit_condition documentation above; BTW 'o' stands for 'operator' [optional, defaults to '>=']
    			#is_temporary = command.arg('-r')  # Boolean value indicating if this breakpoint is temporary. [optional, defaults to false]
                 
				case type
				when 'line':
					file = File.expand_path(decode_uri(command.arg('-f')))

      				line = command.arg('-n').to_i
					set_line_breakpoint(file, line, state)
				end


    		when 'breakpoint_get'
    			# TODO:
				{}

    		when 'breakpoint_update'
				id = command.arg('-d').to_i
				state = command.arg('-s') == 'enabled' ? true : false
				breakpoint_update(id, state)
    		
    		when 'breakpoint_remove'
    			id = command.arg('-d').to_i
				breakpoint_remove(id)

    		when 'breakpoint_list'
    			# TODO:
				{}

    		# Context commands
    		when 'context_names'              
    			depth = command.arg_with_default('-d', @stack.depth - 1)
    			context_names(depth)

    		when 'context_get'
    			depth = command.arg_with_default('-d', @stack.depth - 1)
    			context_id = command.arg_with_default('-c', 0)
    			context_get(depth, context_id)

    		# Property commands
    		when 'property_get'
    			name = command.arg('-n')
    			key = command.arg('-k').to_i

				property_get(name, key)
    			
    		when 'property_set'

    		when 'property_value'
     	
    		# Stack commands
    		when 'stack_get'
				depth = command.arg('-d')	
				stack_get(depth)
				
				
    		when 'stack_depth': stack_depth

    		# Extended commands
    		when 'stdin'
    			value = command.arg('-c')
    			unless value.nil?
					stdin_configure(value.to_i)
    			else
					stdin_data(command.data)
    			end

			when 'stdout'
				value = command.arg('-c').to_i
				stdout_configure(value)

			when 'stderr'
				value = command.arg('-c').to_i
				stderr_configure(value)
    	end

		unless data.nil?
	    	data['id'] = command.arg('-i')
		end

		log('OK')

		data
    end

public
	def shutdown
		map = { 'status' => 'stopped',
				'reason' =>	'ok',
				'id'     => @last_command.arg('-i') }

		send(@last_command.name, map)

		exit
	end

   	def trace_func(event, file, line, id, binding, klass)
		if @stopped
			set_trace_func nil
			@io.close
			exit
		end

   		if @detached or @thread != Thread.current
   			return
   		end
    
   		@file =  File.expand_path(file)  #File.expand_path(File.join(Dir.pwd, file))
   		@line =  line
		@binding = binding

   		case event
   			when 'line'
				@stack.update(@binding, @file, @line)

   				@capturer.disable

   				#print "==== Captured ======\n"
   				#print @capturer.output
   				#print "====================\n"
    
   				if (@waitDepth == -1 or
				    @waitDepth >= @stack.depth or
				    @breakpoints.break?(file, line))
                                        
					# == Information ==
   					log("==>>>>>>>> Line: #{@line} from #{@file} (depth: #{@stack.depth}, waitDepth: #{@waitDepth}) <<<<<<<<==")

					unless @last_command.nil?
						map = { 'status' => 'break',
							    'reason' =>	'ok',
								'id'     => @last_command.arg('-i') }

						send(@last_command.name, map)
					else
						#report status starting
					end

   					# Command handling loop
   					loop do
   						@last_command = Command.new(receive)
						
						data = dispatch_command(@last_command)
	
						unless(data.nil?)
							send(@last_command.name, data)
						else
							break
	 					end
					end
    
   					@capturer.enable
   				end
   
   			when 'call'
				@stack.push(@binding, @file, @line)
    
   			when 'return'
   				@stack.pop
    
   			when 'class'
   				@in_class = true
    
   			when 'end'
   				@in_class = false
    
   			when 'raise'
   				# Exception handling
   		end
   	end
end

logger = nil


log = ENV['DBGP_RUBY_LOG']
unless log.nil?
	#'C:/dbgp_log.txt'
	logger = FileLogger.new(log)
end

begin
	host   = ENV['DBGP_RUBY_HOST']
	port   = ENV['DBGP_RUBY_PORT'].to_i
	key    = ENV['DBGP_RUBY_KEY']
	script = ENV['DBGP_RUBY_SCRIPT']  

	if (host.nil? or port == 0 or key.nil? or script.nil?)
		logger.puts('Invalid debugger params')
	else
		logger.puts($:)
		logger.puts('host: ' + host)
		logger.puts('port: ' + port.to_s)
		logger.puts('key: ' + key)
		logger.puts('script: ' + script)

		logger.puts('Creating IO...')
		@io = SocketIO.new(host, port, logger)
		#@io = TestIO.new(logger)
		logger.puts('Creating Core...')
		@debugger = Debugger.new(@io, key, logger)

		logger.puts('Setting tracing func...')
		set_trace_func proc { |event, file, line, id, binding, klass, *rest|
			@debugger.trace_func(event, file, line, id, binding, klass)
		}

		load script

		set_trace_func nil

		@debugger.shutdown
	end

rescue Exception
	logger.puts('Error:')
	logger.puts($!)
	logger.puts('Message:')
	logger.puts($!.message)
	logger.puts('Backtrace:')
	logger.puts($!.backtrace.join("\n"))
ensure
	logger.close
end


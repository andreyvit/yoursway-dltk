###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'pathname'

THIS_PATH = File.dirname(__FILE__)

require THIS_PATH + '/properties'
require THIS_PATH + '/breakpoints'
require THIS_PATH + '/stack'
require THIS_PATH + '/logger'

#
# uri -> path
#
def uri_to_path(uri)
    # File.expand_path
    CGI.unescape(uri).sub!('file:///', '')
end

#
# path -> uri
#
def path_to_uri(path)
   'file:///' + CGI.escape(path).gsub('+', '%20')
end

#
# Thread label
#
def get_thread_label(thread)	
	is_main = thread == Thread.main
	sprintf("Thread %s id=%d, priority=%d", is_main ? '(main)' : '', thread.object_id, thread.priority)
end

def normalize_path(path)
	Pathname.new(path).expand_path.to_s
end

module XoredDebugger
	class CommandHandler
		include Logger
		
		def initialize(debugger, thread, io_manager, key, script)
            @debugger = debugger
            @thread = thread
            @io_manager = io_manager
            @key = key
            @script = script		                
			@stop_sent = false
            @command = nil            
            @context = debugger.thread_context(thread)
            
            @command_handler = debugger.create_debug_thread do
                log("New control thread started: " + Thread.current.inspect)
                io_manager.send('init', init('app_test_id', @key, get_thread_label(@thread), @script))
                catch (:done) do                 
                    loop do
                        begin
                            command_loop
                        rescue Exception
                            log('Exception in command loop:')
                            log("\tMessage: " + $!.message)
                            log("\tBacktrace: " + $!.backtrace.join("\n"))                            
                        end
                    end
                end
                log("Exiting control thread: " + Thread.current.inspect)
#                
#                unless @context.dead?
#                    # if thread is still alive and suspended then resume thread
#                    log("Resuming thread without control")
#                    if @context.suspended?
#                        @context.resume
#                    end                     
#                end
            end            
		end
  
        # Init
        def init(app_id, ide_key, thread, file_uri)
            { :app_id   => app_id,
              :ide_key  => ide_key,
              :session  => 'session',
              :thread   => thread,
              :parent   => '',
              :file_uri => file_uri }
        end

        # Status command
        def status
            { :status => 'xxx_xxx',
              :reason => 'ok' }
        end

        # Feature commands
        def feature_get(name)
            log("feature_get: #{name}")

            supported = feature_manager.supported?(name)

            { :name      => name,
              :supported => supported,
              :value     => supported ? feature_manager.get(name).to_s : nil }
        end

        def feature_set(name, value)
            log("feature_set: #{name} = #{value}")

            feature_manager.set(name, value) # Check types!!! (string or int)
            { :name    => name,
              :success => 1 }
        end

        # Context commands
        LOCAL_CONTEXT_ID  = 0
        GLOBAL_CONTEXT_ID = 1
        CLASS_CONTEXT_ID  = 2

        def context_names(depth)
            { :contexts => [
                { :name => 'Local',  :id => LOCAL_CONTEXT_ID },
                { :name => 'Global', :id => GLOBAL_CONTEXT_ID },
                { :name => 'Class',  :id => CLASS_CONTEXT_ID }
             ] }
        end

        def context_get(depth, context_id)
            contexts = [LOCAL_CONTEXT_ID, GLOBAL_CONTEXT_ID, CLASS_CONTEXT_ID]
            unless context_id.nil?
                contexts = [context_id]
            end

            def make_props(exp, d)
                vars = stack_manager.eval(exp, d)

                props = []
                vars.each { |var|
                    real_var = stack_manager.eval(var, d)
                    props << make_property(var, real_var)
                }
                props
            end

            properties = []

            # Local variables
            if contexts.include?(LOCAL_CONTEXT_ID)
                properties += make_props('local_variables', depth)

                # TODO: correct this later
                self_var = stack_manager.eval('self', depth)
                unless self_var.nil?
                    properties << make_property('self', self_var)
                end
            end

            # Global variables
            if contexts.include?(GLOBAL_CONTEXT_ID)
                properties += make_props('global_variables', depth)
            end

            # Class variables
            if contexts.include?(CLASS_CONTEXT_ID)
                properties += make_props('instance_variables', depth)
            end

            { :properties => properties,
              :context_id => context_id }
        end

        # Property commands
        def property_get(name, depth, key)
            # TODO: name, depth usage
            obj = 'Error :('
            begin
                obj = ObjectSpace._id2ref(key)
                if obj.nil?
                    obj = 'Object is nil'
                end
            rescue Exception
            end

            { :property => make_property(name, obj) }
        end

        def property_set(name, depth, value)
            success = true
            begin
                command = "#{name} = #{value.to_s}"
                stack_manager.eval(command, depth)
            rescue Exception
                success = false
            end

            { :success => success }
        end

        def property_value
            #TODO:
            {}
        end

        # Breakpoint commands
        def breakpoint_set(info)
            log('Setting breakpoint, info: ' + info.inspect)

            id = breakpoint_manager.add(info)

            { :state         => info.state,
              :breakpoint_id => id }
        end

        def breakpoint_get(id)
            info = breakpoint_manager[id]

            breakpoint = {
                :breakpoint_id => info.breakpoint_id, # Specially added by breakpoint manager
                :state         => info.state,
                :hit_count     => info.hit_count,     # Specially added by breakpoint manager
                :hit_value     => info.hit_value,
                :hit_condition => info.hit_condition,
            }

            # Type specific inforamtion
            type = info.class
            if type == LineBreakpointInfo
                breakpoint[:type]     = 'line'
                breakpoint[:filename] = info.file
                breakpoint[:lineno]   = info.line
            end

            # Return value
            { :breakpoint => breakpoint }
        end

        def breakpoint_list
            # TODO:
            {}
        end

        def breakpoint_update(id, info)
            breakpoint_manager.update(id, info)

            {}
        end

        def breakpoint_remove(id)
            breakpoint_manager.remove(id)

            {}
        end

        # Stack commands
        def stack_depth
            { :depth => stack_manager.depth }
        end

        def stack_get(depth = nil)
            levels = []

            stack_manager.depth.times { |i|
                level = stack_manager[i]
				
				where = level.method
				if where.nil?
					where = source_manager.line_at(level.file, level.line)
				end
				
                levels << { :level    => i,
                            :type     => 'source',
                            :filename => path_to_uri(normalize_path(level.file)),
                            :lineno   => level.line,
                            :cmdbegin => '0:0',
                            :cmdend   => '0:0',
                            :where    => where }
            }

            unless depth.nil?
                n = depth.to_i
                { :levels => [levels[n]] }
            else
                { :levels => levels }
            end
        end

        # Context commands
        def stdin_data(data)
            # TODO:
        end

        def stdin_configure(value)
            # 0 - disable
            # 1 - redirect

            # TODO:
            if value == 0
                log("Disabling stdin")
            elsif value == 1
                log("Redirecting stdin")
            end

            { :success => true }
        end

        def stdout_configure(value)
            # 0 - default
            # 1 - copy data
            # 2 - redirection

            # TODO:
            log("Configure stdout: #{value}")

            { :success => true }
        end

        def stderr_configure(value)
            # 0 - default
            # 1 - copy data
            # 2 - redirection

            # TODO:
            log("Configure stderr: #{value}")

            { :success => true }
        end

        def eval_handler(expression, depth)
            log('Evaluating expression: ' + expression)

            success = true
            property = nil
            begin
                property = make_property(expression, stack_manager.eval(expression, depth))
            rescue Exception
                success = false
            end

            { :success  => success,
              :property => property }
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
                when 'break': break_cmd

                # Breakpoint commands
                when 'breakpoint_set'
                    type          = command.arg('-t')
					state         = command.arg_with_default('-s', 'enabled') == 'enabled' ? true : false
                    temporary     = command.arg_with_default('-r', false)
                    hit_value     = command.arg_with_default('-h', 1).to_i
                    hit_condition = command.arg_with_default('-o', '>=')
                    expression    = command.data

                    info = case type
                        when 'line'
                            file = File.expand_path(uri_to_path(command.arg('-f')))
                            line = command.arg('-n').to_i

                            log("\tfile: " + file.to_s)
                            log("\tline: " + line.to_s)

                            LineBreakpointInfo.new(file, line, state, temporary, expression, hit_value, hit_condition)

                        when 'call'
                            function = command.arg('-m')
                            # TODO:
                            nil

                        when 'return'
                            function = command.arg('-m')
                            # TODO:
                            nil

                        when 'exception'
                            exception = command.arg('-x')

                            log("\texception: " + file.to_s)

                            ExceptionBreakpointInfo.new(exception, state, temporary, expression, hit_value, hit_condition)

                        when 'conditional'
                            # TODO:
                            nil
							
                        when 'whatch'
                            # TODO:
                            nil
                    end

                    log('Breakpoint info: ' + info.to_s)

                    unless info.nil?
                        breakpoint_set(info)
                    end

                when 'breakpoint_get'
                    id = command.arg('-d').to_i
                    breakpoint_get(id)

                when 'breakpoint_update'
                    id = command.arg('-d').to_i

					state         = command.arg_with_default('-s', 'enabled') == 'enabled' ? true : false
                    temporary     = command.arg_with_default('-r', false)
                    hit_value     = command.arg_with_default('-h', 1).to_i
                    hit_condition = command.arg_with_default('-o', '>=')
                    expression    = command.data

                    info = BreakpointInfo.new(state, temporary, expression, hit_value, hit_condition)

                    breakpoint_update(id, info)

                when 'breakpoint_remove'
                    id = command.arg('-d').to_i
                    breakpoint_remove(id)

                when 'breakpoint_list'
                    breakpoint_list

                # Context commands
                when 'context_names'
                    depth = command.arg_with_default('-d', '0').to_i
                    context_names(depth)

                when 'context_get'
                    depth = command.arg_with_default('-d', '0').to_i
                    context_id = command.arg_with_default('-c', '0').to_i
                    context_get(depth, context_id)

                # Property commands
                when 'property_get'
                    name = command.arg('-n')
                    depth =  command.arg_with_default('-d', '0').to_i
                    key = command.arg('-k').to_i
                    property_get(name, depth, key)

                when 'property_set'
                    name = command.arg('-n')
                    depth = command.arg_with_default('-d', '0').to_i
                    value = command.data
                    property_set(name, depth, value)

                when 'property_value'
                    property_value

                # Stack commands
                when 'stack_get'
                    depth = command.arg('-d')
                    stack_get(depth)

                when 'stack_depth'
                    stack_depth

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

                when 'eval'
                    expression = command.data
					depth = command.arg_with_default('-d', '0').to_i
                    eval_handler(expression, depth)
            end

            unless data.nil?
                data[:id] = command.arg('-i')
            end

            #log('Dispatch completed, data: ' + data.inspect)

            data
        end
		
        
        # Debugger ineraction       
        def send_break
            unless @last_continuation_command.nil?
                log('Sending responce to continuation command...')
                map = { :status => 'break',
                        :reason => 'ok',
                        :id     => @last_continuation_command.arg('-i') }

                io_manager.send(@last_continuation_command.name, map)
                @last_continuation_command = nil
            end
        end
        
        def send_stopped
            log(@last_continuation_command.inspect)
            unless @last_continuation_command.nil?
                map = { :status => 'stopped',
                        :reason => 'ok',
                        :id     => @last_continuation_command.arg('-i') }

	            io_manager.send(@last_continuation_command.name, map)
                @last_continuation_command = nil                    
            end          
        end        
        
        # Continuation commands
        def run
            @context.resume
            nil
        end
 
        def step_into
            @context.step(1)
            @context.resume
            nil
        end

        def step_over
            @context.step_over(1)
            @context.resume
            nil
        end

        def stop
            @context.interrupt
            nil
        end

        def detach
            nil
        end

        def break_cmd
            @context.suspend
            send_break
            # responce to break command
            { :success => 1 }
        end        
                
        
		# Terminate function
        def terminate
            log('Thread termination: ' + @thread.inspect)
            log("\tMain?: " + (@thread == Thread.main).to_s)
                        
            send_stopped
            Thread.kill(@thread)
        end               
        
        
        def terminated
            log("Thread 'terminated' event: " + @thread.inspect)
            send_stopped
            @io_manager.close            
        end                       
        
        def command_loop
            loop do
                log('Waiting command...')
                @command = Command.new(io_manager.receive)
                
                if ['run', 'step_into', 'step_over', 'step_out'].include?(@command.name)
                    @last_continuation_command = @command
                end
                
                log('Dispatching command...')
                data = dispatch_command(@command)

                if data.nil?
                    break
                else
                    io_manager.send(@command.name, data)
                end         
            end
        end  
        
		# Managers
        def capture_manager
            @debugger.capture_manager
        end
    
        def breakpoint_manager
            @debugger.breakpoint_manager
        end

        def feature_manager
            @debugger.feature_manager
        end
        
        def source_manager
            @debugger.source_manager
        end
        
        def stack_manager
            raise NotImplementedError.new('You MUST implement this method in ancessors')
        end
        
        def io_manager
            @io_manager
        end       
        
        
        
        def at_breakpoint(context, breakpoint)
            log(sprintf('=> at_breakpoint: %s:%s', breakpoint.file, breakpoint.line.to_s))                        
            if breakpoint.state
                @skipBreakpoint = false
            else
                @skipBreakpoint = true
            end         
        end
    
        def at_catchpoint(context, excpt)
        end
    
        def at_tracing(context, file, line)
        end

        def at_line(context, file, line)
            log(sprintf('=> at_line: %s: %d', file, line))
            unless context.stop_reason == :breakpoint && @skipBreakpoint
                @context.suspend
                send_break
            end
        end               
    end # class CommandHandler
end # module XoredDebugger
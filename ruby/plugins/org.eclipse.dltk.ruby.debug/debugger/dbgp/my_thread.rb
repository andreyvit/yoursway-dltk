###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'dbgp/stack'
require 'dbgp/features'
require 'dbgp/properties'


def decode_uri(uri)
    CGI.unescape(uri).sub!('file:///', '')
end

SCRIPT_LINES__ = {} unless defined? SCRIPT_LINES__

module XoredDebugger

    class States
        #starting: State prior to execution of any code
        #stopping: State after completion of code execution. This typically happens at the end of code execution, allowing the IDE to further interact with the debugger engine (for example, to collect performance data, or use other extended commands).
        #stopped: IDE is detached from process, no further interaction is possible.
        #running: code is currently executing. Note that this state would only be seen with async support turned on, otherwise the typical state during IDE/debugger interaction would be '\break'
        #\break: code execution is paused, for whatever reason (see below), and the IDE/debugger can pass information back and forth.

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
    end # class States


class VirtualThread
private
    def breakpoints
        @debugger.breakpoints
    end

    def logger
        @debugger.logger
    end

    def capturer
        @debugger.capturer
    end

    # Init
    def init
        { 'app_id'   => 'test',
          'ide_key'  => @key,
          'session'  => 'session',
          'thread'   => 'Thread with id = ' + @thread.object_id.to_s,
          'parent'   => '',
          'file_uri' => '' }
    end
                         
    # Status command
    def status
        { 'status' => @states.to_s,
          'reason' => 'ok' }
    end

    # Feature commands
    def feature_get(name)
        logger.puts("feature_get: #{name}")

        { 'name'      => name, 
          'supported' => 1, 
          'value'     => @features.get(name).to_s }
    end

    def feature_set(name, value)
        logger.puts("feature_set: #{name} = #{value}")

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
        terminate
        nil
    end

    def detach
        logger.puts('--> detach <--')
        nil
    end

    def break_cmd
        @waitDepth = -1
        nil        
    end

    # Context commands
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

        # Additional self property
        properties << make_property('self', @stack.eval('self'))  

        #for var in @stack.eval('instance_variables') do
        #   properties << make_property(var)
        #end

        #for var in @stack.eval('global_variables') do
        #   properties << make_property(var)
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
    def breakpoint_set_line(file, line, state, temporary)
        id = breakpoints.set_line_bpt(file, line, state)

        { 'state'         => state, 
          'breakpoint_id' => id }
    end
    
    def breakpoint_set_exception(exception, state, temporary)
        id = breakpoints.set_exception_bpt(exception, state)        

        { 'state'         => state, 
          'breakpoint_id' => id }        
    end

    def breakpoint_get
        # TODO:
        {}
    end

    def breakpoint_list
        # TODO:
        {}
    end

    def breakpoint_update(id, state)
        breakpoints.set_state(id, state)
        
        {}
    end

    def breakpoint_remove(id)
        logger.puts('===> Removing breakpoint with id: ' + id.to_s + ' ' + id.class.to_s)
        val = breakpoints.remove(id)
        logger.puts('===> Success: ' + val.to_s)
        
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
                        'cmdend'   => '0:0',
                        'where'    => level['where'] }
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
            logger.puts("Disabling stdin")
        elsif value == 1
            logger.puts("Redirecting stdin")
        end

        { 'success' => 1 }
    end

    def stdout_configure(value)
        # 0 - default
        # 1 - copy data
        # 2 - redirection

        logger.puts("Configure stdout: #{value}")

        { 'success' => 1 }
    end

    def stderr_configure(value)
        # 0 - default
        # 1 - copy data
        # 2 - redirection

        logger.puts("Configure stderr: #{value}")

        { 'success' => 1 }
    end

    def dispatch_command(command)
        logger.puts('Dispatching command: ' + command.name)

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
                type = command.arg('-t')
                state = command.arg_with_default('-s', 'enabled') == 'enabled' ? true : false           
                temporary = command.arg_with_default('-r', false)

                #hit_value = command.arg('-h')     # hit value (hit_value) used with the hit condition to determine if should break; a value of zero indicates hit count processing is disabled for this breakpoint [optional, defaults to zero (i.e. disabled)]
                #hit_condition = command.arg('-o') # hit condition string (hit_condition); see hit_condition documentation above; BTW 'o' stands for 'operator' [optional, defaults to '>=']

                case type
                when 'line'
                    file = File.expand_path(decode_uri(command.arg('-f')))
                    line = command.arg('-n').to_i

                    breakpoint_set_line(file, line, state, temporary)
                
                when 'call'
                    function = command.arg('-m')
                
                when 'return'
                    function = command.arg('-m')

                when 'exception'
                    exception = command.arg('-x')
                    breakpoint_set_exception(exception, state, temporary)

                when 'conditional'
                when 'whatch'                
                end


            when 'breakpoint_get'
                breakpoint_get

            when 'breakpoint_update'
                id = command.arg('-d').to_i
                state = command.arg('-s') == 'enabled' ? true : false
                breakpoint_update(id, state)
            
            when 'breakpoint_remove'
                id = command.arg('-d').to_i
                breakpoint_remove(id)

            when 'breakpoint_list'
                breakpoint_list

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
                # TODO:
                {}

            when 'property_value'
                # TODO:
                {}
        
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
        end

        unless data.nil?
            data['id'] = command.arg('-i')
        end

        logger.puts('Dispatch OK')

        data
    end

private
    def receive
        @io.receive
    end

    def send(name, data)
        @io.send(name, data)
    end

public
    def initialize(debugger, thread, io, key)
        @debugger = debugger
        @thread = thread
        @io = io    
        @key = key
        
        @features = Features.new
        @states = States.new
        
        @stack = VirtualStack.new

        @waitDepth = -1
        
        @terminated = false

        @has_data = false

        send('init', init)
    end

    def terminated?
        @terminated
    end

    def terminate
        unless @terminated
            logger.puts('Terminating thread...')

            @terminated = true

            unless @last_continuation_command.nil?
                map = { 'status' => 'stopped',
                        'reason' => 'ok',
                        'id'     => @last_continuation_command.arg('-i') }

                send(@last_continuation_command.name, map)
            end
        end
    end

    def trace(event, file, line, id, binding, klass)
        if @terminated
            return
        end

        # Don't debug debugger :)
        if klass.to_s.index('XoredDebugger::') == 0
            return
        end
        
        # Get the code line 
        @where = 'Unknown location'
        if lines = SCRIPT_LINES__[file] and lines != true
            @where = lines[line - 1].chomp
        end      
        
                  
        @file = File.expand_path(file)  #File.expand_path(File.join(Dir.pwd, file))
        @line = line
        @binding = binding

        case event
            when 'line'
                @stack.update(@binding, @file, @line, @where)
                
                capturer.disable
                out = capturer.output
                unless out.empty?
                    send('stdout_data', {'data' => out})
                end

                br = breakpoints.line_break?(@file, @line) 
    
                if (@waitDepth == -1 or
                    @waitDepth >= @stack.depth or
                    br)
                                        
                    logger.puts("==>> Line: #{@line} from #{@file} (depth: #{@stack.depth}, waitDepth: #{@waitDepth}) <<==")

                    unless @last_continuation_command.nil?
                        map = { 'status' => 'break',
                                'reason' => 'ok',
                                'id'     => @last_continuation_command.arg('-i') }

                        send(@last_continuation_command.name, map)
                    else
                        #report status starting
                    end
            
                    # Command handling loop
                    loop do
                        command = Command.new(receive)                        
                        data = dispatch_command(command)

                        if ['run', 'step_into', 'step_over', 'step_out'].include?(command.name)
                            @last_continuation_command = command
                        end 

                        if data.nil?
                            break
                        else
                            send(command.name, data)
                        end
                    end
                else
                 #   if @io.has_data?
                 #       @has_data = true
                 #   end
                end

                capturer.enable
   
            when 'call'
                @stack.push(@binding, @file, @line, @where)
    
            when 'return'
                @stack.pop
    
            when 'class'    
            when 'end'     
            
            when 'raise'
                set_trace_func nil
                @debugger.terminate
        end
    end

    def to_s
        'Thread [' + @thread.object_id.to_s + ']'
    end
end # class VirtualThread

end # module
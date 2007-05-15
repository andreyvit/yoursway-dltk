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
        { :app_id   => 'test',
          :ide_key  => @key,
          :session  => 'session',
          :thread   => 'Thread with id = ' + @thread.object_id.to_s,
          :parent   => '',
          :file_uri => '' }
    end
                         
    # Status command
    def status
        { :status => @states.to_s,
          :reason => 'ok' }
    end

    # Feature commands
    def feature_get(name)
        logger.puts("feature_get: #{name}")

        { :name      => name, 
          :supported => 1, 
          :value     => @features.get(name).to_s }
    end

    def feature_set(name, value)
        logger.puts("feature_set: #{name} = #{value}")

        @features.set(name, value) # Check types!!! (string or int)
        { :name    => name, 
          :success => 1 }
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
        { :success => true }       
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

        properties = []

        # Local variables
        if contexts.include?(LOCAL_CONTEXT_ID)   
            vars = @stack.eval('local_variables', depth)
    
            vars.each { |var|
                real_var = @stack.eval(var, depth)

                unless real_var.nil?
                    properties << make_property(var, real_var)              
                end
            }
    
            # TODO: correct this later
            self_var = @stack.eval('self', depth)
            unless self_var.nil?
                properties << make_property('self', self_var)  
            end
        end

        # Global variables
        if contexts.include?(GLOBAL_CONTEXT_ID)
            vars = @stack.eval('global_variables', depth)
            vars.each { |var|
                properties << make_property(var)
            } 
        end

        # Class variables
        if contexts.include?(CLASS_CONTEXT_ID)
            vars = @stack.eval('instance_variables', depth)
            vars.each { |var|
                properties << make_property(var)
            }
        end

        { :properties => properties, 
          :context_id => context_id }
    end

    # Property commands
    def property_get(name, key)
        obj = ObjectSpace._id2ref(key)
        if obj.nil?
            obj = 'Invalid key :('
        end
    
        { :property => make_property(name, obj) }
    end

    def property_set(name, depth, value)
        success = true
        begin
            command = name + ' = ' + value.to_s
            logger.puts('String to evaluate: ' + command)
            @stack.eval(command, depth)
        rescue Exception
            success = false
        end

        { :success => success }
    end

    def property_value
        {}
    end


    # Breakpoint commands
    def breakpoint_set_line(file, line, state, temporary)
        id = breakpoints.set_line_bpt(file, line, state)

        { :state         => state, 
          :breakpoint_id => id }
    end
    
    def breakpoint_set_exception(exception, state, temporary)
        id = breakpoints.set_exception_bpt(exception, state)        

        { :state         => state, 
          :breakpoint_id => id }        
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
        val = breakpoints.remove(id)

        {}
    end

    # Stack commands
    def stack_depth
        { :depth => @stack.depth }
    end

    def stack_get(depth = nil)
        levels = []
        @stack.depth.times { |i|
            level = @stack[i]
            levels << { :level    => i,
                        :type     => 'source',
                        :filename => 'file:///' + level['file'],
                        :lineno   => level['line'],
                        :cmdbegin => '0:0',
                        :cmdend   => '0:0',
                        :where    => level['where'] }            
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
    end

    def stdin_configure(value)
        # 0 - disable
        # 1 - redirect

        if value == 0
            logger.puts("Disabling stdin")
        elsif value == 1
            logger.puts("Redirecting stdin")
        end

        { :success => 1 }
    end

    def stdout_configure(value)
        # 0 - default
        # 1 - copy data
        # 2 - redirection

        logger.puts("Configure stdout: #{value}")

        { :success => 1 }
    end

    def stderr_configure(value)
        # 0 - default
        # 1 - copy data
        # 2 - redirection

        logger.puts("Configure stderr: #{value}")

        { :success => 1 }
    end

    def eval_handler(expression)
        success = true
        property = nil
        begin
            property = make_property(expression, @stack.eval(expression))        
        rescue Exception
            success = false
        end
    
        { :success  => success, 
          :property => property }
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
                depth = command.arg_with_default('-d', '0').to_i
                context_names(depth)

            when 'context_get'
                depth = command.arg_with_default('-d', '0').to_i
                context_id = command.arg_with_default('-c', '0').to_i
                context_get(depth, context_id)

            # Property commands
            when 'property_get'
                name = command.arg('-n')
                key = command.arg('-k').to_i
                property_get(name, key)
                
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
                eval_handler(expression)
        end

        unless data.nil?
            data[:id] = command.arg('-i')
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

        send('init', init)
    end

    def terminated?
        @terminated
    end

    def terminate
        unless @terminated
            logger.puts('Terminating thread...')

            @terminated = true

            unless @command.nil?
                map = { :status => 'stopped',
                        :reason => 'ok',
                        :id     => @command.arg('-i') }

                send(@command.name, map)
            end
        end
    end

    def trace(event, file, line, id, binding, klass)
        if @terminated
            return
        end

        capturer.disable
        out = capturer.get
        unless out.empty?
            send('stdout_data', {:_data => out})
        end
        capturer.enable

        # Don't debug debugger :)
        if klass.to_s.index('XoredDebugger::') == 0
            return
        end
        
        # Get the code line 
        where = 'Unknown code line'
        if lines = SCRIPT_LINES__[file] and lines != true
            where = lines[line - 1].chomp
        end      
        
        @file = File.expand_path(file) # Absolute file path

        case event
            when 'line'
                capturer.disable

                @stack.update(binding, @file, line, where)
            
                has_breakpoint = breakpoints.line_break?(@file, line) 
    
                if (@io.has_data? or
                    @waitDepth == -1 or
                    @waitDepth >= @stack.depth or
                    has_breakpoint)
                    
                    logger.puts("==>> Line ##{line} from #{@file} <<==")

                    # Break checking
                    unless @last_continuation_command.nil?
                        map = { :status => 'break',
                                :reason => 'ok',
                                :id     => @last_continuation_command.arg('-i') }

                        send(@last_continuation_command.name, map)

                        @last_continuation_command = nil
                    end

                    # Command loop
                    loop do
                        @command = Command.new(receive)                        
                            
                        if ['run', 'step_into', 'step_over', 'step_out'].include?(@command.name)
                            @last_continuation_command = @command
                        end

                        data = dispatch_command(@command)

                        logger.puts('Data: ' + data.inspect) 

                        if data.nil?
                            break
                        else
                            send(@command.name, data)
                        end
                    end
                end

                capturer.enable
   
            when 'call'
                @stack.push(binding, @file, line, where)
    
            when 'return'
                @stack.pop
    
            when 'class'
                #TODO: Do something useful
    
            when 'end'
                #TODO: Do something useful
            
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
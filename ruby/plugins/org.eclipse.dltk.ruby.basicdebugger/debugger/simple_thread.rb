###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'dbgp/managers/breakpoint'
require 'dbgp/command_handler'

require 'stack_manager'
                             
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
 

    class RubyThread < CommandHandler
        # Events
        def created
            log_manager.puts("Thread 'created' event: " + @thread.inspect)
            io_manager.send('init', init('app_test_id', @key, thread_label, @script))
        end
        
        def terminated
            log_manager.puts("Thread 'terminated' event: " + @thread.inspect)
            unless @f_term
                sent_stopped
            end         
        end
        
        
        def terminate
            log_manager.puts('Thread termination:')
            log_manager.puts("\tThread main: " + (@thread == Thread.main).to_s)
            log_manager.puts("\tThread info: " + @thread.inspect.to_s)
            
            sent_stopped            
            @f_term = true
            Thread.kill(@thread)
        end
                
        def capturer
            @debugger.capturer
        end
    
        def thread_label
            label = @thread == Thread.main ? 'Main thread' : 'Thread'
            label += ' id=' + @thread.object_id.to_s
            label += ', priority=' + @thread.priority.to_s
        end
    
        def to_s
            'Thread [' + @thread.object_id.to_s + ']'
        end
    
        def breakpoint_manager
            @debugger.breakpoint_manager
        end

        def feature_manager
            @debugger.feature_manager
        end
        
        def log_manager
            @debugger.log_manager
        end
        
        def source_manager
            @debugger.source_manager
        end
        
        def stack_manager
            @stack_manager
        end
        
        def io_manager
            @io
        end

        def initialize(debugger, thread, io, key, script)
            @debugger = debugger
            @thread = thread
            @io = io
            @key = key
            @script = script
            
            
            @waitDepth = -1
            @stack_manager = FullStackManager.new           
                 
            @f_term = false
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
            @waitDepth = @stack_manager.stack.depth
            nil
        end

        def step_out
            @waitDepth = @stack_manager.stack.depth - 1
            nil
        end

        def stop
            log_manager.puts('Stopping thread...')
            terminate
            nil
        end

        def detach
            log_manager.puts('--> detach <--')
            nil
        end

        def break_cmd
            @waitDepth = -1
            { :success => true }
        end

        def command_loop
             if @last_continuation_command.nil?
                loop do
                    @command = Command.new(io_manager.receive)

                    if ['run', 'step_into', 'step_over', 'step_out'].include?(@command.name)
                        @last_continuation_command = @command
                    end

                    data = dispatch_command(@command)

                    log_manager.puts('Data: ' + data.inspect)

                    if data.nil?
                        break
                    else
                        io_manager.send(@command.name, data)
                    end
                end
            end
        end

        def trace(event, file, line, id, binding, klass)       
            #log_manager.puts("Thread trace from #{file} at #{line}")

            # Get the code line
            where = source_manager.line_at(file, line)        #get_code_line(file, line)

            # Absolute path
            @file = File.expand_path(file) # Absolute file path
            @line = line

            # Output
            capturer.disable
            out = capturer.get
            unless out.empty?
                io_manager.send('stdout_data', {:_data => out})
            end
            #capturer.enable

            # Don't debug debugger :)
            if klass.to_s.index('XoredDebugger::') == 0
                return
            end

            command_loop

            case event
                when 'line'
                    #capturer.disable

                    @stack_manager.stack.update(binding, @file, line)

                    br_break = breakpoint_manager.line_break?(@stack_manager.stack, @file, line)
                    log_manager.puts("Breakpoint test result: file: #{file}, line: #{line}, break: #{br_break}, thread: #{Thread.current}")
                    #    @io.has_data? or
                    if (
                        @waitDepth == -1 or
                        @waitDepth >= @stack_manager.stack.depth or
                        br_break)

                        log_manager.puts("==>> Line ##{line} from #{@file} by #{Thread.current} <<==")

                        # Break checking
                        unless @last_continuation_command.nil?
                            map = { :status => 'break',
                                    :reason => 'ok',
                                    :id     => @last_continuation_command.arg('-i') }

                            io_manager.send(@last_continuation_command.name, map)

                            @last_continuation_command = nil
                        end

                        command_loop
                    end

                    #capturer.enable
                when 'call'
                    @stack_manager.stack.push(binding, @file, line)

                when 'return'
                    @stack_manager.stack.pop

                when 'class'
                    #TODO: Do something useful

                when 'end'
                    #TODO: Do something useful

                when 'raise'
                    # TODO: handle exception and check exception breakpoints
                    #set_trace_func nil
                    #@debugger.terminate
                    log_manager.puts('=== Exception ===')
            end
        end
    end
end # module
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
            log("Thread 'created' event: " + @thread.inspect)
            io_manager.send('init', init('app_test_id', @key, get_thread_label(@thread), @script))
        end
        
        def terminated
            log("Thread 'terminated' event: " + @thread.inspect)
            sent_stopped
        end
        
		# Terminate function
        def terminate
            log('Thread termination: ' + @thread.inspect)
            log("\tMain?: " + (@thread == Thread.main).to_s)
                        
            sent_stopped
            Thread.kill(@thread)
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
            @stack_manager
        end
        
        def io_manager
            @io_manager
        end

        def initialize(debugger, thread, io_manager, key, script)
            @debugger = debugger
            @thread = thread
            @io_manager = io_manager
            @key = key
            @script = script
            
            @waitDepth = -1
            @stack_manager = FullStackManager.new
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
            terminate
            nil
        end

        def detach
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

                    log('Data: ' + data.inspect)

                    if data.nil?
                        break
                    else
                        io_manager.send(@command.name, data)
                    end
                end
            end
        end
		
		def sync_output
			stdout = capture_manager.get_stdout
			stderr = capture_manager.get_stderr
			
            unless stdout.empty?
                io_manager.send('stdout_data', {:_data => stdout})
            end
			
			unless stderr.empty?
				io_manager.send('stdout_data', {:_data => stderr})
			end
		end

        def trace(event, file, line, id, binding, klass)       
            # Absolute path
            @file = File.expand_path(file) # Absolute file path
            @line = line

            # Output
            sync_output

            # Don't debug debugger :)
            if klass.to_s.index('XoredDebugger::') == 0
                return
            end

            command_loop
			
			# Output handling
			case event
                when 'line'
                    @stack_manager.stack.update(binding, @file, line)

					log('Checking breakpoint...')
                    br_break = breakpoint_manager.line_break?(@stack_manager.stack, @file, line)
                    log("Breakpoint test result: file: #{file}, line: #{line}, break: #{br_break}, thread: #{Thread.current}")
                    #    @io.has_data? or
                    if (
                        @waitDepth == -1 or
                        @waitDepth >= @stack_manager.stack.depth or
                        br_break)

                        log("==>> Line ##{line} from #{@file} by #{Thread.current} <<==")

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

                    
                when 'call'
                    @stack_manager.stack.push(binding, @file, line)

                when 'return'
                    @stack_manager.stack.pop

                when 'class'
                    #TODO: Do something useful

                when 'end'
                    #TODO: Do something useful

                when 'raise'
					#TODO: Handle exception breakpoints here
            end
        end
    end
end # module
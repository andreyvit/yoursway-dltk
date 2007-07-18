###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################    

require 'dbgp/logger'
require 'dbgp/params'

require 'dbgp/managers/log_null'
require 'dbgp/managers/log_file'
require 'dbgp/managers/log_stdout'

require 'simple_debugger'
                          
module XoredDebugger
    class Runner
        def Runner.go
            begin
                params = Params.new
                puts params.inspect
                log = params.log
                logger = log.nil? ? NullLogManager.new : (log == 'stdout' ? StdoutLogManager.new : FileLogManager.new(log))
                
                Logger.setup(logger)
                
                params.print(logger)
                
                unless params.valid?
                    logger.puts('Invalid debugger params')
                    return
                end

                # Debugger setup
                logger.puts('Creating debugger...')
                debugger = RubyDebugger.new(params)

                logger.puts('Setting trace_func...')
                set_trace_func proc { |event, file, line, id, binding, klass, *rest|
                    #logger.puts("=> Trace: #{event.to_s} from #{file.to_s} at #{line.to_s}")
                    debugger.trace(event, file, line, id, binding, klass)
                }

                # Script for debug
                logger.puts('Unsetting trace func...')
                load(params.script, true)

                # Debugger teardown
                logger.puts('Terminating all...')
                set_trace_func nil
                debugger.terminate
            rescue Exception
                logger.puts('Exception during debugging:')
                logger.puts("\tClass: " + $!.class.to_s)
                logger.puts("\tMessage: " + $!.message)
                logger.puts("\tBacktrace: " + $!.backtrace.join("\n"))
            ensure
                logger.close
            end  
        end # go
    end # class Runner

    Runner.go

end # XoredDebugger
###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'cgi'
require 'thread'

require 'dbgp/breakpoints'
require 'dbgp/command'
require 'dbgp/logging'
require 'dbgp/my_thread'

module XoredDebugger
               
    SCRIPT_LINES__ = {} unless defined? SCRIPT_LINES__


    class Debugger
    private
        def add_thread(thread)
            @logger.puts('Adding thread: ' + thread.to_s)
            @threads[thread] = VirtualThread.new(thread, self, @logger, @host, @port, @key)
        end 

        def remove_thread(thread)
            @logger.puts('Removing thread: ' + thread.to_s)
            @threads[thread].terminate
            @threads.delete(thread)
        end 

        def sync_threads
            @mutex.synchronize do             
                list = Thread.list
                old_list = @threads.keys
         
                removed = old_list - list               
                removed.each { |t| remove_thread(t) }
                                        
                added = list - old_list
                added.each { |t| add_thread(t) }
            end        
        end 
        
    public
        def initialize(host, port, key, logger)
            @host = host
            @port = port
            @key = key      
            @logger = logger
 
            @threads = {}    

            @breakpoints = Breakpoints.new 
            @mutex = Mutex::new 
        end

        def terminate
            @mutex.synchronize do
                @threads.keys.each { |t| remove_thread(t) }
                @threads = nil 
            end            
        end

        # Breakpoint management
        def set_line_breakpoint(file, line, state)
            log("Setting line breakpoint, file: #{file}; line: #{line}; state: #{state}")
            @breakpoints.set_line(file, line, state)
        end

        def breakpoint_remove(id)
            @breakpoints.remove(id)
        end

        def breakpoint_update(id, state)
            @breakpoints.update(id, state)
        end

        def break?(file, line)
            @breakpoints.break?(file, line)
        end

        # Logging
        def log(text)
            @logger.puts(text)
        end

        # Tracing
        def trace(event, file, line, id, binding, klass)
            sync_threads

            thread = @threads[Thread.current]
            thread.trace(event, file, line, id, binding, klass)
        end 
    end # class Debugger


    log = ENV['DBGP_RUBY_LOG']
    logger = log.nil? ? NullLogger.new : (log == 'stdout' ? StdoutLogger.new : FileLogger.new(log))

    host   = ENV['DBGP_RUBY_HOST']
    port   = ENV['DBGP_RUBY_PORT'].to_i
    key    = ENV['DBGP_RUBY_KEY']
    script = ENV['DBGP_RUBY_SCRIPT']

    begin
        if (host.nil? or port == 0 or key.nil? or script.nil?)
            logger.puts('Invalid debugger params')
        else
            logger.puts('Debugging session on ' + Time.new.to_s)
            logger.puts('Host: ' + host)
            logger.puts('Port: ' + port.to_s)
            logger.puts('Key: ' + key)
            logger.puts('Script: ' + script)

            # Debugger setup
            @debugger = Debugger.new(host, port, key, logger)
                    
            set_trace_func proc { |event, file, line, id, binding, klass, *rest|
                @debugger.trace(event, file, line, id, binding, klass)
            }

            load script
            
            set_trace_func nil

            @debugger.terminate

            #set_trace_func nil 
            #@debugger.shutdown
        end

    rescue Exception
        logger.puts('Exception during debugging:')
        logger.puts('Message:')
        logger.puts($!.message)
        logger.puts('Backtrace:')
        logger.puts($!.backtrace.join("\n"))
    ensure
        logger.close
    end
end # module

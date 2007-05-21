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


require 'dbgp/command'
require 'dbgp/socket_io'
require 'dbgp/test_io'
require 'dbgp/printer'
require 'dbgp/capturer'
require 'dbgp/ruby_thread'
require 'dbgp/ruby_breakpoints'

module XoredDebugger
               
    class RubyDebugger
    private
        def add_thread(thread)
            @logger.puts('Adding thread: ' + thread.to_s)

            printer = Printer.new

            io = @test ? TestIO.new(@logger, printer) : SocketIO.new(@host, @port, @logger, printer)

            @threads[thread] = RubyThread.new(self, thread, io, @key)
        end 

        def remove_thread(thread)
            @logger.puts('Removing thread: ' + thread.to_s)
            @threads[thread].terminate
            @threads.delete(thread)
        end 

        def thread_list
            Thread.list - [@remover]
        end

        def add_threads
            @mutex.synchronize do
                added = thread_list - @threads.keys
                added.each { |t| 
                    add_thread(t) 
                }
            end        
        end

        def remove_threads
            @mutex.synchronize do
                @threads.each { |t, thread|
                    if thread.terminated?
                        Thread.kill(t)
                    end
                }
                                        
                removed = @threads.keys - thread_list
                removed.each { |t| 
                    remove_thread(t) 
                }
            end
        end

    public
        def initialize(host, port, key, logger, test)
            @host = host
            @port = port
            @key = key      
            @logger = logger
            @test = test
 
            @threads = {}    

            @stdout_capturer = StdoutCapturer.new(true)
            #@stderr_capturer = StderrCapturer.new(true)

            @breakpoints = Breakpoints.new
            @mutex = Mutex.new 

            @remover = Thread.new do
                loop do
                    remove_threads                   
                    sleep 0.5
                end
            end
        end

        def terminate
            @logger.puts('Debugger termination...')
            Thread.kill(@remover)

            @mutex.synchronize do
                @threads.each { |thread, t|
                    t.terminate
                }
            end            
        end

        def breakpoints
            @breakpoints
        end

        def logger
            @logger
        end

        def capturer
            @stdout_capturer
        end

        # Logging
        def log(text)
            @logger.puts(text)
        end

        # Test mode
        def test?
            @test    
        end

        # Tracing
        def trace(event, file, line, id, binding, klass)
            if Thread.current == @remover
                return
            end

            add_threads
            thread = @threads[Thread.current]
            thread.trace(event, file, line, id, binding, klass)
        end 
    end # class RubyDebugger

end # module

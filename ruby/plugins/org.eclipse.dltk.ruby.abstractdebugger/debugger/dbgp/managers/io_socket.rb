###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'socket'
require 'io/wait'
require 'monitor'

require 'logger'

module XoredDebugger
    class SocketIOManager
		include Logger

        def initialize(host, port, printer)
			@monitor = Monitor.new
            @socket = TCPSocket.new(host, port)
            @printer = printer
        end

        def send(command, data)
			@monitor.synchronize do
	            xml = @printer.print(command, data)
	            log('>>> ' + xml)
	            
                #DEBUGGER: [NUMBER] [NULL] XML(data) [NULL]
	            @socket.write(xml.length.to_s)
	            @socket.putc(0)
	            @socket.write(xml)
	            @socket.putc(0)
	            @socket.flush
			end
        end

        def has_data?
            @socket.ready?
        end

        def receive()
            line = ''
            begin
   	            #IDE: command [SPACE] [args] -- data [NULL]
	            while((ch = @socket.getc) != 0)
	                line << ch
	            end
            rescue Exception
                # Connection broken, exitting
                # TODO: Add reconnect if thread not finished.
                throw :done
            end

            log('<<< ' + line)	            
            line
        end
        
        def close
            @socket.close
        end
    end # class SocketIOManager
end # module XoredDebugger
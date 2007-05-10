###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger

#
# class SocketIO
#

require 'socket'

class SocketIO
    def initialize(host, port, logger, printer)
        @socket = TCPSocket.new(host, port)
        @logger = logger
        @printer = printer
    end

private
    def log_send(text)
        unless @logger.nil?
            @logger.puts('>>> ' + text)
        end
    end

    def log_receive(text)
        unless @logger.nil?
            @logger.puts('<<< ' + text)
        end
    end

public
    def send(command, data)
        xml = @printer.print(command, data)
        #DEBUGGER: [NUMBER] [NULL] XML(data) [NULL]
        @socket.write(xml.length.to_s)
        @socket.putc(0)
        @socket.write(xml)
        @socket.putc(0)
        @socket.flush

        # Logging
        log_send(xml)
    end

    def receive()
        #IDE: command [SPACE] [args] -- data [NULL]
        line = ''
        while((ch = @socket.getc) != 0)
            line << ch
        end

        # Logging
        log_receive(line)

        line
    end

    def close
        @socket.close
    end
end

end # module
#
# class SocketIO
#

require 'socket'

class SocketIO
	def initialize(host, port, logger = nil)
		@socket = TCPSocket.new(host, port)
		@logger = logger
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
	def send(xml)
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

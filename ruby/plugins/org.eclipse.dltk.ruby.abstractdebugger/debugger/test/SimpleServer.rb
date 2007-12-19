require 'socket'

module XoredDebugger
    class SimpleServer
        include Socket::Constants
        
        PORT = 15000
        @@instance = nil
        
        def SimpleServer.start
            @@instance = SimpleServer.new(PORT) if @@instance.nil?
        end           
                
        def SimpleServer.started?
            ! @@instance.nil?            
        end
        
        def SimpleServer.received
            if @@instance.nil?
                ''
            else
                @@instance.received
            end
        end           

        def SimpleServer.stop
            @@instance.terminate unless @@instance.nil?
        end
        
        def initialize(port)
            @port = port
            @received = ''
            @terminated = false
            @server_thread = Thread.new do
                server_proc
            end
        end
               
        def terminate()
            @terminated = true
            begin
                @server_thread.wakeup
                @server_thread.join
            rescue Exception
            end
        end
        
        def received
            value = @received
            @received = ''
            return value
        end
        
            
        def server_proc
            begin
                @server = Socket.new( AF_INET, SOCK_STREAM, 0 )
                sockaddr = Socket.pack_sockaddr_in( PORT, 'localhost' )
                @server.bind( sockaddr )
                @server.listen( 5 )
                @read_array = [ @server ]
	            while (! @terminated)
	                selected = IO.select(@read_array) 
                    unless selected.nil?
	                    selected[0].each do |socket|
		                    if (socket == @server)
	                            client, client_addr = @server.accept
		                        @read_array.push(client)
		                    else
		                        begin
		                            @received << socket.readpartial(1024)
		                        rescue EOFError
		                            @read_array.delete(socket)
		                        end                                
	                        end
	                    end
                        selected.clear
                    end
		        end
            rescue Exception
                puts 'Exception in server_proc: ' + $!.message
            ensure
                @read_array.each { |s| s.close }
            end            
        end
                    
    end        
end

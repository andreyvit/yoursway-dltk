require 'socket'
require 'common/Logger'

module XoredDebugger
    class SimpleServer
        include Socket::Constants
        include Logger
        
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
            unless @@instance.nil?
                @@instance.terminate
                @@instance = nil
            end 
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
                @server.close unless @server.nil?
                @server_thread.wakeup
                @server_thread.join
            rescue Exception
                logException($!, 'in server termination')
            end
        end
        
        def received
            value = @received
            @received = ''
            return value
        end
        
            
        def server_proc
            log('SimpleServer proc started')
            begin
                @server = TCPServer.new('127.0.0.1', PORT)
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
  		                            piece = socket.readpartial(1024)
                                    if (piece.nil?)
                                        raise EOFError
                                    end
  		                            @received << piece 
  		                        rescue IOError
  		                            @read_array.delete(socket)
  		                        end                                
  	                        end
  	                    end
                        selected.clear
                    end
  	            end
            
            rescue IOError
                # expected, if server was stopped
            rescue Exception
                puts $!.class.name + ' in server_proc: ' + $!.message
                puts $!.backtrace.join("\n")
            ensure
                unless @read_array.nil?
                    @read_array.each { |s| 
                        begin 
                            s.close
                        rescue Exception 
                        end
                    } 
                end
                log('SimpleServer proc terminated')
            end            
        end
                    
    end        
end

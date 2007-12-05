require 'dbgp/params'  
require 'dbgp/managers/io_socket'
require 'dbgp/command_printer'
require 'dbgp/command_handler'

module XoredDebugger
	def XoredDebugger.init_error(code, app_code, message)
	    params = Params.instance
	    error = {
	        :code => code,
	        :app_code => app_code,
	        :message => message }   
	             
	    attrs = { :app_id   => 'app_test_id',
	      :ide_key  => params.key,
	      :session  => 'session',
	      :thread   => get_thread_label(Thread.current),
	      :parent   => '',
	      :file_uri => params.script,
	      :error => error }
	      
        io = SocketIOManager.new(params.host, params.port, Printer.new)
	    io.send('init', attrs)

        # wait until connection closed
        begin
            io.receive
        rescue Exception
        ensure
            io.close                          
        end
	end
end

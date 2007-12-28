require 'socket'
require 'monitor'
require 'common/Params'
require 'common/Logger'
require 'dbgp/CommandHandler'
require 'dbgp/Utils'
require 'debugger/DebugEventHandler'

module XoredDebugger
   class DbgpThread
       include DebugEventHandler
       include XoredDebuggerUtils
       include Logger
       
       attr_reader :communicator
       
       def initialize(thread, thread_manager)
           @debugger = thread_manager.debugger
           @thread = thread
           @command_handler = CommandHandler.new(thread_manager, thread)
           @exited = false
           @context = @debugger.thread_context(thread)
           @communicator = Communicator.new(thread)
           @monitor = Monitor.new
           # starting command handling
           log('creating control thread')
           @dispatcher = @debugger.create_debug_thread { handle_commands }                 
       end
       
       def exited(exception)
           @monitor.synchronize do
               begin
                   send_answer(AbstractContext::STOPPED, exception)
               ensure
                   @exited = true
                   @communicator.close
               end
           end
           @dispatcher.join
       end
       
       def at_breakpoint(context)
       end

       def at_catchpoint(context, excpt)
       end

       def at_line(context, file, line)
           @monitor.synchronize do
               send_answer(AbstractContext::BREAK)
           end           
           context.suspend()
       end        	 
       
       def send_answer(status, exception = nil)
           unless @unanswered.nil?
	           response = Response.new(@unanswered)
	           response.add_attribute('status', status)
	           if (exception.nil?)
	               response.add_attribute('reason', 'ok')
	           else
	               response.add_attribute('reason', 'exception')
               end	               
	           @communicator.sendPacket(response)
               @unanswered = nil
           end
       end
       
       def handle_commands
           begin
               # wait until thread is suspended
               while not @context.suspended?
                   sleep 0.1                   
                   return if @exited
               end
               
               log('Started control thread for ' + @thread.object_id.to_s)
               params = Params.instance
               packet = InitPacket.new(params.key, get_thread_label(@thread), params.script)
               @communicator.sendPacket(packet)               
               while (true)
                   log('waiting command')
                   command = @communicator.receive_command
                                                        
                   @monitor.synchronize do
                       log('processing')
                       response = @command_handler.handle(command)
                       
                       if (command.name == 'break')
                           send_answer('break')                            
                       end                       

                       if (!response.nil?)
                           @communicator.sendPacket(response)
                       else
                           @unanswered = command
                       end                    
                   end
               end
           
           rescue SystemExit
               # stop command received
           
           rescue IOError
               # connection was closed
           
           rescue Exception
               logException($!, 'in control thread') unless $!.nil?
           
           ensure               
               log('Exited control thread for ' + @thread.object_id.to_s)
           end
       end
       
                       
   end
end

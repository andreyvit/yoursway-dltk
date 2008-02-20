###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################
require 'common/Logger'

require 'dbgp/Command'
require 'dbgp/Response'
require 'dbgp/BreakpointElement'
require 'dbgp/StackLevelElement'
require 'dbgp/PropertyElement'

require 'dbgp/SourceManager'
require 'dbgp/Utils'

require 'debugger/FeatureManager'
require 'debugger/BreakpointContracts'
require 'debugger/Exceptions'

module XoredDebugger
	class CommandHandler
	    include Logger
        include XoredDebuggerUtils
        		
        def initialize(thread_manager, thread)
            @debugger = thread_manager.debugger            
            @thread = thread
            @context = @debugger.thread_context(thread)
            @feature_manager = @debugger.feature_manager
            @breakpoint_manager = @debugger.breakpoint_manager
            @capture_manager = thread_manager.capture_manager
        end
        
        # Handles DBGP command. Returns Responce to be sent to client, 
        # or nil if no responce required  
        def handle(command)
            begin          
                log('Command: ' + command.name)   
                check_command_arguments(command, '-i')               
                self.send('handle_' + command.name, command)
            
            rescue SystemExit
                raise $!
                
            rescue InvalidContextError
                err_invalid_context(command)
                
            rescue BreakpointCouldNotBeSetError
                err_could_not_set_breakpoint(command)
                
            rescue NoSuchBreakpointError
                err_no_such_breakpoint(command)
                
            rescue BreakpointTypeNotSupportedError
                err_breakpoint_type_not_supported(command)
                
            rescue OperationNotAvailableError
                err_command_not_available(command)
            
            rescue ArgumentError       
                err_invalid_option(command)
            
            rescue NotImplementedError  
                err_unimplemented_command(command)
            
            rescue Exception
                logException($!, 'in command handler:')     
                err_parse_error(command)  
            end
        end
        
    protected
        # Status
        def handle_status(command)
            response = Response.new(command)
            response.add_attribute('status', @context.status)
            response.add_attribute('reason', 'ok')
            return response
        end
        
        # Options and Configuration
        def handle_feature_get(command)
            raise OperationNotAvailableError unless @context.suspended? 
            
            check_command_arguments(command, '-n')
            name = command.arg('-n')
            supported = @feature_manager.supported?(name)
            
            response = Response.new(command)
            response.add_attribute('feature_name', name)
            response.add_attribute('supported', (supported ? 1:0))
            if (supported)
                value = @feature_manager.get(name).to_s
                response.set_data(value)
            end
            return response
        end
        
        def handle_feature_set(command)
            raise OperationNotAvailableError unless @context.suspended? 
            
            check_command_arguments(command, '-n', '-v')
            name = command.arg('-n')
            value = command.arg('-v')
            
            changed = @feature_manager.set(name, value) # Check types!!! (string or int)
            response = Response.new(command)
            response.add_attribute('feature_name', name)
            response.add_attribute('success', (changed ? 1:0))
            return response
        end
        
        
        # Continuation Commands
        def handle_run(command)
            raise OperationNotAvailableError unless @context.suspended?             
            @context.run
            nil               
        end
        
        def handle_step_into(command)
            raise OperationNotAvailableError unless @context.suspended?             
            @context.step_into
            nil               
        end
        
        def handle_step_over(command)
            raise OperationNotAvailableError unless @context.suspended? 
            @context.step_over
            nil
        end
        
        def handle_step_out(command)
            raise OperationNotAvailableError unless @context.suspended?             
            @context.step_out
            nil
        end
		
        def handle_stop(command)
            @context.stop
            response = Response.new(command)
            response.add_attribute('status', @context.status)
            response.add_attribute('reason', 'ok')
            return response
        end
        
        def handle_detach(command)
            raise NotImplementedError, 'Feature not implemented'
        end
        
        
        #breakpoints
        def handle_breakpoint_set(command)
            check_command_arguments(command, '-t')
            type          = command.arg('-t')
            state         = command.arg_with_default('-s', 'enabled') == 'enabled' ? true : false
            hit_value     = command.arg_with_default('-h', '0').to_i
            hit_condition = command.arg_with_default('-o', '>=')
            temporary     = command.arg_with_default('-r', '0').to_i > 0
            expression    = command.data

            bp = case type
                when 'line'
                    check_command_arguments(command, '-f', '-n')
                    file = File.expand_path(uri_to_path(command.arg('-f')))
                    line = command.arg('-n').to_i
                    @breakpoint_manager.add_line_breakpoint(file, line, temporary)

                when 'exception'
                    check_command_arguments(command, '-x')
                    exception = command.arg('-x')
                    @breakpoint_manager.add_exception_breakpoint(exception, temporary)
                    
                when 'conditional'
                    check_command_arguments(command, '-f', '--')
                    # TODO: add support for conditional breakpoints 
                    raise BreakpointTypeNotSupportedError
                     
                when 'whatch'
                    check_command_arguments(command, '--')
                    # TODO: add support for watch breakpoints 
                    raise BreakpointTypeNotSupportedError
                    
                when 'call'
                    check_command_arguments(command, '-m')
                    # TODO: add support for call breakpoints 
                    raise BreakpointTypeNotSupportedError

                when 'return'
                    check_command_arguments(command, '-m')
                    # TODO: add support for return breakpoints 
                    raise BreakpointTypeNotSupportedError

                else
                    raise BreakpointTypeNotSupportedError
            end   
            
            bp.state = state            
            bp.expression = expression unless expression.nil?
            bp.hit_condition = hit_condition
            bp.hit_value = hit_value

            response = Response.new(command)
            response.add_attribute('state', (state ? 'enabled':'disabled'))
            response.add_attribute('id', bp.breakpoint_id)
            return response            
        end
        
        def handle_breakpoint_get(command)
            check_command_arguments(command, '-d')
            id = command.arg('-d').to_i
            bp = @breakpoint_manager.breakpoint(id)

            response = Response.new(command)
            response.set_data(BreakpointElement.new(bp))
            return response
        end
        
        def handle_breakpoint_update(command)
            check_command_arguments(command, '-d')
            id = command.arg('-d').to_i
            bp = @breakpoint_manager.breakpoint(id)

            state         = command.arg('-s')
            lineno        = command.arg('-n')
            hit_value     = command.arg('-h')
            hit_condition = command.arg('-o')
            expression    = command.data
            
            modified = false
            unless state.nil?
                bp.state = (state == 'enabled' ? true : false)
                modified = true                
            end
            
            if (!lineno.nil? && (bp.is_a? LineBreakpointContract))
                bp.lineno = lineno.to_i
                modified = true                
            end
                        
            unless hit_value.nil?
                bp.hit_value = hit_value
                modified = true                
            end
            
            unless hit_condition.nil?
                bp.hit_condition = hit_condition
                modified = true                
            end
            
            unless expression.nil?
                bp.expression = expression
                modified = true                
            end

            
            response = Response.new(command)
            unless modified
                response.set_error(3)
            end                     
            return response  
        end
        
        def handle_breakpoint_remove(command)
            check_command_arguments(command, '-d')
            id = command.arg('-d').to_i
            @breakpoint_manager.remove_breakpoint(id)            
            return Response.new(command)
        end
        
        def handle_breakpoint_list(command)
            xml = ''
            @breakpoint_manager.breakpoints.each do |bp|
                xml += BreakpointElement.new(bp).to_xml
            end
            
            response = Response.new(command)
            unless xml.empty?
                response.set_data(xml)
            end
            return response
        end
        
        
        # Stack commands
        def handle_stack_depth(command)
            raise OperationNotAvailableError unless @context.suspended? 
            response = Response.new(command)
            response.add_attribute('depth', get_stack_depth(@context))
            return response
        end
        
        def handle_stack_get(command)            
            raise OperationNotAvailableError unless @context.suspended? 
            response = Response.new(command)
                                  
            depth = command.arg('-d')
            if (!depth.nil?)
                level = @context.stack_frame(depth.to_i)
                response.set_data(StackLevelElement.new(depth.to_i, level))
            else
                xml = '' 
                get_stack_depth(@context).times { |i|
                    level = @context.stack_frame(i)
	                xml += StackLevelElement.new(i, level).to_xml
	            }
                response.set_data(xml)                                
            end
            return response
        end
        
        # Context commands
        LOCAL_CONTEXT_ID = 0
        GLOBAL_CONTEXT_ID = 1
        CLASS_CONTEXT_ID = 2
        CONTEXT_NAMES = [['Local',  LOCAL_CONTEXT_ID],
                         ['Global', GLOBAL_CONTEXT_ID],
                         ['Class',  CLASS_CONTEXT_ID]]
                
        def handle_context_names(command)
            raise OperationNotAvailableError unless @context.suspended? 
            data = CONTEXT_NAMES.collect { |name, id| sprintf('<context name="%s" id="%d"/>', name, id)}
            
            response = Response.new(command)
            response.set_data(data)
            return response
        end
        
        def handle_context_get(command)
            raise OperationNotAvailableError unless @context.suspended?            
            depth = command.arg_with_default('-d', '0').to_i
            context_id = command.arg_with_default('-c', '0').to_i            
                                    
            def make_props(exp, d)
                vars = @context.eval(exp, d)
                pagesize = @feature_manager.get('max_children').to_i
                props = []
                vars.each { |var|                   
                    real_var = @context.eval(var, d)
                    props << PropertyElement.new(real_var, var, pagesize, 0)
                }
                props
            end

            properties = []
           
            response = Response.new(command)
            response.add_attribute('context', context_id)
             
            case context_id                
            # Local variables
            when LOCAL_CONTEXT_ID:
                properties += make_props('local_variables', depth)

                # TODO: correct this later
                self_var = @context.eval('self', depth)
                unless self_var.nil?
                    properties << PropertyElement.new(self_var, 'self')
                end       

            # Global variables
            when GLOBAL_CONTEXT_ID:
                properties += make_props('global_variables', depth)

            # Class variables
            when CLASS_CONTEXT_ID:
                properties += make_props('instance_variables', depth)
            
            else
                raise InvalidContextError
            end
            
            xml = ''
            properties.each { |property|
                xml += property.to_xml
            }
            response.set_data(xml)
            return response
        end
        
        
        # Common Data Types
        def handle_typemap_get(command)
            raise OperationNotAvailableError unless @context.suspended? 
            response = Response.new(command)
            response.add_attribute('xmlns:xsi', 'http://www.w3.org/2001/XMLSchema-instance')
            response.add_attribute('xmlns:xsd', 'http://www.w3.org/2001/XMLSchema')
            # TODO: Add ruby type mappings to response
            return response
        end
        
        
        # Properties, variables and values
        def handle_property_get(command)
            raise OperationNotAvailableError unless @context.suspended? 
            check_command_arguments(command, '-n')
            name = command.arg('-n')
            depth =  command.arg_with_default('-d', '0').to_i            
            page = command.arg_with_default('-p', '0').to_i   
            pagesize = @feature_manager.get('max_children').to_i

            response = Response.new(command)
            begin
                cmd = "#{name}"
                property = PropertyElement.new(@context.eval(cmd, depth), name, pagesize, page)
                response.set_data(property)

            rescue OperationNotAvailableError
                raise $!
				
            rescue Exception
                logException($!, 'in property_get:')     
                response.set_error(300)  
            end
            return response
        end
        
        def handle_property_set(command)
            raise OperationNotAvailableError unless @context.suspended? 
            check_command_arguments(command, '-n', '--')
            name = command.arg('-n')
            depth = command.arg_with_default('-d', '0').to_i
            value = command.data
            
            response = Response.new(command)
            begin
                cmd = "#{name} = #{value.to_s}"
                property = PropertyElement.new(@context.eval(cmd, depth), name)
                response.set_data(property)
                response.add_attribute('success', 1)
                
            rescue OperationNotAvailableError
                raise $!

            rescue Exception
                response.set_error(300)  
                response.add_attribute('success', 0)
            end
            return response        
        end
        
        def handle_property_value(command)
            raise OperationNotAvailableError unless @context.suspended? 
            check_command_arguments(command, '-n')
            name = command.arg('-n')
            depth = command.arg_with_default('-d', '0').to_i

            response = Response.new(command)
            begin
                cmd = "#{name}"   
                value = @context.eval(cmd, depth).inspect                          
                response.set_data(value, true)
                response.add_attribute('encoding', 'base64')
                # TODO: What size attribute means?
                # response.add_attribute('size', ??? )
            rescue OperationNotAvailableError
                raise $!

            rescue Exception
                response.set_error(300)  
            end
            return response            
        end   
        
        # Source
        def handle_source(command)
            check_command_arguments(command, '-f')
            path = uri_to_path(command.arg('-f'))          
            lines = SourceManager.instance.source_for(path)
            
            response = Response.new(command)
            if (lines.nil?)
                # error: can not open file
                response.set_error(100)
            else
                response.add_attribute('success', 1)
                response.set_data(lines.join, true)
            end    
            return response           
        end
        
        
        # Stdout, stderr redirection
        def handle_stdout(command)
            check_command_arguments(command, '-c')
            state = case command.arg('-c').to_i
                when 0 : state = CaptureManager::DISABLE
                when 1 : state = CaptureManager::COPY
	            when 2 : state = CaptureManager::REDIRECT
	            else raise ArgumentError   
            end
                
            @capture_manager.stdout_capturer.state = state
            response = Response.new(command)
            response.add_attribute('success', 1)
            return response
        end
        
        def handle_stderr(command)
            check_command_arguments(command, '-c')
            state = case command.arg('-c').to_i
                when 0 : state = CaptureManager::DISABLE
                when 1 : state = CaptureManager::COPY
	            when 2 : state = CaptureManager::REDIRECT
	            else raise ArgumentError   
            end

            @capture_manager.stderr_capturer.state = state
            response = Response.new(command)
            response.add_attribute('success', 1)
            return response
        end
        
        
        ######################################
        # Extended Commands
        ######################################
        
        def handle_stdin(command)
            raise NotImplementedError, 'Feature not implemented'
        end

	
        def handle_break(command)
            raise OperationNotAvailableError if @context.suspended? 
            @context.suspend
            response = Response.new(command)
            response.add_attribute('status', @context.status)
            response.add_attribute('reason', 'ok')
            return response        
        end

        def handle_eval(command)
            check_command_arguments(command, '--')
            response = Response.new(command)
            begin
                expression = command.data
                result = calculate_in_another_thread(expression)
                response.add_attribute('success', 1)
                response.set_data(PropertyElement.new(result, expression))
            
            rescue OperationNotAvailableError
                raise $!
            
            rescue Exception                        
                response.add_attribute('success', 0)
                response.set_error(206)                 
            end
            return response
        end

        def handle_expr(command)
            raise NotImplementedError, 'Feature not implemented'
        end

        def handle_exec(command)
            raise NotImplementedError, 'Feature not implemented'
        end

        # Spawnpoint commands
        def handle_spawnpoint_set(command)
            raise NotImplementedError, 'Feature not implemented'
        end

        def handle_spawnpoint_get(command)
            raise NotImplementedError, 'Feature not implemented'
        end

        def handle_spawnpoint_update(command)
            raise NotImplementedError, 'Feature not implemented'
        end

        def handle_spawnpoint_remove(command)
            raise NotImplementedError, 'Feature not implemented'
        end

        def handle_spawnpoint_list(command)
            raise NotImplementedError, 'Feature not implemented'
        end

        # interact - Interactive Shell
        def handle_interact(command)
            raise NotImplementedError, 'Feature not implemented'
        end
        
        # Error handling
        def err_parse_error(command)
            response = Response.new(command)
            response.set_error(1)
            return response
        end  
        
        def err_invalid_option(command)
            response = Response.new(command)
            response.set_error(3)
            return response
        end
        
        def err_unimplemented_command(command)
            response = Response.new(command)
            response.set_error(4)
            return response
        end        
        
        def err_command_not_available(command)
            response = Response.new(command)
            response.set_error(5)
            return response
        end        

        def err_could_not_set_breakpoint(command)
            response = Response.new(command)
            response.set_error(200)
            return response
        end
        
        def err_breakpoint_type_not_supported(command)
            response = Response.new(command)
            response.set_error(201)
            return response
        end
        
        def err_no_such_breakpoint(command)
            response = Response.new(command)
            response.set_error(205)
            return response
        end

        def err_invalid_context(command)
            response = Response.new(command)
            response.set_error(302)
            return response           
        end

        def check_command_arguments(command, *args)
            unless (command.is_a? Command)
                raise ArgumentError, 'Invalid command was supplied'                
            end
            args.each do
                |name|
                unless (!command.arg(name).nil? || (name == '--' && !command.data.nil?))
                    raise ArgumentError, 'Required argument missed'
                end 
            end 
        end  
        
        def calculate_in_another_thread(expression)
            @result = nil
            thread = @debugger.create_debug_thread do
                @result = @context.eval(expression, 0)
            end            
            exited = thread.join(5)
            if (thread.alive?)
                thread.kill
                raise Exception, 'Thread not exited'
            end
            @result            
        end  
    end # class CommandHandler
end # module XoredDebugger

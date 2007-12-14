require 'test/unit'
require 'dbgp/CommandHandler.rb'
require 'dbgp/ThreadManager'
require 'test/MockDebugger'

module XoredDebugger    
    class CommandHandlerTest < Test::Unit::TestCase        
        def setup
            @debugger = MockDebugger.new()
            @thread_manager = ThreadManager.new(@debugger)
            @breakpoint_manager = @debugger.breakpoint_manager
            @context = @debugger.current_context                         
            @handler = CommandHandler.new(@thread_manager, Thread.current)
            @capture_manager = @thread_manager.capture_manager
        end
        
        def teardown
            @thread_manager.terminate
            @debugger.terminate
        end
        
        # Status
        def test_handle_status
            @context.status = 'starting'             
            
            response = @handler.handle(Command.new('status -i 5'))
            assert_equal("<response status=\"starting\" command=\"status\" transaction_id=\"5\" reason=\"ok\"/>", 
                response.to_xml)
                        
            @context.status = 'running'             

            response = @handler.handle(Command.new('status -i 5'))
            assert_equal("<response status=\"running\" command=\"status\" transaction_id=\"5\" reason=\"ok\"/>", 
                response.to_xml)
        end
        
        # Options and Configuration
        def test_handle_feature_get
            response = @handler.handle(Command.new('feature_get -i 5 -n protocol_version'))
            assert_equal("<response command=\"feature_get\" feature_name=\"protocol_version\" supported=\"1\"" +
                " transaction_id=\"5\"><![CDATA[MQ==\n]]></response>", response.to_xml)

            response = @handler.handle(Command.new('feature_get -i 5'))
            assert_equal(3, response.get_error)
                
            response = @handler.handle(Command.new('feature_get -i 5 -n not_supported_option'))
            assert_equal("<response command=\"feature_get\" feature_name=\"not_supported_option\" " +
                "supported=\"0\" transaction_id=\"5\"/>", response.to_xml)
        end
        
        def test_handle_feature_set
            response = @handler.handle(Command.new('feature_set -i 5 -n protocol_version -v 2.0'))
            assert_equal("<response command=\"feature_set\" feature_name=\"protocol_version\" " + 
                "transaction_id=\"5\" success=\"0\"/>", response.to_xml)

            response = @handler.handle(Command.new('feature_set -i 5'))
            assert_equal(3, response.get_error)
                
            response = @handler.handle(Command.new('feature_set -i 5 -n max_depth -v 10'))
            assert_equal("<response command=\"feature_set\" feature_name=\"max_depth\" " +
                "transaction_id=\"5\" success=\"1\"/>", response.to_xml)
        end
        
        
        # Continuation Commands
        def test_handle_run
            @context.status = 'break'                         
            response = @handler.handle(Command.new('run -i 5'))
            assert_equal(nil, response)
            assert_equal('running', @context.status)
            
            response = @handler.handle(Command.new('run -i 5'))
            assert_not_equal(nil, response)
            assert_equal(5, response.get_error)            
        end
        
        def test_handle_step_into
            @context.status = 'break'                         
            response = @handler.handle(Command.new('step_into -i 5'))
            assert_equal(nil, response)
            assert_equal('break', @context.status)
            
            @context.status = 'running'                         
            response = @handler.handle(Command.new('step_into -i 5'))
            assert_not_equal(nil, response)
            assert_equal(5, response.get_error)            
        end
        
        def test_handle_step_over
            @context.status = 'break'                         
            response = @handler.handle(Command.new('step_over -i 5'))
            assert_equal(nil, response)
            assert_equal('break', @context.status)
            
            @context.status = 'running'                         
            response = @handler.handle(Command.new('step_over -i 5'))
            assert_not_equal(nil, response)
            assert_equal(5, response.get_error)            
        end
        
        def test_handle_step_out
            @context.status = 'running'                         
            response = @handler.handle(Command.new('step_out -i 5'))
            assert_not_equal(nil, response)
            assert_equal(5, response.get_error)                    

            @context.status = 'break'                         
            response = @handler.handle(Command.new('step_out -i 5'))
            assert_equal(nil, response)
            assert_equal('stopped', @context.status)            
        end
		
        def test_handle_stop
            @context.status = 'running'                         
            response = @handler.handle(Command.new('stop -i 5'))
            assert_equal("<response status=\"stopped\" command=\"stop\" transaction_id=\"5\" reason=\"ok\"/>",
                response.to_xml)
            assert_equal('stopped', @context.status)            

            @context.status = 'break'                         
            response = @handler.handle(Command.new('stop -i 5'))
            assert_equal("<response status=\"stopped\" command=\"stop\" transaction_id=\"5\" reason=\"ok\"/>",
                response.to_xml)
            assert_equal('stopped', @context.status)            
        end
        
        def test_handle_detach
            response = @handler.handle(Command.new("detach -i 5"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end
        
        
        #breakpoints
        def test_handle_breakpoint_set
            response = @handler.handle(Command.new("breakpoint_set -i 5 -t line -f test.rb -n 100"))            
            assert_equal(0, response.get_error)
            assert(!response.get_attribute('id').nil?)

            response = @handler.handle(Command.new("breakpoint_set -i 5 -t exception -x Exception"))            
            assert_equal(0, response.get_error)
            assert(!response.get_attribute('id').nil?)

            response = @handler.handle(Command.new("breakpoint_set -i 5 -t exception -x Fixnum"))            
            assert_equal(200, response.get_error)
                        
            response = @handler.handle(Command.new("breakpoint_set -i 5"))            
            assert_equal(3, response.get_error)

            response = @handler.handle(Command.new("breakpoint_set -i 5 -t unknownType"))            
            assert_equal(201, response.get_error)
        end
        
        def test_handle_breakpoint_get
            bp = @breakpoint_manager.add_line_breakpoint('test.rb', 100)
            response = @handler.handle(Command.new("breakpoint_get -i 5 -d " + bp.breakpoint_id.to_s))
            assert_equal("<response command=\"breakpoint_get\" transaction_id=\"5\">" +
                "<breakpoint type=\"line\" hit_value=\"0\" id=\"" + bp.breakpoint_id.to_s + "\" filename=\"test.rb\" " +
                "hit_count=\"0\" hit_condition=\"&gt;=\" lineno=\"100\" state=\"true\"/>" + 
                "</response>", response.to_xml)            
            
            bp = @breakpoint_manager.add_exception_breakpoint('Exception')
            response = @handler.handle(Command.new("breakpoint_get -i 5 -d " + bp.breakpoint_id.to_s))
            assert_equal("<response command=\"breakpoint_get\" transaction_id=\"5\">" +
                "<breakpoint exception=\"Exception\" type=\"exception\" hit_value=\"0\" id=\"" + bp.breakpoint_id.to_s + "\" " +
                "hit_count=\"0\" hit_condition=\"&gt;=\" state=\"true\"/>" + 
                "</response>", response.to_xml)            
                
            response = @handler.handle(Command.new("breakpoint_get -i 5 -d 97575795"))
            assert_equal(205, response.get_error)            
        end
        
        def test_handle_breakpoint_update
            bp = @breakpoint_manager.add_line_breakpoint('test.rb', 100)
            
            response = @handler.handle(Command.new("breakpoint_update -i 5 -d " + bp.breakpoint_id.to_s))
            assert_equal(3, response.get_error)    

            response = @handler.handle(Command.new("breakpoint_update -i 5 -n 101 -d " + bp.breakpoint_id.to_s))
            assert_equal("<response command=\"breakpoint_update\" transaction_id=\"5\"/>", response.to_xml)            
            assert_equal('101', bp.lineno)
            
            response = @handler.handle(Command.new("breakpoint_update -i 5 -d 868986086"))
            assert_equal(205, response.get_error)    
        end
        
        def test_handle_breakpoint_remove
            bp = @breakpoint_manager.add_line_breakpoint('test.rb', 100)
            response = @handler.handle(Command.new("breakpoint_remove -i 5 -d " + bp.breakpoint_id.to_s))
            assert_equal("<response command=\"breakpoint_remove\" transaction_id=\"5\"/>", 
                response.to_xml)            

            response = @handler.handle(Command.new("breakpoint_remove -i 5 -d " + bp.breakpoint_id.to_s))
            assert_equal(205, response.get_error)    
        end
        
        def test_handle_breakpoint_list
            @breakpoint_manager.clear
            response = @handler.handle(Command.new("breakpoint_list -i 5"))
            assert_equal(nil, response.get_data) 

            line = @breakpoint_manager.add_line_breakpoint('test.rb', 100)
            expt = @breakpoint_manager.add_exception_breakpoint('Exception')
            expected = BreakpointElement.new(line).to_xml + BreakpointElement.new(expt).to_xml
            response = @handler.handle(Command.new("breakpoint_list -i 5"))
            assert_equal(expected, response.get_data) 
        end
        
        
        # Stack commands
        def test_handle_stack_depth
            @context.status = 'break'            
            response = @handler.handle(Command.new('stack_depth -i 5'))
            assert_equal(@context.stack_frames_num.to_s, response.get_attribute('depth'))
            
            @context.status = 'running'            
            response = @handler.handle(Command.new('stack_depth -i 5'))
            assert_equal(5, response.get_error)            
        end
        
        def test_handle_stack_get
            @context.status = 'running'            
            response = @handler.handle(Command.new('stack_get -i 5'))
            assert_equal(5, response.get_error)            
            
            @context.status = 'break'            
            response = @handler.handle(Command.new('stack_get -i 5 -d 0'))
            assert_equal("<response command=\"stack_get\" transaction_id=\"5\">" +
                "<stack where=\"\n\" type=\"file\" level=\"0\" filename=\"file:///test.rb\" " +
                "lineno=\"1\"/></response>", response.to_xml)

            response = @handler.handle(Command.new('stack_get -i 5'))
            assert_equal("<response command=\"stack_get\" transaction_id=\"5\">" +
                "<stack where=\"\n\" type=\"file\" level=\"0\" filename=\"file:///test.rb\" lineno=\"1\"/>" +
                "<stack where=\"\n\" type=\"file\" level=\"1\" filename=\"file:///test.rb\" lineno=\"8\"/>" +
                "</response>", response.to_xml)
        end
        
        
        # Context commands
        def test_handle_context_names
            response = @handler.handle(Command.new('context_names -i 5'))
            assert_equal("<response command=\"context_names\" transaction_id=\"5\">" +
                "<context name=\"Local\" id=\"0\"/><context name=\"Global\" id=\"1\"/>" + 
                "<context name=\"Class\" id=\"2\"/></response>", response.to_xml)
        end
        
        def test_handle_context_get
            @context.status = 'running'                         
            response = @handler.handle(Command.new('context_get -i 5'))
            assert_equal(5, response.get_error)                    

            @context.status = 'break'                         
            response = @handler.handle(Command.new('context_get -i 5'))
            assert_equal(0, response.get_error)                    
            assert_equal('0', response.get_attribute('context'))                    
           
            response = @handler.handle(Command.new('context_get -i 5 -c 1'))
            assert_equal(0, response.get_error)                    
            assert_equal('1', response.get_attribute('context'))                    
        end
        
        
        # Common Data Types
        def test_handle_typemap_get
            response = @handler.handle(Command.new('typemap_get -i 5'))
            assert_equal("<response command=\"typemap_get\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" transaction_id=\"5\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>", response.to_xml)
        end
        
        
        # Properties, variables and values
        def test_handle_property_get
            @context.status = 'running'                         
            response = @handler.handle(Command.new("property_get -i 5 -n 2+2"))
            assert_equal(5, response.get_error)                    
            
            @context.status = 'break'                         
            expected = PropertyElement.new(Kernel.eval('2+2'), '2+2')
            response = @handler.handle(Command.new("property_get -i 5 -n 2+2"))
            assert_equal(expected.to_xml, response.get_data.to_s)            
            
            response = @handler.handle(Command.new("property_get -i 5 -n wefipiwpif"))
            assert_equal(300, response.get_error)                  
        end
        
        def test_handle_property_set
            @context.status = 'running'                         
            response = @handler.handle(Command.new("property_set -i 5 -n $stdout.sync -- " + Base64.encode64('true')))
            assert_equal(5, response.get_error)                    

            @context.status = 'break'
            $stdout.sync = false                         
            response = @handler.handle(Command.new("property_set -i 5 -n $stdout.sync -- " + Base64.encode64('true')))
            assert_equal('1', response.get_attribute('success'))
            assert_equal(true, $stdout.sync)            
            
            response = @handler.handle(Command.new("property_set -i 5 -n wefipiwpif -- " + Base64.encode64('value')))
            assert_equal(300, response.get_error)                  
            assert_equal('0', response.get_attribute('success'))
        end
        
        def test_handle_property_value
            @context.status = 'running'                         
            response = @handler.handle(Command.new("property_value -i 5 -n 2+2"))
            assert_equal(5, response.get_error)                    
            
            @context.status = 'break'                         
            response = @handler.handle(Command.new("property_value -i 5 -n 2+2"))
            assert_equal('4', response.get_data.to_s)            
            
            response = @handler.handle(Command.new("property_get -i 5 -n wefipiwpif"))
            assert_equal(300, response.get_error)                  
        end
                
        # Source
        def test_handle_source
            response = @handler.handle(Command.new('source -i 5 -z thisFileWillNotBeFound'))
            assert_equal(3, response.get_error)

            response = @handler.handle(Command.new('source -i 5 -f thisFileWillNotBeFound'))
            assert_equal(100, response.get_error)
            
            response = @handler.handle(Command.new('source -i 5 -f CommandHandlerTest.rb'))
            assert_equal(0, response.get_error)
            assert_not_equal(nil, response.get_data)
                        
        end
        
        
        # Stdout, stderr redirection
        def test_handle_stdout
            capturer = @capture_manager.stdout_capturer
            capturer.state = Capturer::REDIRECT

            response = @handler.handle(Command.new('stdout -i 5 -c 0'))
            assert_equal('1', response.get_attribute('success'))
            assert_equal(Capturer::DISABLE, capturer.state)
        end
        
        def test_handle_stderr
            capturer = @capture_manager.stderr_capturer
            capturer.state = Capturer::REDIRECT

            response = @handler.handle(Command.new('stderr -i 5 -c 0'))
            assert_equal('1', response.get_attribute('success'))
            assert_equal(Capturer::DISABLE, capturer.state)
        end
        
        
        ######################################
        # Extended Commands
        ######################################
        
        def test_handle_stdin
            response = @handler.handle(Command.new('stdin -i 5 -c 1'))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end

	
        def test_handle_break
            @context.status = 'running'                         
            response = @handler.handle(Command.new('break -i 5'))
            assert_equal("<response status=\"break\" command=\"break\" transaction_id=\"5\" " +
                "reason=\"ok\"/>", response.to_xml)                    
            assert_equal(AbstractContext::BREAK, @context.status)
            
            response = @handler.handle(Command.new('break -i 5'))
            assert_equal(5, response.get_error)                    
        end

        def test_handle_eval
            @context.status = 'running'                         
            response = @handler.handle(Command.new("eval -i 5 -- " + Base64.encode64('2+2')))
            assert_equal(5, response.get_error)                    
            
            @context.status = 'break'                         
            expected = PropertyElement.new(Kernel.eval('2+2'), '2+2')
            response = @handler.handle(Command.new("eval -i 5 -- " + Base64.encode64('2+2')))
            assert_equal(expected.to_xml, response.get_data.to_s)            
            assert_equal('1', response.get_attribute('success'))                  
            
            response = @handler.handle(Command.new("eval -i 5 -- " + Base64.encode64('+!fpefnpwen')))
            assert_equal(206, response.get_error)                  
            assert_equal('0', response.get_attribute('success'))                  
        end

        def test_handle_expr
            response = @handler.handle(Command.new("expr -i 5 -- SGVsbG8=\n"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end

        def test_handle_exec
            response = @handler.handle(Command.new("exec -i 5 -- SGVsbG8=\n"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end

        # Spawnpoint commands
        def test_handle_spawnpoint_set
            response = @handler.handle(Command.new("spawnpoint_set -i 5"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end

        def test_handle_spawnpoint_get
            response = @handler.handle(Command.new("spawnpoint_get -i 5 -d 1"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end

        def test_handle_spawnpoint_update
            response = @handler.handle(Command.new("spawnpoint_update -i 5 -d 1"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end

        def test_handle_spawnpoint_remove
            response = @handler.handle(Command.new("spawnpoint_remove -i 5 -d 1"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end

        def test_handle_spawnpoint_list
            response = @handler.handle(Command.new("spawnpoint_list -i 5"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end

        # interact - Interactive Shell
        def test_handle_interact
            response = @handler.handle(Command.new("interact -i 5 -m mode -- base64(code)"))
            assert_equal(4, response.get_error, 'Please provide unit test for implementation')
        end
    end
end

###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'logger'

module XoredDebugger
    class TestIOManager
		include Logger
	
        def initialize(printer)            
            @printer = printer

            @n = 0

    #       @commands = [
    #           'feature_get -n language_name',             
    #           'step_into', 
    #           'context_get',
    #           'context_names',
    #           'context_get',
    #           'stack_depth',
    #           'breakpoint_set -t line -f test.rb -n 2'
    #       ]

           @commands = [
               'context_get -c 1',
               'feature_set -n max_children -v 256',
               'status',   
               'stop',
               'stack_get',
               'context_get',
               'stdin -c 1', # redirect stdin
               'feature_get -n supports_async',
               'breakpoint_set -t line -o == -h 32 -f file:///D:/data/programming/java/runtime-New_configuration_test/Test%20Project/my.rb -n 2',
               'breakpoint_get -d 1',
               'breakpoint_remove -d 1', 
               'status',
               'stack_depth',
               'step_into',
               'stack_get',
               'stack_get -d 0',
               'context_get',
               'run'
           ]

    #        @commands = [
    #            'status',
    #            'step_over',
    #            'step_over',
    #            'step_over',
    #            'step_over',
    #            'step_over',
    #            'step_over',
    #            'stack_get',
    #            'context_get',
    #            'property_get -n tank -k 23455',
    #            'step_over',
    #            'status',
    #            'run'
    #        ]
        end

        def has_data?
            false
        end

        def receive
            cmd = if @n < @commands.length          
                command = @commands[@n] + " -i #@n"
                @n += 1
                command
            else
                "dummy"
            end
            
            log('<<< ' + command)

            cmd
        end

        def send(command, data)
            response = @printer.print(command, data)

            log('>>> ' + response)           
        end

        def close
            log('Closing...')
        end
    end # class TestIOManager
end # module XoredDebugger
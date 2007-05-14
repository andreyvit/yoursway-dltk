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
# class TestIO
#

class TestIO
    def initialize(logger, printer)
        @logger = logger
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
           'feature_set -n max_children -v 256',
           'status',   
           'stack_get',
           'context_get',
           'stdin -c 1', # redirect stdin
           'feature_get -n supports_async',
           'breakpoint_set -t line -f file:///C:%5Cdata%5Cprogramming%5Cjava%5Cruntime-New_configuration%5CTest%5Ctank.rb -n 2',
           'breakpoint_remove -d 1', 
           'status',
           'stack_depth',
           'step_into',
           'stack_get',
           'stack_get -d 0',
           'context_get',
           'run',
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
        
        unless @logger.nil?
            @logger.puts('<<< ' + command)
        end

        cmd
    end

    def send(command, data)
        response = @printer.print(command, data)

        unless @logger.nil?
            @logger.puts('>>> ' + response)
        end
    end

    def close
        puts "Closing..."
    end
end

end # module
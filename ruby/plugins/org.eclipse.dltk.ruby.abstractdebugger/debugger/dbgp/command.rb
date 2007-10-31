###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'base64'

module XoredDebugger

    class Command
        def parse_args
            @arg_map = {}
            name = nil
            @args.split.each { |token|
                if (name.nil?)
                    name = token
                else
                    @arg_map[name] = token
                    name = nil
                end
            }
        end
        private :parse_args

        def initialize(command)
            @command = command

            if (@command =~ /(\w+)\s(.+)--(.*)/)
                @name = $1
                @args = $2
                @data = Base64.decode64($3.to_s.strip)           
            elsif (@command =~ /(\w+)\s(.+)/)
                @name = $1
                @args = $2
                @data = nil
            end

            parse_args
        end 
    
        attr_reader :name, :data

        def arg_with_default(name, default)
            value = self.arg(name)
            value.nil? ? default : value
        end

        def arg(name)
            @arg_map[name]
        end

       
        def to_s
            @command
        end
    end # class Command

end # module
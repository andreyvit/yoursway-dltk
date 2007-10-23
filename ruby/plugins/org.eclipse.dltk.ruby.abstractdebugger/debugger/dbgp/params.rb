###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger    
    class Params 
        def initialize
            @host   = ENV['DBGP_RUBY_HOST']
            @port   = ENV['DBGP_RUBY_PORT'].to_i
            @key    = ENV['DBGP_RUBY_KEY']
            @script = ENV['DBGP_RUBY_SCRIPT']
            @test   = ENV['DBGP_RUBY_TEST']
            @test   = @test.nil? ? false : @test == '1' ? true : false
            @log    = ENV['DBGP_RUBY_LOG']
        end
        
        attr_reader :host, :port, :key, :script, :test, :log
        
        def valid?
            not (@host.nil? or @port == 0 or @key.nil? or @script.nil?)
        end
        
        def print(out)
            out.puts("Time:   #{Time.new.to_s}")
            out.puts("Host:   #{@host.to_s}")
            out.puts("Port:   #{@port.to_s}")
            out.puts("Key:    #{@key.to_s}")
            out.puts("Script: #{@script.to_s}")
            out.puts("Test:   #{@test.to_s}")
			
			out.puts('Input args:')
			ARGV.each { |arg|
				out.puts("\t#{arg}")
			}

			out.puts('Include paths:')
			$:.each { |path|
				out.puts("\t#{path}")
			}
        end
    end # class Params 
end # module XoredDebugger
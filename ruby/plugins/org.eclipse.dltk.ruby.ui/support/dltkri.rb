require 'rdoc/ri/ri_driver'

ENV["ri"] = "-f html"

ri = RiDriver.new

while true do 
	s = STDIN.gets.chop!
	begin		
		ri.get_info_for(s)		
		STDOUT.puts "\nDLTKDOCEND"
		STDOUT.flush
	rescue RiError => e		
		STDOUT.puts "#{e}"
		STDOUT.puts "\nDLTKDOCEND"
		STDOUT.flush		
		next
	end
end

STDOUT.puts "/bye"
STDOUT.puts "\nDLTKDOCEND"
STDOUT.flush

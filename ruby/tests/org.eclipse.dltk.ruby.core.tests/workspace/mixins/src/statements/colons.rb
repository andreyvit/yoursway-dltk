class Class1
	class Class2
		
		#doc for foo
		def foo
			(bar)::Class2
			CGI
		end
		
		#doc for bar
		def bar
			c = Class2.new
			c.private_methods
			Class1
		end
	end	
end
n = Class1::Class2.new
n ## expr n => Class1::Class2
class Foo
	
	@abx = 45
	
	@@clvar = 42

	def cool
		@abx = "ab"
		@@clvar = "be" 
	end
	
	@abx ## expr @abx => Fixnum%
	
	@@clvar ## expr @@clvar => Fixnum%
		

end

class Foo

	def wow(a,x)
		@abx ## expr @abx => String%
		@@clvar ## expr @@clvar => String%
	end

end
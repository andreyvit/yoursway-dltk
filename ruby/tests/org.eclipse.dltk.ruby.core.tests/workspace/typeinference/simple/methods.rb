class Foo
	def doo
		creator.cool_method
	end
	
	def creator
		Bar.new
	end
	
end

class Bar
	
	def cool_method
		42
	end
	
end

class Baz
	def bobobo
		Bar.new.cool_method
		f = Foo.new
		f.doo
	end
end

## meth Foo.creator => Bar
## meth Baz.bobobo => Fixnum
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
		ff = Foo.new
		ff.doo
	end
end
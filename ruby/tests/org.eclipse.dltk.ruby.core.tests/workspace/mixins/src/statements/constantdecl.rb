
class Bar
	Foo = 12
	
	def func
		boz(Foo)
	end
	
	def boz(foo)
		foo
	end
end

## meth Bar.func => Fixnum

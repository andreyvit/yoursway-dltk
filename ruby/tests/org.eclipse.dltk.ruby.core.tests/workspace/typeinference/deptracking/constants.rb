
class Bar
	Foo = boz(17)
	
	def func
		boz(Foo)
	end
	
	def boz(foo)
		foo
	end
end

## meth Bar.func => Fixnum

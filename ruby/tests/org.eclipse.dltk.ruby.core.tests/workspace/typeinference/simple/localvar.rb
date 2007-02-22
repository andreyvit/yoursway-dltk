
class Bar
	def func
		test = biz(15)
		boz(test)
	end
	
	def boz(foo)
		foo
	end
	
	def biz(foo)
		foo
	end
end

## meth Bar.biz => Fixnum
## meth Bar.boz => Fixnum
## meth Bar.func => Fixnum


class Bar
	def func
		if 2 > 3
			boz(24)
		else
			boz(42)
		end
	end
	
	def boz(foo)
		foo
	end
end

## meth Bar.func => Fixnum

class FooRecursion
	def foo(x)
		if x > 0
			foo(x-1)
		else
			42
		end
	end
	
	def bar
		foo ## expr foo => Fixnum%
	end
end


class Foo
	def foo(x)
		if x > 0
			foo(x-1)
		else
			42
		end
	end
end

## meth Foo.foo => Fixnum

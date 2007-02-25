
class MethodsArguments
	class Bar
		def foo
			bar(7) ## expr bar => Fixnum
		end
		
		def bar(x)
			x
		end
	end
end

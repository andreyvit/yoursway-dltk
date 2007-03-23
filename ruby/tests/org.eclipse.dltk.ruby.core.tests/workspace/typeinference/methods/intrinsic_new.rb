
class MethodsIntrinsicNew
	class Bar
		def foo
			Foo.new ## expr Foo.new => MethodsIntrinsicNew{Foo%
		end
	end
	
	class Foo
	end
end

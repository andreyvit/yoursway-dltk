
class MethodsIntrinsicNew
	class Bar
		def foo
			Foo.new ## expr Foo.new => MethodsIntrinsicNew::Foo.new
		end
	end
	
	class Foo
	end
end


class Foo
end

class Boz
	class Foo
	end
	
	class Bar
		Foo ## expr Foo => Boz{Foo
	end
end

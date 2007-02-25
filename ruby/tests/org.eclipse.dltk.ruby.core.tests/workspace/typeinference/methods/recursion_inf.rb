class Foo
	def foo
		foo
	end
	
	def bar
		foo ## expr foo => recursion
	end
eend

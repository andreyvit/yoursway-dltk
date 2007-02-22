
class Foo

	def ultimate_answer
		42
	end
	
	def dining_philosopher
		ultimate_answer
	end

end

class Bar
	def boz
		Foo.new
	end
	
	def func
		boz.dining_philosopher
	end
end

## meth Foo.ultimate_answer => Fixnum
## meth Foo.dining_philosopher => Fixnum
## meth Bar.boz => Foo
## meth Bar.func => Fixnum

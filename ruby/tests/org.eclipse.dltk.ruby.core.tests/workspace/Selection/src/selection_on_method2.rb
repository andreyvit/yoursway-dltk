class Bar
	def func
		boz.dining_philosopher
	end
	
	def boz
		Foo.new
	end
end

class Foo
	
	def dining_philosopher
		ultimate_answer
	end

	def ultimate_answer
		42
	end

end

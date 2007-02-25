
class Bar
	def func
		boz.dining_philosopher
	end
	
	def boz
		Foo42.new.dining_philosopher
		Foo42::
	end
end

class Foo42
	class Inner
	end
	
	def dining_philosopher
		ultimate_answer
	end

	def ultimate_answer
		42
	end

end

class BozSelfSingletons
	class Bar
		xxx
		self ## expr self => BozSelfSingletons{Bar
		
		def foo
			xxx
			self ## expr self => BozSelfSingletons{Bar%
		end
		
		def self.foo
			xxxx
			self ## expr self => BozSelfSingletons{Bar
		end
		
		def Bar.foo
			xxx
			self ## expr self => BozSelfSingletons{Bar
		end
	end
end


class BozSelfSingletons
	class Bar
		self ## expr self => BozSelfSingletons::Bar
		
		def foo
			self ## expr self => BozSelfSingletons::Bar.new
		end
		
		def self.foo
			self ## expr self => BozSelfSingletons::Bar
		end
		
		def Bar.foo
			self ## expr self => BozSelfSingletons::Bar
		end
	end
end


class BozSelf
	class Bar
		self ## expr self => BozSelf{Bar
		
		def foo
			self ## expr self => BozSelf{Bar.new
		end
	end

	def foo
		self ## expr self => BozSelf.new
	end

	self ## expr self => BozSelf
end

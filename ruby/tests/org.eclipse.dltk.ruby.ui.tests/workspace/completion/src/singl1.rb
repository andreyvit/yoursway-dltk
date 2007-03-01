class Foo66
	
	class <<self
		def cool(x,y,z)
			p "1"
		end
	end
	
	def self.cool2
		p "2"
		Foo66.new
	end
	
	def inst_meth
	end
	
end

Foo66.cool
Foo66.cool2
Foo66.
MIX = "yo"

class Const
	MIX = 45
	
	def lin
		MIX ## expr MIX => Fixnum%
	end
	
	class Lol
	
		MIX ## expr MIX => Fixnum%
	
		def lin2
			MIX ## expr MIX => Fixnum%
		end	
	
	end
	
end

MIX ## expr MIX => String%

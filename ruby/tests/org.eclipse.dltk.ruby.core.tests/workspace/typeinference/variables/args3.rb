class Args3

	def wow
		Usable.new.work (42, Args2Class.new)
	end

end


class Usable
	def work(x,y)
		xxx
		x ## expr x => Fixnum%
		xxx
		y ## expr y => Arg2Class% 
	end
end

class Arg2Class

	def use1
	
		z = Usable.new
		z.work (34, self)
	
	end

end


class MClass
end

## get MClass

def top_level
end

## get top_level

class MAbc
	def inst1
	end
		
end


## get MAbc%{inst1


class MAbc
	class << self
		def meta3
		end
	end
	
	def self.meta4
	end
	
end

## get MAbc{meta3
## get MAbc{meta4

class TopGlobal
	class ::MAbc
		def inst2
			def inst3
			end
		end
	end
	def inst1
	end
end


## get MAbc%{inst2
## get MAbc%{inst3
## get TopGlobal%{inst1

def (::TopGlobal).meta1
end

## get TopGlobal{meta1



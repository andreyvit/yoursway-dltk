class SomeExt
	def SomeExt.foo
	end
end

## get SomeExt{foo

class MAbc
end

def MAbc.meta1
end

def MAbc::meta2
end

## get MAbc{meta1
## get MAbc{meta2

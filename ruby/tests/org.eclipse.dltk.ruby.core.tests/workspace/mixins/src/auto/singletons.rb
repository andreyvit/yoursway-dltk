class YAClass
end

a = YAClass.new

def a.singl1
	42
end

## get a%v{singl1

def (a.class).singl2
	43
end

## get YAClass{singl2

class << a
	class Internal
		def foo
		end
	end
end

## get a%v{Internal
## get a%v{Internal%{foo

def (::YAClass).doo
end

## get YAClass{doo
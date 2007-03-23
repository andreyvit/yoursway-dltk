class Abc
end

class XYZ
	class Wow
	end
end

## get Abc
## get XYZ
## get XYZ{Wow

class ::Global1
	class ::Global2
		class ::XYZ::Wow::Subwow
		end
	end
end

## get Global1
## get Global2
## get XYZ{Wow{Subwow



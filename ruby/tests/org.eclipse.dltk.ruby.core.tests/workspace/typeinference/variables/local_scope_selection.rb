
test = 3
class Boz
	test = 7
	class Bar
		test = 18
		def func
			test = "Abc"
			test = "Def"
			test ## localvar test => Str
		end
		test ## localvar test => Fixnum
	end
end

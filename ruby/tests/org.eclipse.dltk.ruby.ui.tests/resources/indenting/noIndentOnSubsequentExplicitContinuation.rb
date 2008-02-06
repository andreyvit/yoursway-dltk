def foo(arg)
	arg.boz 1, 2 \
		, 3, 4 \#n#
end
###
def foo(arg)
	arg.boz 1, 2 \
		, 3, 4 \
		
end

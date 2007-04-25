$var1 = "abc"

def foo
	$var2 = 444
	
	$var1 ## expr $var1 => String%
	$var2 ## expr $var2 => Fixnum%	
end

$var1 ## expr $var1 => String%
$var2 ## expr $var2 => Fixnum%
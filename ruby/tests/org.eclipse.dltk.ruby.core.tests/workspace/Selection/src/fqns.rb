class Foo42
 class Doo42
  class Coo42
  
   def coo_method42
   end
   
   def (::Foo42).foo_method3
    def (::Foo42).foo_method4
    end
   end
   
  end
  
  def doo_method42
  end
  
 end
	
 def (::Foo42).foo_method2
 end
 
 def Foo42.foo_method22
 end
 
 def (::Foo42.class).foo_method23
 end	
 
 def foo_method42
 
  ::Foo42::Inner42.new

  def (Foo42::Doo42::Coo42).coo_method2		
  end

  def (::Foo42::Doo42).doo_method2
  end

  f = Foo42.new
  d = ::Foo42::Doo42.new
  c = ::Foo42::Doo42::Coo42.new

  f.foo_method 42
  Foo42.foo_method2
  Foo42.foo_method22 
  Foo42.class.foo_method23 #wtf?
  Foo42.foo_method3
  Foo42.foo_method4

  d.doo_method42
  Foo42::Doo42.doo_method2

  c.coo_method42
  Foo42::Doo42::Coo42.coo_method2
 end
 
 class ::Foo42::Inner42
 end

end

# <line1> <col1> <line2> <col2>

## 1 9 1 8
## 1 7 1 8
## 1 8 1 8
## 2 9 2 9
## 3 10 3 10
## 5 10 5 10
## 8 12 1 8
## 9 13 1 8
## 8 22 8 22
## 9 23 9 23
## 15 15 15 15
## 23 7 1 8
## 31 6 1 8
## 31 12 57 12
## 33 9 1 8
## 33 14 2 10
## 33 19 3 10
## 41 20 3 10
## 54 29 33 25
## 51 23 36 27

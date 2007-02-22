class Foo
 class Doo
  class Coo
  
   def coo_method
   end
   
   def (::Foo).foo_method3
    def (::Foo).foo_method4
    end
   end
   
  end
  
  def doo_method
  end
  
 end
	
 def (::Foo).foo_method2
 end
 
 def Foo.foo_method22
 end
 
 def (::Foo.class).foo_method23
 end	
 
 def foo_method
 
  ::Foo::Inner.new

  def (Foo::Doo::Coo).coo_method2		
  end

  def (::Foo::Doo).doo_method2
  end

  f = Foo.new
  d = ::Foo::Doo.new
  c = ::Foo::Doo::Coo.new

  f.foo_method 
  Foo.foo_method2
  Foo.foo_method22 
  Foo.class.foo_method23 #wtf?
  Foo.foo_method3
  Foo.foo_method4

  d.doo_method
  Foo::Doo.doo_method2

  c.coo_method
  Foo::Doo::Coo.coo_method2
 end
 
 class ::Foo::Inner
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
## 54 23 33 25
## 51 19 36 27

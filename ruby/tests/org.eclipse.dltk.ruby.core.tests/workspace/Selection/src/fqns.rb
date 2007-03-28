class Foo42   ## 9
 class Doo42  ## 10
  class Coo42  ## 11
  
   def coo_method42 ## 15
   end
   
   def (::Foo42).foo_method3  ## 13
    def (::Foo42).foo_method4 ## 15
    end
   end
   
  end
  
  def doo_method42 ## 12
  end
  
 end
	
 def (::Foo42).foo_method2 ## 11
 end
  
 def foo_method42
 
  ::Foo42::Inner42.new ## 14

  def (::Foo42::Doo42::Coo42).coo_method2		## 24
  end

  def (::Foo42::Doo42).doo_method2 ## 18
  end

  f = Foo42.new ## 9
  d = ::Foo42::Doo42.new  
  c = ::Foo42::Doo42::Coo42.new

  f.foo_method42  ## 10
  Foo42.foo_method2 ## 13 
  Foo42.foo_method3 ## 13
  Foo42.foo_method4 ## 13

  d.doo_method42 ## 10 
  Foo42::Doo42.doo_method2 ## 22 

  c.coo_method42  ## 11 
  Foo42::Doo42::Coo42.coo_method2 ## 28 
 end
 
 class ::Foo42::Inner42  ## 20 
 end

end

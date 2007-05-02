###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################
Bar = Class.new
puts Bar.name
Boz = Bar
puts Boz.name

Customer = Struct.new(:name, :address, :email)

class MyCustomer < Struct.new(:name, :address, :email)
  
  def pay(amount)
  end

end

class Foo

  WORD = "word"

  @@foo = 0
  @foo = 0

  attr_accessor :foo

  def initialize(a)
    @foo = a
  end

  def bar; @foo += 1; end

  def self.bar
    @foo += 1
  end

  def fubar
    @@foo += 1
  end

  def Foo.fubar
    @@foo += 1
  end

  def (Foo.new("word")).xxx
    puts "5!!!"
  end

  def WORD.word
    puts "word!!!"
  end

  class << self
    
    def boz
      @foo += 1
    end

    def self.boz
      puts "Secret area found!"
    end

  end

  class Foo
    
    def self.foo
      puts "Foo::Foo.foo called!"
    end

  end

end

def Foo.mega
  puts "hello, world!"

  def Foo.verygood
    puts "verygood"
  end 
end

class Foo
  def self.megax
    puts "hellox, world!"

    def Foo.verygood
      puts "verygood"
    end 
  end
end

def (::Foo).good
  puts "good-bye, world!"
end

Foo.send(:define_method, :something, proc do
  puts "I'm a mega-method!"
end)

def test(x, &block)
  puts x.to_s + " = " + eval(x, block.binding).to_s
end

2.times do 
  puts 
  test "Foo.bar" do end
  test "Foo.boz" do end
  test "Foo.fubar" do end

  puts
  test "Foo::bar" do end
  test "Foo::boz" do end
  test "Foo::fubar" do end
end

f = Foo.new(0)

3.times do 
  puts 
  test "f.bar" do end
  test "f.fubar" do end
end

2.times do 
  puts 
  test "Foo.bar" do end
  test "Foo.boz" do end
  test "Foo.fubar" do end
  puts
  test "Foo::bar" do end
  test "Foo::boz" do end
  test "Foo::fubar" do end
end



test "5 + 4" do end

class Fixnum
  alias_method :old_plus, :+

  def +(z)
    old_plus(z).to_f * 0.999999
  end

  def mega(a, b, c=nil, d=nil, *args, &block)
    puts "hmmm"
  end
end

SuperFoo = (class << Foo; self; end)
SuperFoo.boz

test "5 + 4" do end

Foo::WORD.word

Foo.mega
Foo.good
Foo.verygood
Foo.megax
Foo::Foo.verygood

Foo.send(:public, :something)
f.something
Foo.send(:private, :something)


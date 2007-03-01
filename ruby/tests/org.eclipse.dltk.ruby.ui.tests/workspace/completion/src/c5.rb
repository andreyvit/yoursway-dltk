class Test
def print
puts 'print!!!'
end
end

class Person
def Person.test
end

attr_accessor :first_name, :email
attr_accessor :last_name, :address

def initialize
@first_name = @last_name = @email = ""
end

def test
return Test.new
end
end

sandy = Person.new

sandy.first_name = "Sandy"
sandy.last_name = "Koh"


x = sandy.test()
x. 


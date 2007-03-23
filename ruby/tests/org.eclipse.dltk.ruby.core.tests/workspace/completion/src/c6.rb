class Foo
def initialize
p 'Foo'
end

def foo_f
end
end

class Bar
def initialize
p 'Bar'
end

def bar_f
end
end

t = false

x = (t ? Foo : Bar).new
t = Bar
t.

foo = Foo.new
bar = Bar.new
###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

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

def self.abcd; end

end

t = false

x = (t ? Foo : Bar).new
t = Bar
t.

foo = Foo.new
bar = Bar.new
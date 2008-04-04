###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

#JRubySourceParser.COMMA_FIXER1
a :b =>,do
end

a :b =>, do
end

a(:b =>,)do
end

a( :b =>, ) do
end


#JRubySourceParser.COMMA_FIXER2
d e,do
end

d e, do
end

d(e,)do
end

d( e, ) do
end


#JRubySourceParser.COMMA_FIXER3
f :g => :h, :i do
end

f(:g => :h, :i) do
end

f( :g => :h, :i ) do
end


#JRubySourceParser.COMMA_FIXER4
f(:g => :h,)

f( :g => :h, )


#JRubySourceParser.COMMA_FIXER5
f(g,)

f( g, )


#JRubySourceParser.COMMA_FIXER6
f(:g => :h, :i)

f( :g => :h, :i )

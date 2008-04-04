###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

#JRubySourceParser.COMMA_FIXER_UNSAFE1
a :b => :c,

a :b => :c, 

a(:b => :c,)

a(:b => :c,) 

a( :b => :c, )

a( :b => :c, ) 


#JRubySourceParser.COMMA_FIXER_UNSAFE2
a b,

a b, 

a(b,)

a(b,) 

a( b, )

a( b, ) 


#JRubySourceParser.COMMA_FIXER_UNSAFE3
f :g => :h, :i

f :g => :h, :i 

f(:g => :h, :i)

f(:g => :h, :i) 

f( :g => :h, :i )

f( :g => :h, :i ) 

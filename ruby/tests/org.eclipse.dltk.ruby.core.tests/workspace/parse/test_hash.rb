###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

#JRubySourceParser.HASH_FIXER1
a :b =>do
end

a :b => do
end

a(:b =>)do
end

a( :b => ) do
end


#JRubySourceParser.HASH_FIXER2
j :k =>

j :k => 

j{k :l =>}

j{k :l => }

j(k :l =>)

j(k :l => )

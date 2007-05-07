###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

puts smt; if foo; unless bar ## if unless ### if
case 40; when 3 then puts 1; when 10 ## case:when ### /end
if foo then puts 1 else puts 2 end end ## /end ### /end /end
end ## /end

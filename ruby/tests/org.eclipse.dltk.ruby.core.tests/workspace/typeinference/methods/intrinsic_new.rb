###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################


class MethodsIntrinsicNew
	class Bar
		def foo
			Foo.new ## expr Foo.new => MethodsIntrinsicNew{Foo%
		end
	end
	
	class Foo
	end
end

###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Fooz
end

class Bozz
	class Fooz
	end
	
	class Barz
		Fooz 
	end
end

## get Fooz
## get Bozz
## get Bozz{Fooz
## get Bozz{Barz

###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

import types
def make_type( element ):
	if type( element ) == types.FunctionType:
		return "function:" + element.__name__
	if type( element ) == types.ClassType:
		return "class"
	if type( element ) == types.IntType:
		return "number"
	if type( element ) == types.LongType:
		return "number"
	if type( element ) == types.FloatType:
		return "number"
	if type( element ) == types.StringType:
		return "string"
	if type( element ) == types.UnicodeType:
		return "string"
	if type( element ) == types.BooleanType:
		return "boolean"
	if type( element ) == types.ComplexType:
		return "number"
	if type( element ) == types.TupleType:
		return "tuple"
	if type( element ) == types.ListType:
		return "list"
	if type( element ) == types.DictType:
		return "dict"
	if type( element ) == types.FileType:
		return "file"
	if type( element ) == types.InstanceType:
		return ( "class:" + element.__class__.__name__ + " instance" ).replace( " ", "#")
	if type( element ) == types.ModuleType:
		return "module"
	return "unknown element"
def out( name, var ):
	print name + " " + make_type( var )

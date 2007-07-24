###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

import compiler
import compiler.ast
import os
class MakeScriptTestCase( object ):
	# Initialize from file_name and index. Index used to output junit test.
	def __init__( self, file_name, index, pluginPath ):
		self.file_name = file_name
		self.makeNFN()
		self.test_index= index
		self.inst_index = 0		
		self.parentName = "module"
		self.pluginPath = pluginPath
		self.after_fields = []
		
		self.inClass = 0
		self.inMethod = 0
		self.added_fields = {} # already declated fiels in selected class
		try:
			self.ast = compiler.parseFile( file_name )
			self.content = ""
		except:
			self.content = "\tpublic void testModelGen%s( ) throws Exception\n\t{" %( self.test_index )
			self.content += "\t\tthrow new RuntimeException(\"Failed to parse file:%s\");\n\t}" %( file_name.replace( os.sep, "/" ) )
			return						
		if self.ast is not None:
			self.makeContent()
	# Make self.content to appropriate test.
	def makeContent( self ):
		self.content = "\tpublic void testModelGen%s( ) throws Exception {" %( self.test_index )
		self.content += """
		String prj = "%(pluginPath)s";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("%(fName)s"));

		assertNotNull("Module %(fName)s not found", module);
		assertEquals("%(fName)s", module.getElementName());
		""" %{ "index": self.test_index, "fName": self.file_name, "pluginPath": self.pluginPath }
		
		self.content += "\n"
		self.addChildTests( self.ast )
		self.content += "\n\t}"		
	
	def addChildTests( self, node, depth = 2 ):
		childs = node.getChildNodes( )
		#self.content += "\t"*depth + "//Nodes count %s" %( len( childs ) ) + "\n"
		#self.content += "\t"*depth + str( node.__class__ ) + "\n"
		
		oldParentName = self.parentName
		oldInClass = self.inClass
		oldInMethod = self.inMethod
		hasSub = 0
		
		if node.__class__ == compiler.ast.Function:
			self.inMethod = 1			
			hasSub = 1
			self.content += "\t"*depth + "//Function test:%s" %( node.name ) + "\n"			
			instName = "method" + node.name + "%d" %( self.inst_index )
			self.parentName = instName
			self.content += "\t"*depth + "{\n"
			self.content += "\t"*depth +  "IMethod %s;" %( instName ) + "\n"
			self.inst_index += 1			
			args = node.argnames
			self.content += "\t"*(depth+1) +  "IModelElement[] %sChilds = %s.getChildren();" %( oldParentName, oldParentName ) + "\n"
			self.content += "\t"*(depth+1) + instName + " = ModelTestUtils.getAssertMethod( %sChilds, \"%s\", %d );" %( oldParentName, node.name, len( args ) ) + "\n"
			
			if( len( args ) > 0 ):
				argss = ""
				first = 1
				for arg in args:
					if arg is not None:
						if first: first = 0
						else: argss += ", "
						argss += "\"" + str( arg ) + "\""
				ar = (instName, argss  )
				self.content += "\t"*(depth+1) + "ModelTestUtils.assertParameterNames( %s, new String[] {%s} );" % ar + "\n"
			
		if node.__class__ == compiler.ast.Class:
			self.inClass = 1
			hasSub = 1
			self.content += "\t"*depth + "//Class test" + "\n"
			instName = "class" + node.name + "%d" %( self.inst_index )
			self.parentName = instName
			self.content += "\t"*depth + "{\n"
			self.content += "\t"*depth +  "IType %s;" %( instName ) + "\n"
			self.inst_index += 1			
			self.content += "\t"*(depth+1) +  "IModelElement[] %sChilds = %s.getChildren();" %( oldParentName, oldParentName ) + "\n"
			self.content += "\t"*(depth+1) + instName + " = ModelTestUtils.getAssertClass( %sChilds, \"%s\" );" %( oldParentName, node.name ) + "\n"
		if node.__class__ == compiler.ast.Assign:				
			if ( self.inClass and not self.inMethod ) or self.parentName == "module":				
				try:# can raise exceptions if not simple assert here.                                
					child_name = ( node.getChildren()[0].name )
					self.content += "\t"*(depth) + "{\n"
					self.content += "\t"*(depth+1) +  "IModelElement[] %sChilds = %s.getChildren();" %( oldParentName, oldParentName ) + "\n"
					self.content += "\t"*(depth+1) + """IField fieldValue = ModelTestUtils.getAssertField( %(className)s, "%(childName)s");\n""" % { "childName": child_name, "className": self.parentName + "Childs"  }                                        
					self.content += "\t"*(depth) + "}\n"
				except:
					pass
			elif self.inClass and self.inMethod:
				try:# can raise exceptions if not simple assert here.
					child_base = ( node.getChildren()[0].getChildren()[0].name )
					child_name = ( node.getChildren()[0].getChildren()[1] )					
					if child_base == "self":						
						self.after_fields.append( child_name )                                                
				except:	
					pass
		for child in childs:
				if node.__class__ in [ compiler.ast.Class, compiler.ast.Function, compiler.ast.Assign ]:
					self.addChildTests( child, depth + 1 )
				else:
					self.addChildTests( child, depth )	                
		if self.parentName != oldParentName:
			self.parentName = oldParentName
			self.content += "\t"*depth + "}\n"
		if hasSub:
			if node.__class__ == compiler.ast.Function:
				if len( self.after_fields ) > 0:
					for field in self.after_fields:
						if str( node ) in self.added_fields.keys():
							fields = self.added_fields[ str( node ) ]
							if not field in fields:
								fields.append( field )
								self.content += "\t"*(depth) + "{\n"
								self.content += "\t"*(depth+1) +  "IModelElement[] %sChilds = %s.getChildren();" %( oldParentName, oldParentName ) + "\n"
								self.content += "\t"*(depth+1) + """IField fieldValue = ModelTestUtils.getAssertField( %(className)s, "%(childName)s");\n""" % { "childName": field, "className": self.parentName + "Childs"  }
								self.content += "\t"*(depth) + "}\n"
						else:
							fields = [ field ]
							self.added_fields[ str( node ) ] = fields
							self.content += "\t"*(depth) + "{\n"
							self.content += "\t"*(depth+1) +  "IModelElement[] %sChilds = %s.getChildren();" %( oldParentName, oldParentName ) + "\n"
							self.content += "\t"*(depth+1) + """IField fieldValue = ModelTestUtils.getAssertField( %(className)s, "%(childName)s");\n""" % { "childName": field, "className": self.parentName + "Childs"  }
							self.content += "\t"*(depth) + "}\n"
							self.inst_index += 1
				self.after_fields = []               
		self.inMethod = oldInMethod
		self.inClass = oldInClass
	def makeNFN( self ):
		import os
		if self.file_name.find( os.sep ) != -1:
			pos = self.file_name.rfind( os.sep )
			self.file_name = self.file_name[ pos + 1: ]
	# Final Content
	Content = property( lambda self: self.content )

if __name__ == "__main__":
	mt = MakeScriptTestCase( "module0.py", 0 )
	print mt.Content

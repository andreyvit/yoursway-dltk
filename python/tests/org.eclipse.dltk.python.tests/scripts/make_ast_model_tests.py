###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

#!/usr/bin/python
# -*- coding: utf-8 -*-
# Desired to create model tests from ast tree.
try:
        import psyco
        psyco.full()
except:
	print "No Psyco found. may take long time"
import model_test_maker
import zipfile
if __name__ == "__main__":
	import glob
	import os
	tests_count = 50
	files_zip = "../workspace/src.zip".replace( "/", os.sep )
	files_zip_arch = "../../../../../../workspace/src.zip".replace( "/", os.sep )
	proj_dir = "src"
	file_name = "../src/com/xored/dltk/python/tests/model/GeneratedModelTests".replace( "/", os.sep )
	plugin_path = "pytests"
	zip = zipfile.PyZipFile(files_zip) 
	files = [ n for n in zip.namelist() if n.endswith(".py")]
	
	base_0 = """
package org.eclipse.dltk.python.tests.model;

import junit.framework.Test;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.core.tests.util.ModelTestUtils;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;

public class GeneratedModelTests%d extends AbstractModelTests
{
	public GeneratedModelTests%d(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( GeneratedModelTests%d.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		setUpScriptProject( "pytests" );
		// Extract all files from selected zip file.
		
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests" );
	}
"""
	ending_0 = """
}
	"""
	index = 0
	file_index = 0
	output = open( file_name + "%s.java" %( file_index ), "w" )
	content = ""
	for test_file in files:
		maker = model_test_maker.MakeScriptTestCase( test_file, index, plugin_path )
		content += maker.Content + "\n"
		index += 1
		if index % tests_count == 0:
			print "output is:", output
			output.write( base_0 %( file_index, file_index, file_index ) + content + ending_0 )
			output.close()
			content = ""
			file_index += 1
			output = open( file_name + "%s.java" %( file_index ), "w" )			
	output.write( base_0 %( file_index, file_index, file_index ) + content + ending_0 )
	output.close()
	content = ""
	#print content

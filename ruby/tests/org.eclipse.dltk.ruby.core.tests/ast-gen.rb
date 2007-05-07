###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

AST_HOME = "../org.eclipse.dltk.ruby.core/src/org/eclipse/dltk/ruby/ast/"
TEST_HOME = "src/org/eclipse/dltk/ruby/tests/parser/ast/"

override = ARGV[0]

Dir.new(AST_HOME).each { |f|
	f = f.to_s
	if f =~ /(Ruby.*)\.java/ then
		f = f.chomp
		ast_klass = $1
		klass = $1 + "Test"
		puts "Processing " + f + " " + klass
		filename = TEST_HOME + klass + ".java" 
		if File.exists?(filename) then
			puts "	File " + filename  + " already exists!\n\n"
			if override =~ /o.*/ then
				puts "	WARN: overriding"
			else
				next
			end
		end
		File.open(filename, "w") { |file|
			file << <<-HERE
package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ruby.ast.#{ast_klass};
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class #{klass} extends AbstractASTTest {

	public #{klass}(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return #{ast_klass}.class;	
	}
	
	public void test0 () throws Exception {
		String text = "";
		checkNode (text, 0, text.length());
	}		
	
}
			HERE
		}		
	end
}
	
@ECHO OFF

rem *******************************************************************************
rem  Copyright (c) 2005, 2007 IBM Corporation and others.
rem  All rights reserved. This program and the accompanying materials
rem  are made available under the terms of the Eclipse Public License v1.0
rem  which accompanies this distribution, and is available at
rem  http://www.eclipse.org/legal/epl-v10.html
rem 
rem  Contributors:
rem      IBM Corporation - initial API and implementation
rem *******************************************************************************


SET DBGP_RUBY_HOST=localhost
SET DBGP_RUBY_PORT=12000
SET DBGP_RUBY_KEY=test
SET DBGP_RUBY_SCRIPT=C:/data/programming/java/dltk/org.eclipse.dltk.ruby.debug/debugger/test.rb 
SET DBGP_RUBY_LOG=stdout

ruby -Idbgp dbgp/debugger.rb
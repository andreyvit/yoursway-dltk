/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.launching;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.launching.RubyLaunchingPlugin;
import org.osgi.framework.Bundle;

public class GenericRubyInstallType extends AbstractInterpreterInstallType {

	private static String[] interpreterNames = { "ruby" };

	public String getNatureId() {
		return RubyNature.NATURE_ID;
	}

	public String getName() {
		return "Generic Ruby install";
	}

	protected String getPluginId() {
		return RubyLaunchingPlugin.PLUGIN_ID;
	}

	protected String[] getPossibleInterpreterNames() {
		return interpreterNames;
	}

	protected IInterpreterInstall doCreateInterpreterInstall(String id) {
		return new GenericRubyInstall(this, id);
	}

	protected File createPathFile() throws IOException {
		Bundle bundle = RubyLaunchingPlugin.getDefault().getBundle();
		return storeToMetadata(bundle, "path.rb", "scripts/path.rb");
	}
	
	protected String getBuildPathDelimeter() {
		return ";:";
	}

	protected ILog getLog() {
		return RubyLaunchingPlugin.getDefault().getLog();
	}
}

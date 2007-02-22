package org.eclipse.dltk.console;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.dltk.core.DLTKCore;


public class ScriptConsoleProxy {
	private File proxyFile;

	protected ScriptConsoleProxy(URL url, String language) {
		try {
			proxyFile = DLTKCore.getDefault().getStateLocation().append(
					language + "_proxy").toFile();

			InputStream input = null;
			OutputStream output = null;
			try {
				input = new BufferedInputStream(url.openStream());

				output = new BufferedOutputStream(new FileOutputStream(
						proxyFile));

				// Simple copy
				int ch = -1;
				while ((ch = input.read()) != -1) {
					output.write(ch);
				}
			} finally {
				if (input != null) {
					input.close();
				}

				if (output != null) {
					output.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return proxyFile;				
	}
}

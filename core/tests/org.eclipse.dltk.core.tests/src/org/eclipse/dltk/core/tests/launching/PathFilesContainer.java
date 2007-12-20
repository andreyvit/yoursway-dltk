package org.eclipse.dltk.core.tests.launching;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;

public class PathFilesContainer {
	private static final String PATH = "path";

	public PathFilesContainer() {
	}

	public void accept(IFileVisitor visitor) {
		accept(visitor, new NullProgressMonitor());
	}

	public void accept(IFileVisitor visitor, IProgressMonitor monitor) {
		accept(visitor, -1, monitor);
	}

	public void accept(IFileVisitor visitor, int deep, IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			return;
		}

		// Path variable
		Map env = DebugPlugin.getDefault().getLaunchManager()
				.getNativeEnvironmentCasePreserved();

		String path = null;
		final Iterator it = env.keySet().iterator();
		while (it.hasNext()) {
			final String name = (String) it.next();
			if (name.compareToIgnoreCase(PATH) == 0) { //$NON-NLS-1$
				path = (String) env.get(name);
			}
		}

		if (path == null) {
			return;
		}

		// Folder list
		final String separator = Platform.getOS().equals(Platform.OS_WIN32) ? ";" : ":"; //$NON-NLS-1$ $NON-NLS-1$

		final List folders = new ArrayList();
		String[] res = path.split(separator);
		for (int i = 0; i < res.length; i++) {
			folders.add(Path.fromOSString(res[i]));
		}

		ArrayList searchedFiles = new ArrayList();
		final Iterator iter = folders.iterator();
		while (iter.hasNext()) {
			final IPath folder = (IPath) iter.next();

			if (folder != null) {
				File f = folder.toFile();
				if (f.isDirectory()) {
					visitFolder(visitor, f, monitor, deep, searchedFiles);
				}
			}
		}
	}

	/**
	 * Searches the specified directory recursively for installed Interpreters,
	 * adding each detected Interpreter to the <code>found</code> list. Any
	 * directories specified in the <code>ignore</code> are not traversed.
	 * 
	 * @param visitor
	 * 
	 * @param directory
	 * @param found
	 * @param types
	 * @param ignore
	 * @param deep
	 *            deepness of search. -1 if infinite.
	 * @param searchedFiles
	 */
	protected void visitFolder(IFileVisitor visitor, File directory,
			IProgressMonitor monitor, int deep, ArrayList searchedFiles) {
		if (deep == 0) {
			return;
		}

		if (monitor.isCanceled()) {
			return;
		}

		if (searchedFiles.contains(directory)) {
			return;
		}

		String[] names = directory.list();
		if (names == null) {
			return;
		}

		List subDirs = new ArrayList();
		for (int i = 0; i < names.length; i++) {
			if (monitor.isCanceled()) {
				return;
			}

			final File file = new File(directory, names[i]);

			try {
				monitor.subTask(MessageFormat.format("Searching {0}",
						new String[] { file.getCanonicalPath() }));

				// Check if file is a symlink
				if (file.isDirectory()
						&& (!file.getCanonicalPath().equals(
								file.getAbsolutePath()))) {
					continue;
				}
			} catch (IOException e) {
			}

			if (visitor.visit(file)) {
				while (!subDirs.isEmpty()) {
					File subDir = (File) subDirs.remove(0);
					visitFolder(visitor, subDir, monitor, deep - 1,
							searchedFiles);
				}
			}
		}

		searchedFiles.add(directory);
	}
}

package org.eclipse.dltk.launching;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;

public class InterpreterSearcher {
	private Set searchedFiles;
	private List found;
	private List types;

	private String natureId;
	private Set ignore;

	protected void searchFast(IProgressMonitor monitor, int deep) {
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
			if (name.compareToIgnoreCase("path") == 0) { //$NON-NLS-1$
				path = (String) env.get(name);
			}
		}

		if (path == null) {
			return;
		}

		// Folder list
		final String separator = Platform.getOS().equals(Platform.OS_WIN32) ? ";" : ":"; //$NON-NLS-1$ //$NON-NLS-2$

		final List folders = new ArrayList();
		String[] res = path.split(separator);
		for (int i = 0; i < res.length; i++) {
			folders.add(Path.fromOSString(res[i]));
		}

		final Iterator iter = folders.iterator();
		while (iter.hasNext()) {
			final IPath folder = (IPath) iter.next();

			if (folder != null) {
				File f = folder.toFile();
				if (f.isDirectory()) {
					search(f, monitor, deep);
				}
			}
		}
	}

	/**
	 * Searches the specified directory recursively for installed Interpreters,
	 * adding each detected Interpreter to the <code>found</code> list. Any
	 * directories specified in the <code>ignore</code> are not traversed.
	 * 
	 * @param directory
	 * @param found
	 * @param types
	 * @param ignore
	 * @param deep
	 *            deepness of search. -1 if infinite.
	 */
	protected void search(File directory, IProgressMonitor monitor, int deep) {
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
				monitor.subTask(MessageFormat.format(
						Messages.InterpreterSearcher_foundSearching, new String[] {
								Integer.toString(found.size()),
								file.getCanonicalPath() }));

				// Check if file is a symlink
				if (file.isDirectory()
						&& (!file.getCanonicalPath().equals(
								file.getAbsolutePath()))) {
					continue;
				}
			} catch (IOException e) {
			}

			IInterpreterInstallType[] installTypes = ScriptRuntime
					.getInterpreterInstallTypes(natureId);

			if (!ignore.contains(file)) {
				boolean validLocation = false;
				// Take the first Interpreter install type that claims the
				// location as a
				// valid Interpreter install. Interpreter install types should
				// be smart enough to not
				// claim another type's Interpreter, but just in case...
				for (int j = 0; j < installTypes.length; j++) {
					if (monitor.isCanceled()) {
						return;
					}

					final IInterpreterInstallType installType = installTypes[j];
					IStatus status = installType.validateInstallLocation(file);

					if (status.isOK()) {
						found.add(file);
						types.add(installType);
						validLocation = true;
						break;
					}
				}

				if (file.isDirectory() && !validLocation) {
					subDirs.add(file);
				}
			}
		}

		while (!subDirs.isEmpty()) {
			File subDir = (File) subDirs.remove(0);
			search(subDir, monitor, deep - 1);
		}

		searchedFiles.add(directory);
	}

	public InterpreterSearcher() {
		this.searchedFiles = new HashSet();
		this.found = new ArrayList();
		this.types = new ArrayList();
	}

	public void search(String natureId, Set ignore, int deep,
			IProgressMonitor monitor) {
		if (natureId == null) {
			throw new IllegalArgumentException();
		}

		this.found.clear();
		this.types.clear();
		this.searchedFiles.clear();

		this.natureId = natureId;
		this.ignore = ignore == null ? Collections.EMPTY_SET : ignore;

		searchFast(monitor == null ? new NullProgressMonitor() : monitor, deep);
	}

	public boolean hasResults() {
		return !found.isEmpty();
	}

	public File[] getFoundFiles() {
		return (File[]) found.toArray(new File[found.size()]);
	}

	public IInterpreterInstallType[] getFoundInstallTypes() {
		return (IInterpreterInstallType[]) types
				.toArray(new IInterpreterInstallType[types.size()]);
	}
}

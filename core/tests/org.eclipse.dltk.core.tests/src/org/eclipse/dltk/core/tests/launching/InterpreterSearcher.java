package org.eclipse.dltk.core.tests.launching;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.ScriptRuntime;

public class InterpreterSearcher {
	private List types = new ArrayList();;
	private ArrayList found = new ArrayList();;
	private String natureId;
	private Set ignore;

	public InterpreterSearcher() {
	}

	public void search(String natureId, Set ignore, int deep,
			IProgressMonitor monsitor) {
		if (natureId == null) {
			throw new IllegalArgumentException();
		}

		this.found.clear();
		this.types.clear();

		this.natureId = natureId;
		this.ignore = ignore == null ? Collections.EMPTY_SET : ignore;

		Searcher searcher = new Searcher(); 
		PathFilesContainer path = new PathFilesContainer();
		path.accept(searcher);
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
	
	class Searcher implements IFileVisitor {
		public boolean visit(File file) {
			IInterpreterInstallType[] installTypes = ScriptRuntime
					.getInterpreterInstallTypes(natureId);

			boolean interpreterFound = false;

			if (!ignore.contains(file)) {
				// Take the first Interpreter install type that claims the
				// location as a valid Interpreter install. Interpreter install types
				// should be smart enough to not claim another type's Interpreter, 
				// but just in case...
				for (int j = 0; j < installTypes.length; j++) {
					final IInterpreterInstallType installType = installTypes[j];
					IStatus status = installType.validateInstallLocation(file);

					if (status.isOK()) {
						found.add(file);
						types.add(installType);
						interpreterFound = true;
						break;
					}
				}
			}
			return !interpreterFound;
		}
	}
}

package org.eclipse.dltk.tcl.internal.ui.preferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPUserLibraryElement;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptLaunchUtil;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.ui.dialogs.TimeTriggeredProgressMonitorDialog;
import org.eclipse.dltk.ui.preferences.UserLibraryPreferencePage;
import org.eclipse.dltk.utils.DeployHelper;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class TclLibraryPreferencePage extends UserLibraryPreferencePage {
	public TclLibraryPreferencePage() {
	}

	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return TclLanguageToolkit.getDefault();
	}

	protected void doDetection() {
		File script;
		try {
			script = DeployHelper.deploy(TclUI.getDefault(), "scripts/")
					.append("packages.tcl").toFile();
			IInterpreterInstall install = ScriptRuntime
					.getDefaultInterpreterInstall(TclNature.NATURE_ID);
			Process process = ScriptLaunchUtil.runScriptWithInterpreter(install
					.getInstallLocation().getAbsolutePath(), script, null,
					null, null);
			final BufferedReader input = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			// BufferedReader error = new BufferedReader(new InputStreamReader(
			// process.getErrorStream()));
			ProgressMonitorDialog dialog = new TimeTriggeredProgressMonitorDialog(null, 1000);
			dialog.run(true, true, new IRunnableWithProgress() {
				private String packageName;
				private Set paths = new HashSet();

				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					boolean add = false;
					if (monitor != null) {
						monitor.beginTask("Discovering tcl packages",
								IProgressMonitor.UNKNOWN);
					}
					try {
						while (true) {
							if (monitor != null) {
								monitor.worked(1);
							}
							String line = null;
							try {
								line = input.readLine();
							} catch (IOException e) {
								if (DLTKCore.DEBUG) {
									e.printStackTrace();
								}
							}
							if (line == null) {
								return;
							}
							if (add) {
								String l = line.trim();
								// Package start
								if (l.charAt(0) == '+') {
									addPrevPackage();
								}
								if (l.charAt(0) == '-') {
									packageName = l.substring(1).trim();
								}
								if (l.charAt(0) == '.') {
									IPath path = Path.fromOSString(l.substring(
											1).trim());
									path = path.removeLastSegments(1);
									paths.add(path);
								}
							} else {
								if (line.startsWith("DLTK-BEGIN")) {
									add = true;
								}
								if (line.startsWith("DLTK-END")) {
									addPrevPackage();
									add = false;
									return;
								}
							}
						}
					} finally {
						if (monitor != null) {
							monitor.done();
						}
					}
				}

				private void addPrevPackage() {
					if (this.packageName != null && paths.size() > 0) {
						IPath[] paths = (IPath[]) this.paths
								.toArray(new IPath[this.paths.size()]);
						BPUserLibraryElement newLibrary = new BPUserLibraryElement(
								packageName, false, null);
						for (int i = 0; i < paths.length; i++) {
							BPListElement entry = new BPListElement(newLibrary,
									null, IBuildpathEntry.BPE_LIBRARY,
									paths[i], null, true);
							newLibrary.add(entry);
						}

						boolean exist = false;
						List existingLibraries = fLibraryList.getElements();
						for (int i = 0; i < existingLibraries.size(); i++) {
							BPUserLibraryElement curr = (BPUserLibraryElement) existingLibraries
									.get(i);
							if (curr.getName().equals(newLibrary.getName())) {
								exist = true;
								break;
							}
						}
						if (!exist) {
							fLibraryList.addElement(newLibrary);
						}
						// fLibraryList.expandElement(newLibrary,
						// AbstractTreeViewer.ALL_LEVELS);
						// fLibraryList.selectElements(new StructuredSelection(
						// newLibrary));
						this.packageName = null;
						this.paths.clear();
					}
				}
			});
		} catch (IOException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (InvocationTargetException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		fLibraryList.refresh();
	}

	protected boolean isDetectionSupported() {
//		IInterpreterInstall install = ScriptRuntime
//				.getDefaultInterpreterInstall(TclNature.NATURE_ID);
//		return install != null;
		return false;
	}
}

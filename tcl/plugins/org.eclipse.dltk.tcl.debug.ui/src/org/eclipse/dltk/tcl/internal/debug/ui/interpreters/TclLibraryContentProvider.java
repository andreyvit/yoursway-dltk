package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryContentProvider;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryStandin;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.dltk.tcl.internal.launching.PackagesHelper;
import org.eclipse.dltk.tcl.internal.launching.PackagesHelper.PackageLocation;
import org.eclipse.dltk.ui.dialogs.TimeTriggeredProgressMonitorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

public class TclLibraryContentProvider extends LibraryContentProvider {
	PackageLocation[] packageLocations;
	private Set additions = new HashSet();
	private static HashMap fCachedPacakges = new HashMap();

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof PackageLocation) {
			return ((PackageLocation) parentElement).getPackages();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof PackageLocation) {
			return ((PackageLocation) element).getPackages().length > 0;
		}
		return false;
	}

	public void setLibraries(LibraryLocation[] libs) {
		super.setLibraries(libs);
		if (this.packageLocations != null) {
			updateColors();
		}
	}

	public synchronized void initialize(final File file,
			final EnvironmentVariable[] environmentVariables,
			boolean restoreDefault) {
		if (file != null && file.exists()) {
			final LibraryLocation[] additions;
			// if (restoreDefault) {
			// // this.additions.clear();
			// additions = null;
			// } else {
			additions = getAdditions();
			// }

			final Object key = makeKey(file, environmentVariables, additions);
			if (fCachedPacakges.containsKey(key)) {
				packageLocations = (PackageLocation[]) fCachedPacakges.get(key);
				return;
			}
			ProgressMonitorDialog dialog = new TimeTriggeredProgressMonitorDialog(
					null, 1000);
			try {
				dialog.run(true, true, new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						// TODO Auto-generated method stub
						packageLocations = PackagesHelper.getLocations(
								new Path(file.getAbsolutePath()),
								environmentVariables, additions, monitor);
						// Try to use without specific libraries.
						if (packageLocations.length == 0) {
							packageLocations = PackagesHelper.getLocations(
									new Path(file.getAbsolutePath()),
									environmentVariables, null, monitor);
						}
						// Failsafe.
						if (packageLocations.length == 0) {
							packageLocations = createPackageLocationsFrom(getLibraries());
						}
					}
				});
			} catch (InvocationTargetException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}

			updateLibrariesFromPackages();

			LibraryLocation[] adds = getAdditions();
			Object key2 = makeKey(file, environmentVariables, adds);
			fCachedPacakges.put(key2, packageLocations);

			updateColors();
		}
	}

	public static Object makeKey(File installLocation,
			EnvironmentVariable[] variables, LibraryLocation[] additions) {
		String key = installLocation.getAbsolutePath();
		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				key += "|" + variables[i].getName() + ":"
						+ variables[i].getValue();
			}
		}
		if (additions != null) {
			for (int i = 0; i < additions.length; i++) {
				key += "|" + additions[i].getLibraryPath().toOSString();
			}
		}
		return key;
	}

	/**
	 * Add all libraries not found in packages
	 * 
	 * @return
	 */
	private LibraryLocation[] getAdditions() {
		LibraryLocation[] libraries = super.getLibraries();
		if (packageLocations == null || packageLocations.length == 0) {
			return null;
		}
		Set packages = new HashSet();
		packages.addAll(Arrays.asList(packageLocations));
		for (int i = 0; i < libraries.length; i++) {
			if (!packages.contains(new PackageLocation(libraries[i]
					.getLibraryPath().toOSString()))) {
				additions.add(libraries[i]);
			}
		}
		return (LibraryLocation[]) additions
				.toArray(new LibraryLocation[additions.size()]);
	}

	private PackageLocation[] createPackageLocationsFrom(
			LibraryLocation[] libraries) {
		if (libraries == null) {
			return new PackageLocation[0];
		}
		PackageLocation[] locs = new PackageLocation[libraries.length];
		for (int i = 0; i < locs.length; i++) {
			locs[i] = new PackageLocation(libraries[i].getLibraryPath()
					.toOSString());
		}
		return locs;
	}

	private void updateLibrariesFromPackages() {
		// We need to remove libraries not pressent in packages.
		if (packageLocations == null) {
			return;
		}
		LibraryLocation[] libraries = super.getLibraries();
		Set set = new HashSet();
		set.addAll(Arrays.asList(this.packageLocations));
		Set result = new HashSet();
		for (int i = 0; i < libraries.length; i++) {
			IPath lPath = libraries[i].getLibraryPath();
			PackageLocation l = new PackageLocation(lPath.toOSString());
			if (set.contains(l)) {
				result.add(libraries[i]);
			} // We need to add all libs from packages with this

			// prefix.
			for (int j = 0; j < packageLocations.length; j++) {
				Path path = new Path(packageLocations[j].getPath());
				if (lPath.isPrefixOf(path)) {
					result.add(new LibraryLocation(path));
				}
			}
		}

		LibraryLocation[] newLocations = (LibraryLocation[]) result
				.toArray(new LibraryLocation[result.size()]);
		super.setLibraries(newLocations);
	}

	private void updateColors() {
		// We need to set some elements as greyed
		TreeItem[] items = this.fViewer.getTree().getItems();
		for (int i = 0; i < items.length; i++) {
			Color color = PlatformUI.getWorkbench().getDisplay()
					.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
			items[i].setBackground(color);
		}
		for (int j = 0; j < packageLocations.length; j++) {
			PackageLocation loc = packageLocations[j];
			boolean exist = find(loc.getPath(), this.fLibraries);

			for (int i = 0; i < items.length; i++) {
				Object data = items[i].getData();
				Color color = PlatformUI.getWorkbench().getDisplay()
						.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
				if (loc.equals(data) && exist) {
					items[i].setBackground(color);
				}
			}
		}
	}

	private boolean find(String path, LibraryStandin[] libraries) {
		Path lpath = new Path(path);
		for (int i = 0; i < libraries.length; i++) {
			if (libraries[i].getSystemLibraryPath().equals(lpath)) {
				return true;
			}
		}
		return false;
	}

	public Object[] getElements(Object inputElement) {
		if (packageLocations == null) {
			return new Object[0];
		}
		return packageLocations;
	}

	public boolean canRemove(IStructuredSelection selection) {
		boolean all = true;
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (!(element instanceof LibraryStandin)) {
				all = false;
				break;
			}
		}
		return all;
	}

	public boolean canEnable(IStructuredSelection selection) {
		return selection.size() == 1
				&& selection.getFirstElement() instanceof PackageLocation;
	}

	public boolean canDown(IStructuredSelection selection) {
		return false;
	}

	public boolean canUp(IStructuredSelection selection) {
		return false;
	}

	public boolean isEnabled(Object lib) {
		if (lib instanceof PackageLocation) {
			PackageLocation loc = (PackageLocation) lib;
			return find(loc.getPath(), this.fLibraries);
		}
		return false;
	}

	public void changeEnabled() {
		ISelection selection = this.fViewer.getSelection();
		if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			for (Iterator iterator = sel.iterator(); iterator.hasNext();) {
				Object object = iterator.next();
				if (object instanceof PackageLocation) {
					boolean oldState = isEnabled(object);
					boolean newState = !oldState;
					Set set = new HashSet();
					set.addAll(Arrays.asList(getLibraries()));
					IPath path = new Path(((PackageLocation) object).getPath());
					if (newState) {
						set.add(new LibraryLocation(path));
					} else {
						set.remove(new LibraryLocation(path));
					}

					LibraryLocation[] newLocations = (LibraryLocation[]) set
							.toArray(new LibraryLocation[set.size()]);
					this.setLibraries(newLocations);
				}
			}
		}
	}
}

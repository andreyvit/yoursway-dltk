package org.eclipse.dltk.tcl.internal.launching;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.dltk.launching.ScriptLaunchUtil;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;
import org.eclipse.dltk.utils.DeployHelper;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class PackagesHelper {
	private static final String TCLLIBPATH_ENV_VAR = "TCLLIBPATH";

	public static class Package {
		private String name;
		private String version;
		private String[] paths;

		public Package(String name, String version, String[] paths) {
			this.name = name;
			this.version = version;
			this.paths = paths;
		}

		public Package(String key, String[] paths) {
			int pos1 = key.indexOf('(');
			int pos2 = key.indexOf(')');
			if (pos1 != -1 && pos2 != -1 && pos1 < pos2) {
				this.name = key.substring(0, pos1);
				this.version = key.substring(pos1 + 1, pos2);
			}
			this.paths = paths;
		}

		public String[] getPaths() {
			return this.paths;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result
					+ ((version == null) ? 0 : version.hashCode());
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Package other = (Package) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (version == null) {
				if (other.version != null)
					return false;
			} else if (!version.equals(other.version))
				return false;
			return true;
		}

		public String toString() {
			return this.name;
		}
	}

	public static class PackageLocation {
		private Set packages = new HashSet();
		private String path;

		public PackageLocation() {

		}

		public PackageLocation(String path) {
			this.path = path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getPath() {
			return this.path;
		}

		public void addPackage(Package pack) {
			this.packages.add(pack);
		}

		public Package[] getPackages() {
			return (Package[]) this.packages.toArray(new Package[this.packages
					.size()]);
		}

		public String toString() {
			return this.path;
		}

		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((path == null) ? 0 : path.hashCode());
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PackageLocation other = (PackageLocation) obj;
			if (path == null) {
				if (other.path != null)
					return false;
			} else if (!path.equals(other.path))
				return false;
			return true;
		}
	}

	private static void addLibpathEnvVar(InterpreterConfig config,
			LibraryLocation[] locations) throws CoreException {
		// config.removeEnvVar(TCLLIBPATH_ENV_VAR);
		String oldVar = config.getEnvVar(TCLLIBPATH_ENV_VAR);
		config.removeEnvVar("DISPLAY");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < locations.length; ++i) {
			sb.append('{');
			sb.append(locations[i].getLibraryPath().toOSString());
			sb.append('}');
			if (i < locations.length - 1) {
				sb.append(' ');
			}
		}

		if (oldVar != null) {
			config.addEnvVar(TCLLIBPATH_ENV_VAR, sb.toString() + " " + oldVar);
		} else {
			config.addEnvVar(TCLLIBPATH_ENV_VAR, sb.toString());
		}
	}

	public static PackageLocation[] getLocations(IPath installLocation,
			EnvironmentVariable[] environmentVariables,
			LibraryLocation[] libraryLocations) {
		File script;
		try {
			script = DeployHelper.deploy(TclLaunchingPlugin.getDefault(),
					"scripts/").append("packages.tcl").toFile();

			InterpreterConfig config = ScriptLaunchUtil
					.createInterpreterConfig(script, script.getParentFile());
			if (libraryLocations != null) {
				addLibpathEnvVar(config, libraryLocations);
			}
			if (environmentVariables != null) {
				for (int i = 0; i < environmentVariables.length; i++) {
					config.addEnvVar(environmentVariables[i].getName(),
							environmentVariables[i].getValue());
				}
				// config.addEnvVar(name, value)
			}
			// config.addInterpreterArg("-KU");
			Process process = ScriptLaunchUtil.runScriptWithInterpreter(
					installLocation.toOSString(), config);

			final BufferedReader input = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			// BufferedReader error = new BufferedReader(new InputStreamReader(
			// process.getErrorStream()));
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(null);

			final List locations = new ArrayList();
			dialog.run(true, true, new IRunnableWithProgress() {
				private String packageName;
				private Set paths = new HashSet();
				private String mainPath;

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
									mainPath = l.substring(1).trim();
								}
								if (l.charAt(0) == '-') {
									packageName = l.substring(1).trim();
								}
								if (l.charAt(0) == '.') {
									IPath path = Path.fromOSString(l.substring(
											1).trim());
									path = path.removeLastSegments(1);
									paths.add(path.toOSString());
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
						String[] paths = (String[]) this.paths
								.toArray(new String[this.paths.size()]);

						for (int i = 0; i < paths.length; i++) {
//							System.out.println(paths[i]);
							PackageLocation loc = findLocation(locations,
									paths[i]);
							if (loc == null) {
								loc = new PackageLocation();
								loc.setPath(paths[i]);
								locations.add(loc);
							}
							loc
									.addPackage(new Package(this.packageName,
											paths));

						}
						this.packageName = null;
						this.paths.clear();
						this.mainPath = null;
					}
				}

				private PackageLocation findLocation(List locations,
						String mainPath) {
					for (int i = 0; i < locations.size(); i++) {
						PackageLocation loc = (PackageLocation) locations
								.get(i);
						if (loc.getPath().equals(mainPath)) {
							return loc;
						}
					}
					return null;
				}
			});
			Collections.sort(locations, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					if (arg0 instanceof PackageLocation
							&& arg1 instanceof PackageLocation) {
						PackageLocation l1 = (PackageLocation) arg0;
						PackageLocation l2 = (PackageLocation) arg1;
						return l1.getPath().compareTo(l2.getPath());
					}
					return 0;
				}
			});
			return (PackageLocation[]) locations
					.toArray(new PackageLocation[locations.size()]);
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
		return null;
	}
}

package org.eclipse.dltk.core.internal.rse;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.internal.launching.execution.EFSDeployment;
import org.eclipse.rse.core.model.IHost;
import org.eclipse.rse.internal.efs.RSEFileSystem;
import org.eclipse.rse.services.shells.HostShellProcessAdapter;
import org.eclipse.rse.services.shells.IHostShell;
import org.eclipse.rse.services.shells.IShellService;
import org.eclipse.rse.subsystems.shells.core.subsystems.servicesubsystem.IShellServiceSubSystem;

public class RSEExecEnvironment implements IExecutionEnvironment {
	private final static String EXIT_CMD = "exit"; //$NON-NLS-1$
	private final static String CMD_DELIMITER = ";"; //$NON-NLS-1$
	private RSEEnvironment environment;
	private IShellServiceSubSystem shell;
	private static int counter = -1;

	public RSEExecEnvironment(RSEEnvironment env, IShellServiceSubSystem shell) {
		this.environment = env;
		this.shell = shell;
	}

	public IDeployment createDeployment() {
		try {
			String rootPath = getTempDir() + environment.getSeparator()
					+ getTempName("dltk", ".tmp");
			URI rootUri = createRemoteURI(environment.getHost(), rootPath);
			return new EFSDeployment(environment, rootUri);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG)
				e.printStackTrace();
		}
		return null;
	}

	private URI createRemoteURI(IHost host, String rootPath) {
		return RSEFileSystem.getURIFor(host.getHostName(), rootPath);
	}

	private String getTempName(String prefix, String suffix) {
		if (counter == -1) {
			counter = new Random().nextInt() & 0xffff;
		}
		counter++;
		return prefix + Integer.toString(counter) + suffix;
	}

	private String getTempDir() {
		String temp = shell.getConnectorService().getTempDirectory();
		if (temp.length() == 0) {
			temp = "/tmp";
		}
		return temp;
	}

	public Process exec(String[] cmdLine, IPath workingDir, String[] environment)
			throws CoreException {
		try {
			IShellService shellService = shell.getShellService();		
			String command = createCommand(cmdLine);
			IHostShell hostShell = shellService.runCommand(
					createWorkingDir(workingDir), command,
					environment, null);
			return new MyHostShellProcessAdapter(command, hostShell);
		} catch (IOException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}			
			return null;
		}
	}

	private String createWorkingDir(IPath workingDir) {
		if (workingDir == null)
			return ".";
		return workingDir.toPortableString();
	}

	private String createCommand(String[] cmdLine) {
		StringBuffer result = new StringBuffer();
		for (int i=0; i<cmdLine.length; i++) {
			result.append(cmdLine[i]).append(" ");
		}
		return result.toString() + CMD_DELIMITER + EXIT_CMD;
	}

	public Map getEnvironmentVariables() {
		Iterator it = shell.getHostEnvironmentVariables().iterator();
		Map result = new HashMap();
		while (it.hasNext()) {
			String var = (String) it.next();
			int pos = var.indexOf('=');
			if (pos < 0) {
				result.put(var, "");
			} else {
				String name = var.substring(0, pos);
				String value = var.substring(pos + 1);
				result.put(name, value);
			}
		}
		return result;
	}

	public IEnvironment getEnvironment() {
		return environment;
	}

}

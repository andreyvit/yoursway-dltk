package org.eclipse.dltk.launching;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InterpreterConfig {
	private File scriptFile;
	private File workingDirectory;

	private ArrayList interpreterArgs;
	private ArrayList scriptArgs;
	private HashMap environment;
	
	public InterpreterConfig(File scriptFile) {
		this();
		
		if (scriptFile == null) {
			throw new IllegalArgumentException();
		}
		
		this.scriptFile = scriptFile;		
	}

	public InterpreterConfig(File scriptFile, File workingDirectory) {
		this();

		if (scriptFile == null || workingDirectory == null) {
			throw new IllegalArgumentException();
		}

		this.scriptFile = scriptFile;
		this.workingDirectory = workingDirectory;
	}
		
	public InterpreterConfig() {
		interpreterArgs = new ArrayList();
		scriptArgs = new ArrayList();
		environment = new HashMap();
	}

	// Script file & Working
	public File getScriptFile() {
		return scriptFile;
	}

	public File getWorkingDirectory() {
		return workingDirectory;
	}

	// Interpreter section
	public boolean addInterpreterArg(String arg) {
		return interpreterArgs.add(arg);
	}
	
	public void addInterpreterArgs(String[] args) {
		for(int i = 0; i < args.length; ++i) {
			addInterpreterArg(args[i]);
		}
	}

	public boolean hasInterpreterArg(String arg) {
		return interpreterArgs.contains(arg);
	}
	
	public boolean hasInterpreterArgWithRegex(String regex) {
		Iterator it = interpreterArgs.iterator();
		while(it.hasNext()) {
			if (((String)it.next()).matches(regex)) {
				return true;
			}
		}
		
		return false;
	}

	public boolean removeInterpreterArg(String arg) {
		return interpreterArgs.remove(arg);
	}

	public List getInterpreterArgs() {
		return (List) interpreterArgs.clone();
	}

	// Script section
	public boolean addScriptArg(String arg) {
		return scriptArgs.add(arg);
	}
	
	public void addScriptArgs(String[] args) {
		for(int i = 0; i < args.length; ++i) {
			addScriptArg(args[i]);
		}
	}

	public boolean hasScriptArg(String arg) {
		return scriptArgs.contains(arg);
	}

	public boolean removeScriptArg(String arg) {
		return scriptArgs.remove(arg);
	}

	public List getScriptArgs() {
		return (List) scriptArgs.clone();
	}

	// Environment
	public String addEnvVar(String name, String value) {
		if (name == null || value == null) {
			throw new IllegalArgumentException();
		}

		return (String) environment.put(name, value);
	}
	
	public void addEnvVars(Map vars) {
		environment.putAll(vars);		
	}
	

	public String removeEnvVar(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		return (String) environment.remove(name);
	}

	public String getEnvVar(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		return (String) environment.get(name);
	}

	public boolean hasEnvVar(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		return environment.containsKey(name);
	}

	public Map getEnvVars() {
		return (Map) environment.clone();
	}
	
	public String[] getEnvironmentAsStrings() {
		ArrayList list = new ArrayList();
		Iterator it = environment.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			String value = (String)environment.get(key);
			list.add(key + "=" + value);
		}
		
		return (String[])list.toArray(new String[list.size()]);
	}

	// Command line
	public String[] renderCommandLine(String exe) {
		List items = new ArrayList();

		items.add(exe);

		// Interpreter arguments
		Iterator it = interpreterArgs.iterator();
		while (it.hasNext()) {
			items.add(it.next());
		}

		// Script file
		items.add(scriptFile.toString());

		// Script arguments
		it = scriptArgs.iterator();
		while (it.hasNext()) {
			items.add(it.next());
		}

		return (String[]) items.toArray(new String[items.size()]);
	}
	
    public String toString() {
    	return "TODO";
    }	
}

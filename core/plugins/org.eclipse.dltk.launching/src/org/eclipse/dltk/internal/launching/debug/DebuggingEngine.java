package org.eclipse.dltk.internal.launching.debug;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;

public class DebuggingEngine implements IDebuggingEngine {

	private String id;
	private String modelId;
	private String natureId;
	private String name;
	private String description;
	private int priority;
	private IInterpreterRunnerFactory factory;

	public DebuggingEngine(String id, String modelId, String natureId, String name,
			String description, int priority,
			IInterpreterRunnerFactory factory) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.natureId = natureId;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.factory = factory;
	}

	public String getId() {
		return id;
	}
	
	public String getModelId() {
		return modelId;
	}

	public String getNatureId() {
		return natureId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getPriority() {
		return priority;
	}

	public IInterpreterRunner getRunner(IInterpreterInstall install) {
		return factory.createRunner(install);
	}	
}

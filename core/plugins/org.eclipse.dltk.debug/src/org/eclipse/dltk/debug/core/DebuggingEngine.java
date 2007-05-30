package org.eclipse.dltk.debug.core;

public class DebuggingEngine implements IDebuggingEngine {

	private String id;
	private String natureId;
	private String name;
	private String description;
	private int priority;
	private IDebuggingEngineRunnerFactory factory;
	
	public DebuggingEngine(String id, String natureId, String name,
			String description, int priority,
			IDebuggingEngineRunnerFactory factory) {
		super();
		this.id = id;
		this.natureId = natureId;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.factory = factory;
	}

	public String getId() {
		return id;
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

	public IDebuggingEngineRunner getRunner() {
		return factory.createDebuggingEngineRunner();
	}

}

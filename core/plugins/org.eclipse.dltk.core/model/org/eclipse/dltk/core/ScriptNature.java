package org.eclipse.dltk.core;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.internal.core.Model;
import org.eclipse.dltk.internal.core.ModelManager;

public class ScriptNature implements IProjectNature {
	private IProject project;
	private Model parent;
	/**
	 * Configure the project with Java nature.
	 */
	public void configure() throws CoreException {

		// register Script builder
		addToBuildSpec(DLTKCore.BUILDER_ID);
	}
	/**
	/**
	 * Removes the Java nature from the project.
	 */
	public void deconfigure() throws CoreException {

		// deregister Script builder
		removeFromBuildSpec(DLTKCore.BUILDER_ID);
	}
	/**
	 * Adds a builder to the build spec for the given project.
	 */
	protected void addToBuildSpec(String builderID) throws CoreException {

		IProjectDescription description = this.project.getDescription();
		int scriptCommandIndex = getScriptCommandIndex(description.getBuildSpec());

		if (scriptCommandIndex == -1) {

			// Add a Java command to the build spec
			ICommand command = description.newCommand();
			command.setBuilderName(builderID);
			setScriptCommand(description, command);
		}
	}
	
	/**
	 * Update the Script command in the build spec (replace existing one if present,
	 * add one first if none).
	 */
	private void setScriptCommand(
		IProjectDescription description,
		ICommand newCommand)
		throws CoreException {

		ICommand[] oldBuildSpec = description.getBuildSpec();
		int oldScriptCommandIndex = getScriptCommandIndex(oldBuildSpec);
		ICommand[] newCommands;

		if (oldScriptCommandIndex == -1) {
			// Add a Java build spec before other builders (1FWJK7I)
			newCommands = new ICommand[oldBuildSpec.length + 1];
			System.arraycopy(oldBuildSpec, 0, newCommands, 1, oldBuildSpec.length);
			newCommands[0] = newCommand;
		} else {
		    oldBuildSpec[oldScriptCommandIndex] = newCommand;
			newCommands = oldBuildSpec;
		}

		// Commit the spec change into the project
		description.setBuildSpec(newCommands);
		this.project.setDescription(description, null);
	}
	/**
	 * Removes the given builder from the build spec for the given project.
	 */
	protected void removeFromBuildSpec(String builderID) throws CoreException {

		IProjectDescription description = this.project.getDescription();
		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(builderID)) {
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy(commands, 0, newCommands, 0, i);
				System.arraycopy(commands, i + 1, newCommands, i, commands.length - i - 1);
				description.setBuildSpec(newCommands);
				this.project.setDescription(description, null);
				return;
			}
		}
	}
	/**
	 * Find the specific Script command amongst the given build spec
	 * and return its index or -1 if not found.
	 */
	private int getScriptCommandIndex(ICommand[] buildSpec) {

		for (int i = 0; i < buildSpec.length; ++i) {
			if (buildSpec[i].getBuilderName().equals(DLTKCore.BUILDER_ID)) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Sets the underlying kernel project of this Java project,
	 * and fills in its parent and name.
	 * Called by IProject.getNature().
	 *
	 * @see IProjectNature#setProject(IProject)
	 */
	public void setProject(IProject project) {

		this.project = project;
		this.parent = ModelManager.getModelManager().getModel();
	}
	public IProject getProject() {
		return this.project;
	}
}

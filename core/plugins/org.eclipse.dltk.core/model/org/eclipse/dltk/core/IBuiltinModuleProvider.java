package org.eclipse.dltk.core;

/**
 * Used to provide builtin information into model.
 * @author Haiodo
 *
 */
public interface IBuiltinModuleProvider {
	/**
	 * Used to builtin model contributions.
	 * @return
	 */
	String[] getBuiltinModules();
	String getBuiltinModuleContent( String name );
}

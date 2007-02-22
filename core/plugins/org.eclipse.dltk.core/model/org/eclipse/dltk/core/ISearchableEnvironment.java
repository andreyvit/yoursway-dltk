package org.eclipse.dltk.core;

import org.eclipse.dltk.compiler.env.INameEnvironment;
import org.eclipse.dltk.internal.codeassist.ISearchRequestor;
import org.eclipse.dltk.internal.core.NameLookup;

public interface ISearchableEnvironment extends INameEnvironment {
	/**
	 * Find the packages that start with the given prefix.
	 * A valid prefix is a qualified name separated by periods
	 * (ex. java.util).
	 * The packages found are passed to:
	 *    ISearchRequestor.acceptPackage(char[][] packageName)
	 */
	public abstract void findPackages(char[] prefix, ISearchRequestor requestor);

	/**
	 * Find the top-level types (classes and interfaces) that are defined
	 * in the current environment and whose name starts with the
	 * given prefix. The prefix is a qualified name separated by periods
	 * or a simple name (ex. java.util.V or V).
	 *
	 * The types found are passed to one of the following methods (if additional
	 * information is known about the types):
	 *    ISearchRequestor.acceptType(char[][] packageName, char[] typeName)
	 *    ISearchRequestor.acceptClass(char[][] packageName, char[] typeName, int modifiers)
	 *    ISearchRequestor.acceptInterface(char[][] packageName, char[] typeName, int modifiers)
	 *
	 * This method can not be used to find member types... member
	 * types are found relative to their enclosing type.
	 */
	public abstract void findTypes(char[] prefix, final boolean findMembers, boolean camelCaseMatch, final ISearchRequestor storage);
		
	public NameLookup getNameLookup();
}

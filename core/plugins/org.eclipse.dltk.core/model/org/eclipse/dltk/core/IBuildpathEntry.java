package org.eclipse.dltk.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public interface IBuildpathEntry {

	/**
	 * Entry kind constant describing a buildpath entry identifying a
	 * library. A library is a ...
	 */
	int BPE_LIBRARY = 1;
	
	/**
	 * Entry kind constant describing a buildpath entry identifying a
	 * required project.
	 */
	int BPE_PROJECT = 2;
	
	/**
	 * Entry kind constant describing a buildpath entry identifying a
	 * folder containing modules with source code
	 * to be compiled.
	 */
	int BPE_SOURCE = 3;
	
	/**
	 * Entry kind constant describing a buildpath entry representing
	 * a name buildpath container.
	 */
	int BPE_CONTAINER = 5;

	IPath BUILDIN_EXTERNAL_ENTRY = new Path("#special#builtin#");
	
	/**
	 * Returns whether the access rules of the project's exported entries should be combined with this entry's access rules.
	 * Returns true for container entries.
	 * Returns false otherwise.
	 * 
	 * @return whether the access rules of the project's exported entries should be combined with this entry's access rules
	 *
	 */
	boolean combineAccessRules();

	/**
	 * Returns the possibly empty list of access rules for this entry.
	 * 
	 * @return the possibly empty list of access rules for this entry
	 *
	 */
	IAccessRule[] getAccessRules();	
	/**
	 * Returns the kind of files found in the project identified by this
	 * buildpath entry.
	 *
	 * @return <code>IProjectFragment.K_SOURCE</code> for files containing
	 *   source code, and <code>IProjectFragment.K_STATIC</code> for readonly
	 *   files.
	 *   There is no specified value for an entry denoting a buildpath container 
	 *   (<code>BPE_CONTAINER</code>).
	 */
	int getContentKind();
	
	/**
	 * Returns the kind of this buildpath entry.
	 *
	 * @return one of:
	 * <ul>
	 * <li><code>BPE_PROJECT</code> - this entry describes another project
	 *
	 * <li><code>BPE_CONTAINER</code> - this entry describes set of entries
	 *  	referenced indirectly via a buildpath container
	 * </ul>
	 */
	int getEntryKind();
	/**
	 * Returns the set of patterns used to exclude resources or classes associated with
	 * this buildpath entry.
	 * <p>
	 * For source buildpath entries,
	 * exclusion patterns allow specified portions of the resource tree rooted
	 * at this source entry's path to be filtered out. If no exclusion patterns
	 * are specified, this source entry includes all relevent files. Each path
	 * specified must be a relative path, and will be interpreted relative
	 * to this source entry's path. File patterns are case-sensitive. A file
	 * matched by one or more of these patterns is excluded from the 
	 * corresponding package fragment root.
	 * Exclusion patterns have higher precedence than inclusion patterns;
	 * in other words, exclusion patterns can remove files for the ones that 
	 * are to be included, not the other way around.
	 * </p>
	 * <p>
	 * Note that there is no need to supply a pattern to exclude ".class" files
	 * because a source entry filters these out automatically.
	 * </p>
	 * <p>
	 * The pattern mechanism is similar to Ant's. Each pattern is represented as
	 * a relative path. The path segments can be regular file or folder names or simple patterns
	 * involving standard wildcard characters.
	 * </p>
	 * <p>
	 * '*' matches 0 or more characters within a segment. So
	 * <code>*.java</code> matches <code>.java</code>, <code>a.java</code>
	 * and <code>Foo.java</code>, but not <code>Foo.properties</code>
	 * (does not end with <code>.java</code>).
	 * </p>
	 * <p>
	 * '?' matches 1 character within a segment. So <code>?.java</code> 
	 * matches <code>a.java</code>, <code>A.java</code>, 
	 * but not <code>.java</code> or <code>xyz.java</code> (neither have
	 * just one character before <code>.java</code>).
	 * </p>
	 * <p>
	 * Combinations of *'s and ?'s are allowed.
	 * </p>
	 * <p>
	 * The special pattern '**' matches zero or more segments. In a source entry,
	 * a path like <code>tests/</code> that ends in a trailing separator is interpreted
	 * as <code>tests/&#42;&#42;</code>, and would match everything under
	 * the folder named <code>tests</code>.
	 * </p>
	 * <p>
	 * Example patterns in source entries (assuming that "java" is the only {@link DLTKCore#getScriptLikeExtensions() Script-like extension}):
	 * <ul>
	 * <li>
	 * <code>tests/&#42;&#42;</code> (or simply <code>tests/</code>) 
	 * matches all files under a root folder
	 * named <code>tests</code>. This includes <code>tests/Foo.java</code>
	 * and <code>tests/com/example/Foo.java</code>, but not 
	 * <code>com/example/tests/Foo.java</code> (not under a root folder named
	 * <code>tests</code>).
	 * </li>
	 * <li>
	 * <code>tests/&#42;</code> matches all files directly below a root 
	 * folder named <code>tests</code>. This includes <code>tests/Foo.java</code>
	 * and <code>tests/FooHelp.java</code>
	 * but not <code>tests/com/example/Foo.java</code> (not directly under
	 * a folder named <code>tests</code>) or 
	 * <code>com/Foo.java</code> (not under a folder named <code>tests</code>).
	 * </li>
	 * <li>
	 * <code>&#42;&#42;/tests/&#42;&#42;</code> matches all files under any
	 * folder named <code>tests</code>. This includes <code>tests/Foo.java</code>,
	 * <code>com/examples/tests/Foo.java</code>, and 
	 * <code>com/examples/tests/unit/Foo.java</code>, but not 
	 * <code>com/example/Foo.java</code> (not under a folder named
	 * <code>tests</code>).
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @return the possibly empty list of resource exclusion patterns 
	 *   associated with this buildpath entry, or <code>null</code> if this kind
	 *   of buildpath entry does not support exclusion patterns
	 *
	 */
	IPath[] getExclusionPatterns();
	/**
	 * Returns the extra buildpath attributes for this buildpath entry. Returns an empty array if this entry
	 * has no extra attributes.
	 * 
	 * @return the possibly empty list of extra buildpath attributes for this buildpath entry
	 *
	 */
	IBuildpathAttribute[] getExtraAttributes();
	
	/**
	 * Returns the set of patterns used to explicitly define resources or classes
	 * to be included with this buildpath entry.
	 * <p>
	 * For source buildpath entries,
	 * when no inclusion patterns are specified, the source entry includes all
	 * relevent files in the resource tree rooted at this source entry's path.
	 * Specifying one or more inclusion patterns means that only the specified
	 * portions of the resource tree are to be included. Each path specified
	 * must be a relative path, and will be interpreted relative to this source
	 * entry's path. File patterns are case-sensitive. A file matched by one or
	 * more of these patterns is included in the corresponding package fragment
	 * root unless it is excluded by one or more of this entrie's exclusion
	 * patterns. Exclusion patterns have higher precedence than inclusion
	 * patterns; in other words, exclusion patterns can remove files for the
	 * ones that are to be included, not the other way around.
	 * </p>
	 * <p>
	 * See {@link #getExclusionPatterns()} for a discussion of the syntax and
	 * semantics of path patterns. The absence of any inclusion patterns is
	 * semantically equivalent to the explicit inclusion pattern
	 * <code>&#42;&#42;</code>.
	 * </p>
	 * <p>
	 * Example patterns in source entries:
	 * <ul>
	 * <li>
	 * The inclusion pattern <code>src/&#42;&#42;</code> by itself includes all
	 * files under a root folder named <code>src</code>.
	 * </li>
	 * <li>
	 * The inclusion patterns <code>src/&#42;&#42;</code> and
	 * <code>tests/&#42;&#42;</code> includes all files under the root folders
	 * named <code>src</code> and <code>tests</code>.
	 * </li>
	 * <li>
	 * The inclusion pattern <code>src/&#42;&#42;</code> together with the
	 * exclusion pattern <code>src/&#42;&#42;/Foo.java</code> includes all
	 * files under a root folder named <code>src</code> except for ones
	 * named <code>Foo.java</code>.
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @return the possibly empty list of resource inclusion patterns 
	 *   associated with this buildpath entry, or <code>null</code> if this kind
	 *   of buildpath entry does not support inclusion patterns
	 *
	 */
	IPath[] getInclusionPatterns();

	
	/**
	 * Returns the path of this buildpath entry.
	 *
	 * The meaning of the path of a buildpath entry depends on its entry kind:<ul>
	 *	<li>Source code in the current project (<code>BPE_SOURCE</code>) -  
	 *      The path associated with this entry is the absolute path to the root folder. </li>
	 *	<li>A required project (<code>BPE_PROJECT</code>) - the path of the entry denotes the
	 *		path to the corresponding project resource.</li>
	 *  <li> A container entry (<code>BPE_CONTAINER</code>) - the path of the entry
	 * 	is the name of the buildpath container, which can be bound indirectly to a set of buildpath 
	 * 	entries after resolution. The containerPath is a formed by a first ID segment followed with 
	 *     extra segments that can be used as additional hints for resolving this container 
	 * 	reference (also see <code>IBuildpathContainer</code>).
	 * </li>
	 * </ul>
	 *
	 * @return the path of this buildpath entry
	 */
	IPath getPath();
	
	/**
	 * Returns whether this entry is exported to dependent projects.
	 * 
	 * @return <code>true</code> if exported, and <code>false</code> otherwise
	 */
	boolean isExported();
	
	boolean isExternal();
	public boolean isContainerEntry();
}

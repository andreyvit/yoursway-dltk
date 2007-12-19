package org.eclipse.dltk.tcl.core.extensions;

import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinBuildVisitor;

public interface IMixinBuildVisitorExtension {

	/**
	 * If method return true, then other checks will not be provided and
	 * original visit will return true
	 */
	boolean visit(MethodDeclaration s, TclMixinBuildVisitor original);

	/**
	 * If method return true, then other checks will not be provided and
	 * original visit will return true
	 */
	boolean visit(TypeDeclaration s, TclMixinBuildVisitor tclMixinBuildVisitor);

	/**
	 * If method return true, then other checks will not be provided and
	 * original visit will return <b>false</b>
	 */
	boolean visit(Statement s, TclMixinBuildVisitor tclMixinBuildVisitor);

}

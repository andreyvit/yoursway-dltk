package org.eclipse.dltk.tcl.internal.core.search;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.FieldReferenceMatch;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.internal.core.SourceMethod;

public class TclMatchLocator extends MatchLocator {

	public TclMatchLocator(SearchPattern pattern, SearchRequestor requestor,
			IDLTKSearchScope scope, IProgressMonitor progressMonitor) {
		super(pattern, requestor, scope, progressMonitor);
	}

	/*
	 * Create method handle. Store occurences for create handle to retrieve
	 * possible duplicate ones.
	 */
	protected IModelElement createMethodHandle(ISourceModule module,
			String methodName) {
		IMethod methodHandle = null;
		if (methodName.indexOf("::") != -1) {
			int pos = methodName.lastIndexOf("::");
			String cName = methodName.substring(0, pos);
			String name = methodName.substring(pos + 2);

			if (!cName.startsWith("$")) {
				cName = "$" + cName;
			}

			cName = cName.replaceAll("::", "\\$");
			if (!cName.equals("$")) {
				IType type = null;
				try {
					type = findTypeFrom(module.getChildren(), "", cName, '$');
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (type != null) {
					methodHandle = type.getMethod(name);
				}
			} else {
				methodHandle = module.getMethod(methodName);
			}
		} else {
			methodHandle = module.getMethod(methodName);
		}

		// TODO: make this more correctly without using SourceMethod from
		// internal package!!!
		if (methodHandle instanceof SourceMethod) {
			while (this.methodHandles.contains(methodHandle)) {
				((SourceMethod) methodHandle).occurrenceCount++;
			}
		}
		this.methodHandles.add(methodHandle);
		return methodHandle;
	}

	/**
	 * Creates an IMethod from the given method declaration and type.
	 */
	protected IModelElement createHandle(MethodDeclaration method,
			IModelElement parent) {
		// if (!(parent instanceof IType)) return parent;
		if (parent instanceof IType) {
			IType type = (IType) parent;
			return createMethodHandle(type, new String(method.getName()));
		} else if (parent instanceof ISourceModule) {
			if (method.getDeclaringTypeName() != null) {
				return createMethodHandle((ISourceModule) parent, method
						.getDeclaringTypeName()
						+ "::" + method.getName());
			} else {
				return createMethodHandle((ISourceModule) parent, method
						.getName());
			}
		}
		return null;
	}
}

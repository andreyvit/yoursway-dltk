package org.eclipse.dltk.core.mixin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;

public class MixinSourceElementRequestor implements ISourceElementRequestor {
	private List path = new ArrayList();
	private List modelPath = new ArrayList();
	private IMixinRequestor requestor;
	private boolean signature = false;
	private ISourceModule module;
	public MixinSourceElementRequestor(IMixinRequestor requestor,
			boolean signature, ISourceModule module) {
		this.requestor = requestor;
		this.signature = signature;
		this.module = module;
	}
	protected void enterElement( String path ) {
		this.path.add(path);
		// Also build model element path.
		if( signature ) {
			IModelElement element = this.module;
			if( modelPath.size() > 0 ) {
				element = getModelElement();
			}
			if( element instanceof IParent ) {
				IParent parent = (IParent)element;
				IModelElement[] children = null;
				try {
					children = parent.getChildren();
				} catch (ModelException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				for( int i = 0; i < children.length; ++i ) {
					if( children[i].getElementName().equals(path)) {
						this.modelPath.add(children[i]);
						return;
					}
				}
			}
			throw new RuntimeException("MixinSourceElementPaser: could not find appropriate model element...");
		}
	}
	
	protected IModelElement getModelElement() {
		
		if( this.modelPath.size() == 0 ) {
			return null;
		}
		return (IModelElement)this.modelPath.get(this.modelPath.size() - 1);
	}
	protected void exitElement() {
		if( path.size() > 0 ) {
			this.path.remove(this.path.size() - 1);
		}
		if( modelPath.size() > 0 && signature ) {
			this.modelPath.remove(this.modelPath.size() - 1);
		}
	}
	protected String getKey() {
		StringBuffer buffer = new StringBuffer();
		for( int i = 0; i < path.size(); ++i ) {
			buffer.append(path.get(i));
			if( i != path.size() -1 ) {
				buffer.append(IMixinRequestor.MIXIN_NAME_SEPARATOR);
			}
		}
		return buffer.toString();
	}
	
	public void acceptFieldReference(char[] fieldName, int sourcePosition) {
	}

	public void acceptMethodReference(char[] methodName, int argCount,
			int sourcePosition, int sourceEndPosition) {
	}

	public void acceptPackage(int declarationStart, int declarationEnd,
			char[] name) {
	}

	public void acceptTypeReference(char[][] typeName, int sourceStart,
			int sourceEnd) {
	}

	public void acceptTypeReference(char[] typeName, int sourcePosition) {
	}

	public boolean enterFieldCheckDuplicates(FieldInfo info) {
		return false;
	}

	public boolean enterFieldWithParentType(FieldInfo info, String parentName,
			String delimiter) {
		return false;
	}

	public void enterMethodRemoveSame(MethodInfo info) {
	}

	public boolean enterMethodWithParentType(MethodInfo info,
			String parentName, String delimiter) {
		return false;
	}

	public void enterModule() {
	}

	public void enterModuleRoot() {
	}

	public boolean enterTypeAppend(String fullName, String delimiter) {
		return false;
	}


	public void exitModule(int declarationEnd) {
	}

	public void exitModuleRoot() {
	}

	public void exitField(int declarationEnd) {
		exitElement();
	}

	public void exitMethod(int declarationEnd) {
		exitElement();
	}

	public void exitType(int declarationEnd) {
		exitElement();
	}
	public void enterField(FieldInfo info) {
		enterElement(info.name);
		IMixinRequestor.ElementInfo elInfo = new IMixinRequestor.ElementInfo();
		elInfo.key = getKey();
		if( signature ) {
			elInfo.object = getModelElement();
		}
		requestor.reportElement(elInfo);
	}

	public void enterMethod(MethodInfo info) {
		enterElement(info.name);
		IMixinRequestor.ElementInfo elInfo = new IMixinRequestor.ElementInfo();
		elInfo.key = getKey();
		if( signature ) {
			elInfo.object = getModelElement();
		}
		requestor.reportElement(elInfo);
	}

	public void enterType(TypeInfo info) {
		enterElement(info.name);
		IMixinRequestor.ElementInfo elInfo = new IMixinRequestor.ElementInfo();
		elInfo.key = getKey();
		if( signature ) {
			elInfo.object = getModelElement();
		}
		requestor.reportElement(elInfo);
	}
}

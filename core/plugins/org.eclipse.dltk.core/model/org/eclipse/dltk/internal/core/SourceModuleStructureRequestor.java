package org.eclipse.dltk.internal.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;


public class SourceModuleStructureRequestor implements ISourceElementRequestor {
	
	private final static String[] EMPTY = new String[0];
		
	/**
	 * The handle to the source module being parsed
	 */
	private ISourceModule module;

	/**
	 * The info object for the module being parsed
	 */
	private SourceModuleElementInfo moduleInfo;

	/**
	 * Hashtable of children elements of the source module.
	 * Children are added to the table as they are found by
	 * the parser. Keys are handles, values are corresponding
	 * info objects.
	 */
	private Map newElements;

	/**
	 * Stack of parent scope info objects. The info on the
	 * top of the stack is the parent of the next element found.
	 * For example, when we locate a method, the parent info object
	 * will be the type the method is contained in.
	 */
	private Stack infoStack;

	/**
	 * Stack of parent handles, corresponding to the info stack. We
	 * keep both, since info objects do not have back pointers to
	 * handles.
	 */
	private Stack handleStack;
	
	protected boolean hasSyntaxErrors = false;	
	
	SourceModuleStructureRequestor(ISourceModule module, SourceModuleElementInfo moduleInfo, Map newElements) {
		this.module = module;
		this.moduleInfo = moduleInfo;
		this.newElements = newElements;
	}

	/**
	 * Resolves duplicate handles by incrementing the occurrence count
	 * of the handle being created until there is no conflict.
	 */
	protected void resolveDuplicates(SourceRefElement handle) {
		while (this.newElements.containsKey(handle)) {
			handle.occurrenceCount++;
		}
	}
	
	public void enterModule() {
		this.infoStack = new Stack();
		this.handleStack= new Stack();
		enterModuleRoot();
	}
	
	public void enterModuleRoot() {
		this.infoStack.push(this.moduleInfo);
		this.handleStack.push(this.module);
	}

	public void enterField(FieldInfo fieldInfo) {				
		
		ModelElementInfo parentInfo = (ModelElementInfo) this.infoStack.peek();
		ModelElement parentHandle= (ModelElement) this.handleStack.peek();
		
		createField(fieldInfo, parentInfo, parentHandle);
	}
	
	private void createField(FieldInfo fieldInfo, ModelElementInfo parentInfo, ModelElement parentHandle) {
		ModelManager manager = ModelManager.getModelManager();

		SourceField handle = new SourceField(parentHandle, manager.intern(fieldInfo.name));
		resolveDuplicates(handle);
		
		SourceFieldElementInfo info = new SourceFieldElementInfo();
		info.setNameSourceStart(fieldInfo.nameSourceStart);
		info.setNameSourceEnd(fieldInfo.nameSourceEnd);
		info.setSourceRangeStart(fieldInfo.declarationStart);
		info.setFlags(fieldInfo.modifiers);

		parentInfo.addChild(handle);
		this.newElements.put(handle, info);

		this.infoStack.push(info);
		this.handleStack.push(handle);
	}
	
	public boolean enterFieldCheckDuplicates(FieldInfo fieldInfo, ModelElementInfo parentInfo, ModelElement parentHandle) {
		IModelElement[] childrens = parentInfo.getChildren( );
		for(int i = 0; i < childrens.length; ++i ) {
			if(childrens[i] instanceof SourceField 
					&& childrens[i].getElementName( ).equals( fieldInfo.name )) {
				//we should go inside existent element
				SourceField handle = (SourceField)childrens[i];
				SourceFieldElementInfo info = (SourceFieldElementInfo)newElements.get(handle);
				this.infoStack.push(info);
				this.handleStack.push(handle);
				return true;
			}
		}
		if( parentInfo instanceof SourceMethodElementInfo ) {
			SourceMethodElementInfo method = (SourceMethodElementInfo)parentInfo;
			String[] args = method.getArgumentNames();
			for( int i = 0; i < args.length; ++i ) {
				if( args[i].equals(fieldInfo.name)) {
					return false;
				}
			}
		}
		createField(fieldInfo, parentInfo, parentHandle);
		return true;
	}
	
	public boolean enterFieldCheckDuplicates(FieldInfo fieldInfo) {						
		ModelElementInfo parentInfo = (ModelElementInfo) this.infoStack.peek();
		ModelElement parentHandle= (ModelElement) this.handleStack.peek();
		return  enterFieldCheckDuplicates(fieldInfo, parentInfo, parentHandle);
	}
	
	public void enterMethodRemoveSame(MethodInfo methodInfo) {
		ModelElementInfo parentInfo = (ModelElementInfo) this.infoStack.peek();
		IModelElement[] childrens = parentInfo.getChildren( );
		for(int i = 0; i < childrens.length; ++i ) {
			if( childrens[i].getElementName( ).equals( methodInfo.name )) {
				parentInfo.removeChild( childrens[i] );
			}
		}
		enterMethod( methodInfo );
	}

	public void enterMethod(MethodInfo methodInfo) {
		ModelElementInfo parentInfo = (ModelElementInfo) this.infoStack.peek();
		ModelElement parentHandle= (ModelElement) this.handleStack.peek();
		
		processMethod(methodInfo, parentInfo, parentHandle);
	}

	private void processMethod(MethodInfo methodInfo, ModelElementInfo parentInfo, ModelElement parentHandle) {
		String nameString= new String(methodInfo.name);
		ModelManager manager = ModelManager.getModelManager();
		SourceMethod handle = new SourceMethod(parentHandle, manager.intern(nameString));
		resolveDuplicates(handle);
		
		SourceMethodElementInfo info = new SourceMethodElementInfo();
		info.setSourceRangeStart(methodInfo.declarationStart);
		info.setFlags(methodInfo.modifiers);
		info.setNameSourceStart(methodInfo.nameSourceStart);
		info.setNameSourceEnd(methodInfo.nameSourceEnd);
		
		String[] parameterNames = methodInfo.parameterNames == null ? 
				EMPTY : methodInfo.parameterNames;
		
		String[] parameterInitializers = methodInfo.parameterInitializers == null ? 
				EMPTY : methodInfo.parameterInitializers;
		
		if(parameterNames.length != parameterInitializers.length ) {
			parameterInitializers = new String[parameterNames.length];
		}
			
		for (int i = 0, length = parameterNames.length; i < length; i++) {
			parameterNames[i] = manager.intern(parameterNames[i]);
			if( parameterInitializers[i] != null ) {
				parameterInitializers[i] = manager.intern(parameterInitializers[i]);
			}
		}
		info.setArgumentNames(parameterNames);
		info.setArgumentInializers(parameterInitializers);
		
		parentInfo.addChild(handle);
		this.newElements.put(handle, info);
		this.infoStack.push(info);
		this.handleStack.push(handle);
	}
	
	/**
	 * Returns type in which we currently are. If we are not in type, returns null.
	 * @return
	 */
	private SourceType getCurrentType () {
		SourceType t = null;
		for (Iterator iter = this.handleStack.iterator(); iter.hasNext();) {
			Object o = iter.next();
			if (o instanceof SourceType)
				t = (SourceType)o;
		}
		return t;
	}
	
	/**
	 * Searches for a type already in the model. If founds, returns it.
	 * If <code>parentName</code> starts with a delimeter, searches starting 
	 * from current source module (i.e. in global), else from the current
	 * level.
	 * @param parentName
	 * @param delimiter
	 * @return null if type not found
	 */
	private SourceType getExistentType (String parentName, String delimiter) {
		try {
			SourceType element = null;
			if (parentName.startsWith(delimiter)) {				
				element = findTypeFrom( this.module.getChildren(), "", parentName, delimiter );
				return element;	
			} else {
				parentName = delimiter + parentName;
				SourceType enc = getCurrentType();
				if (enc == null) {
					element = findTypeFrom( this.module.getChildren(), "", parentName, delimiter );
				} else {
					element = findTypeFrom( enc.getChildren(), "", parentName, delimiter );
				}
				return element;
			}
			
		} catch (ModelException e) {
			e.printStackTrace();			
		}		
		return null;
	}
	
	private SourceType findTypeFrom(IModelElement[] childs, String name, String parentName, String delimiter) {
		try {			
			for( int i = 0; i < childs.length; ++i ) {
				if( childs[i] instanceof SourceType ) {
					SourceType type = (SourceType)childs[i];
					String qname = name + delimiter + type.getElementName();
					if( qname.equals(parentName)) {
						return type;
					}
					SourceType val = findTypeFrom(type.getChildren(), qname,  parentName, delimiter);
					if( val != null ) {
						return val;
					}
				}
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public boolean enterMethodWithParentType(MethodInfo info, String parentName, String delimiter) {
		try {
			ModelElement element = getExistentType(parentName, delimiter );
			if( element == null) {
				return false;
			}
			ModelElementInfo typeInfo = (ModelElementInfo)element.getElementInfo();
			
			IModelElement[] childrens = typeInfo.getChildren( );
			for(int i = 0; i < childrens.length; ++i ) {
				if( childrens[i].getElementName( ).equals( info.name )) {
					typeInfo.removeChild( childrens[i] );
				}
			}
			
			processMethod(info, typeInfo, element);
			return true;
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean enterFieldWithParentType(FieldInfo info, String parentName, String delimiter) {
		try {
			ModelElement element = getExistentType(parentName, delimiter );			
			if( element == null) {
				return false;
			}
			ModelElementInfo typeInfo = (ModelElementInfo)element.getElementInfo();
			enterFieldCheckDuplicates(info, typeInfo, element);
			return true;
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

	public void enterType(TypeInfo typeInfo) {
		ModelElementInfo parentInfo = (ModelElementInfo) this.infoStack.peek();
		ModelElement parentHandle= (ModelElement) this.handleStack.peek();
		processType(typeInfo, parentInfo, parentHandle);		
	}
	
	
	
	public boolean enterTypeAppend(String fullName, String delimiter ) {
		try {
			ModelElement element = getExistentType(fullName, delimiter );			
			if( element == null) {				
				return false;
			} else {
				ModelElementInfo info = (ModelElementInfo)element.getElementInfo();			
				this.infoStack.push(info);
				this.handleStack.push(element);
				return true;
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}		
		return false;
	}

	private void processType(TypeInfo typeInfo, ModelElementInfo parentInfo, ModelElement parentHandle) {
		String nameString= new String(typeInfo.name);
		SourceType handle = new SourceType(parentHandle, nameString); //NB: occurenceCount is computed in resolveDuplicates
		resolveDuplicates(handle);
		
		SourceTypeElementInfo info = new SourceTypeElementInfo();
		info.setHandle(handle);
		info.setSourceRangeStart(typeInfo.declarationStart);
		info.setFlags(typeInfo.modifiers);
		info.setNameSourceStart(typeInfo.nameSourceStart);
		info.setNameSourceEnd(typeInfo.nameSourceEnd);
		ModelManager manager = ModelManager.getModelManager();
		String[] superclasses = typeInfo.superclasses;
		for (int i = 0, length = superclasses == null ? 0 : superclasses.length; i < length; i++)
			superclasses[i] = manager.intern(superclasses[i]);
		info.setSuperclassNames(superclasses);
		parentInfo.addChild(handle);
		this.newElements.put(handle, info);
		this.infoStack.push(info);
		this.handleStack.push(handle);
	}

	public void exitModule(int declarationEnd) {
		moduleInfo.setSourceLength(declarationEnd + 1);

		// determine if there were any parsing errors
		moduleInfo.setIsStructureKnown(!hasSyntaxErrors);
	}
	
	public void exitModuleRoot() {
		this.infoStack.pop();
		this.handleStack.pop();
	}

	public void exitField(int declarationEnd) {
		exitMember(declarationEnd);
	}

	public void exitMethod(int declarationEnd) {
		exitMember(declarationEnd);
	}

	public void exitType(int declarationEnd) {
		exitMember(declarationEnd);
	}
	
	protected void exitMember(int declarationEnd) {
		SourceRefElementInfo info = (SourceRefElementInfo) this.infoStack.pop();
		info.setSourceRangeEnd(declarationEnd);
		this.handleStack.pop();
	}

	public void acceptPackage(int declarationStart, int declarationEnd, char[] name) {	
		ModelElementInfo parentInfo = (ModelElementInfo) this.infoStack.peek();
		ModelElement parentHandle= (ModelElement) this.handleStack.peek();
		PackageDeclaration handle = null;
		
		//if (parentHandle.getElementType() == IModelElement.SOURCE_MODULE) {
		handle = new PackageDeclaration(parentHandle, new String(name));
		
		resolveDuplicates(handle);
		
		SourceRefElementInfo info = new SourceRefElementInfo();
		info.setSourceRangeStart(declarationStart);
		info.setSourceRangeEnd(declarationEnd);		
		
		parentInfo.addChild(handle);
		this.newElements.put(handle, info);
	}

	public void acceptFieldReference(char[] fieldName, int sourcePosition) {
		// TODO Auto-generated method stub
	}

	public void acceptMethodReference(char[] methodName, int argCount, int sourcePosition, int sourceEndPosition) {
		// TODO Auto-generated method stub
	}

	public void acceptTypeReference(char[][] typeName, int sourceStart, int sourceEnd) {
		// TODO Auto-generated method stub
	}

	public void acceptTypeReference(char[] typeName, int sourcePosition) {
		// TODO Auto-generated method stub		
	}
}

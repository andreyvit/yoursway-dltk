package org.eclipse.dltk.core.tests.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;



public class ModelTestUtils
{

	public static void assertParameterNames( IMethod method, String[] names ) throws Exception {
		String[] params = method.getParameters();
		TestCase.assertNotNull( params );
		int index = 0;
		for( int i = 0; i < params.length; ++i ) {		
			TestCase.assertNotNull(params[i]);
			TestCase.assertEquals( params[i],  names[index] );
			index++;
		}
	}

	/**
	 * Used to count classes, field and methods in collection. throws assert
	 * expression if not equal to specified counts.
	 * 
	 * @param moduleChildren
	 * @param classCountNeed
	 * @param fieldCountNeed
	 * @param methodCountNeed
	 * @throws Exception
	 */
	public static void counterAssert( IModelElement[] children, int classCountNeed, int fieldCountNeed, int methodCountNeed)
			throws Exception
	{
		int classCount = 0;
		int fieldCount = 0;
		int methodCount = 0;
		
		IModelElement parent = getElementsParent(children);
		if(parent==null){
			TestCase.assertTrue("Can't get children's parent",false);
		}
		
		Collection classes = new ArrayList(1);
		Collection methods = new ArrayList(1);
		Collection fields = new ArrayList(1);
		
		getElementChildren(parent,classes,methods,fields);
	
		classCount = classes.size();
		methodCount = methods.size();
		fieldCount = fields.size();
		
		
		TestCase.assertEquals(classCountNeed, classCount);
		TestCase.assertEquals(fieldCountNeed, fieldCount);
		TestCase.assertEquals(methodCountNeed, methodCount);
	}

	/**
	 * Assert check and return filed by name.
	 * 
	 * @param elements
	 * @param name
	 * @return
	 */
	
	public static IField getAssertField( IModelElement[] elements, String name) throws Exception
	{
	
		IModelElement parent = getElementsParent(elements);
		if(parent==null){
			TestCase.assertTrue("Can't get children's parent",false);
		}
		
		Collection classes = new ArrayList(1);
		Collection methods = new ArrayList(1);
		Collection fields = new ArrayList(1);
		
		getElementChildren(parent,classes,methods,fields);
		
		Iterator i = fields.iterator();
		while( i.hasNext() ) {
			IModelElement element = (IModelElement)i.next();
			
			IField field = (IField) element;
			TestCase.assertNotNull(field);
			if( field.getElementName().equals(name) ) {
				return field;
			}
			
		}
		TestCase.assertEquals("Field not exist", "", name);
		return null;
	}

	/**
	 * Assert check and return method by name. Also checks for arguments count
	 * to determine method.
	 */
	
	public static IMethod getAssertMethod( IModelElement[] elements, String name, int argCount) throws Exception
	{
		IModelElement parent = getElementsParent(elements);
		if(parent==null){
			TestCase.assertTrue("Can't get children's parent",false);
		}
		
		Collection classes = new ArrayList(1);
		Collection methods = new ArrayList(1);
		Collection fields = new ArrayList(1);
		
		getElementChildren(parent,classes,methods,fields);
		
		Iterator i = methods.iterator();
		while( i.hasNext() ) {
			IModelElement element = (IModelElement)i.next();

			
			IMethod method = (IMethod) element;
			TestCase.assertNotNull(method);
			if( method.getElementName().equals(name) ) {
				String[] params = method.getParameters();
				TestCase.assertNotNull(params);
				TestCase.assertEquals(argCount, params.length );

				return method;
			}
		}
		TestCase.assertEquals("Method not exist", "", name);
		return null;
	}
	/**
	 * Assert check and return method by name. Also checks for arguments count
	 * to determine method.
	 * 
	 * Multiple declarations of one class in one module is ignored.
	 */	
	public static IType getAssertClass( IModelElement[] elements, String name ) throws Exception
	{
		IModelElement parent = getElementsParent(elements);
		if(parent==null){
			TestCase.assertTrue("Can't get children's parent",false);
		}
		
		Collection classes = new ArrayList(1);
		Collection methods = new ArrayList(1);
		Collection fields = new ArrayList(1);
		
		getElementChildren(parent,classes,methods,fields);
		Iterator i = classes.iterator();
		while( i.hasNext() ) {
			IModelElement element = (IModelElement)i.next();

			
			IType classElement= (IType) element;
			TestCase.assertNotNull( classElement );
			if( classElement.getElementName().equals(name) ) {									
				return classElement;
			}
			
		}
		TestCase.assertEquals("Class not exist", "", name);
		return null;
	}
	private static void getElementChildren(IModelElement element,Collection classes, 
			Collection methods, Collection fields) throws ModelException{
		
		classes.clear();
		fields.clear();
		methods.clear();
		
		//we don't use IModelElement children getting here cause we want to test each model element
		//descendant's getters.
		
		//checking parent element type
		if(element.getElementType()==IModelElement.SOURCE_MODULE){
			IModule parentModule = (IModule)element;
			
			IModelElement[] childs = parentModule.getChildren();			
			
			for( int i = 0; i < childs.length; ++i ) {
				IModelElement c = childs[i];
				if( c.getElementType() == IModelElement.FIELD ) {
					fields.add( c );
				}
				else if( c.getElementType() == IModelElement.METHOD ) {
					methods.add( c );
				}
				else if( c.getElementType() == IModelElement.TYPE ) {
					classes.add( c );
				}
			}
			
		}else if(element.getElementType()==IModelElement.TYPE){
			IType parentClass = (IType)element;
						
			IModelElement[] childs = parentClass.getChildren();
			for( int i = 0; i < childs.length; ++i ) {
				IModelElement c = childs[i];
				if( c.getElementType() == IModelElement.FIELD ) {
					fields.add( c );
				}
				else if( c.getElementType() == IModelElement.METHOD ) {
					methods.add( c );
				}
				else if( c.getElementType() == IModelElement.TYPE ) {
					classes.add( c );
				}
			}			
		}else if(element.getElementType()==IModelElement.METHOD ){
			IMethod parentMethod = (IMethod)element;
			
			IModelElement[] childs = parentMethod.getChildren();
			for( int i = 0; i < childs.length; ++i ) {
				IModelElement c = childs[i];
				//if( c.getElementType() == IModelElement.FIELD ) {
				//	fields.add( (IField)c );
				//}
				if( c.getElementType() == IModelElement.METHOD ) {
					methods.add( c );
				}
				else if( c.getElementType() == IModelElement.TYPE ) {
					classes.add( c );
				}
			}	
			//classes.addAll(parentMethod.getClasses());
			//methods.addAll(parentMethod.getMethods());
		}else{
			return;
		}
	}
	
	private static IModelElement getElementsParent( IModelElement[] elements){
		if(elements.length ==0){
			return null;
		}				
		if( elements[ 0 ] != null ) {
			return elements[ 0 ].getParent();
		}
		return null;
	}
}

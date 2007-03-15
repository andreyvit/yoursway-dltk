package org.eclipse.dltk.internal.javascript.typeinference;




public class ReferenceFactory {

	public static IReference createNumberReference( String name,double number){
		return new UncknownReference(name,false);		
	}

	public static IReference createStringReference(String name,String string) {
		return new UncknownReference(name,false);
	}

	public static IReference createBooleanReference(String name,boolean b) {
		return new UncknownReference(name,false);
	}
}

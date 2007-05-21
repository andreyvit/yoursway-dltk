/**
 * 
 */
package com.xored.org.mozilla.javascript;


public class SimpleIntrospector extends NativeFunction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String[] getParameterNames(NativeFunction func){
		int paramCount = func.getParamCount();
		String[] result=new String[paramCount];
		for (int a=0;a<paramCount;a++)result[a]=func.getParamOrVarName(a);
		return result;
	}

	protected int getLanguageVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected int getParamAndVarCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected int getParamCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected String getParamOrVarName(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
package org.eclipse.dltk.typeinference;

public class ASTCaching {

	private ASTCaching() {
	}
	
	public static final ASTCaching ALLOW_ANY = new ASTCaching();
	
	public static final ASTCaching REPARSE = new ASTCaching();
	
	public static final ASTCaching CACHED_ONLY = new ASTCaching();
	
}

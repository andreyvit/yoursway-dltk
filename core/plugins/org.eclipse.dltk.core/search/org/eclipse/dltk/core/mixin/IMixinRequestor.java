package org.eclipse.dltk.core.mixin;

import org.eclipse.dltk.core.search.indexing.IIndexConstants;

public interface IMixinRequestor {
	public final static String MIXIN_NAME_SEPARATOR = "" + IIndexConstants.SEPARATOR;
	public static class ElementInfo {
		/**
		 * Could be seperated by MIXIN_NAME_SEPARATOR.
		 * If it is separated, then it added by splitting. 
		 * Then user ask for parent, it will contain this element.
		 */
		public String key;
		/**
		 * All possible user object.
		 */
		public Object object;
	}
	void reportElement( ElementInfo info );
}

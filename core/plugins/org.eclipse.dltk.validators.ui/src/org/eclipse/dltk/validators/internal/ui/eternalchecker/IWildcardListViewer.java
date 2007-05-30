package org.eclipse.dltk.validators.internal.ui.eternalchecker;

import org.eclipse.dltk.validators.internal.core.externalchecker.CustomWildcard;
import org.eclipse.dltk.validators.internal.core.externalchecker.Rule;

public interface IWildcardListViewer {
	public void addWildcard(CustomWildcard r);
	public void removeWildcard(CustomWildcard r);
	public void updateWildcard(CustomWildcard r);


}

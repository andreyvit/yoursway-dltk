package org.eclipse.dltk.ruby.internal.ui.search;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.search.DLTKSearchPage;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;

public class RubySearchPage extends DLTKSearchPage {
	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return RubyLanguageToolkit.getDefault();
	}
}

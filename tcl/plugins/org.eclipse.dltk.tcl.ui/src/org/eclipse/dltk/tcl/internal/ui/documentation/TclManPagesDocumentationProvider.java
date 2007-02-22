package org.eclipse.dltk.tcl.internal.ui.documentation;

import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;

public class TclManPagesDocumentationProvider implements
		IScriptDocumentationProvider {

	private IManPagesLocation[] locations;

	private int oldHash = -1;

	public Reader getInfo(IMember element, boolean lookIntoParents,
			boolean lookIntoExternal) {
		return null;
	}

	public Reader getInfo(String content) {
		initalizeLocations();
		for (int i = 0; i < locations.length; i++) {
			IManPagesLocation loc = locations[i];
			Reader reader = loc.getHtmlInfo(content);
			if (reader != null) { // TODO: what if several results there are?
				return reader;
			}
		}
		return null;
	}

	private void initalizeLocations() {
		String value = TclUI.getDefault().getPreferenceStore().getString(
				TclPreferenceConstants.DOC_MAN_PAGES_LOCATIONS);
		
		if (locations == null || value.hashCode() != oldHash) {
			oldHash = value.hashCode();
			final String[] locs = value.split(">");

			final List list = new ArrayList();
			for (int i = 0; i < locs.length; i++) {
				File file = new File(locs[i].trim());
				if (file.isDirectory()) {
					list.add(new HtmlManPagesLocation(file));
				}
			}

			locations = (IManPagesLocation[]) list
					.toArray(new IManPagesLocation[list.size()]);
		}
	}
}

package org.eclipse.dltk.ui.preferences;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.internal.ui.preferences.ScrolledPageContent;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

/**
 * Use as follows:
 * 
 * <pre>
 *  SectionManager manager= new SectionManager();
 *  Composite composite= manager.createSectionComposite(parent);
 *  
 *  Composite xSection= manager.createSection(&quot;section X&quot;));
 *  xSection.setLayout(new FillLayout());
 *  new Button(xSection, SWT.PUSH); // add controls to section..
 *  
 *  [...]
 *  
 *  return composite; // return main composite
 * </pre>
 */
public class SectionManager {
	/** The preference setting for keeping no section open. */
	private static final String __NONE = "__none"; //$NON-NLS-1$

	private Set fSections = new HashSet();

	private boolean fIsBeingManaged = false;

	private ExpansionAdapter fListener = new ExpansionAdapter() {
		public void expansionStateChanged(ExpansionEvent e) {
			ExpandableComposite source = (ExpandableComposite) e
					.getSource();
			updateSectionStyle(source);
			if (fIsBeingManaged)
				return;
			if (e.getState()) {
				try {
					fIsBeingManaged = true;
					for (Iterator iter = fSections.iterator(); iter
							.hasNext();) {
						ExpandableComposite composite = (ExpandableComposite) iter
								.next();
						if (composite != source)
							composite.setExpanded(false);
					}
				} finally {
					fIsBeingManaged = false;
				}
				if (fLastOpenKey != null && fDialogSettingsStore != null)
					fDialogSettingsStore.setValue(fLastOpenKey, source
							.getText());
			} else {
				if (!fIsBeingManaged && fLastOpenKey != null
						&& fDialogSettingsStore != null)
					fDialogSettingsStore.setValue(fLastOpenKey, __NONE);
			}
			ExpandableComposite exComp = getParentExpandableComposite(source);
			if (exComp != null)
				exComp.layout(true, true);
			ScrolledPageContent parentScrolledComposite = getParentScrolledComposite(source);
			if (parentScrolledComposite != null) {
				parentScrolledComposite.reflow(true);
			}
		}
	};
	
	 private final ExpandableComposite getParentExpandableComposite(
			Control control) {
		Control parent = control.getParent();
		while (!(parent instanceof ExpandableComposite) && parent != null) {
			parent = parent.getParent();
		}
		if (parent instanceof ExpandableComposite) {
			return (ExpandableComposite) parent;
		}
		return null;
	}


	protected final ScrolledPageContent getParentScrolledComposite(
			Control control) {
		Control parent = control.getParent();
		while (!(parent instanceof ScrolledPageContent) && parent != null) {
			parent = parent.getParent();
		}
		if (parent instanceof ScrolledPageContent) {
			return (ScrolledPageContent) parent;
		}
		return null;
	}

	private boolean isNestedInScrolledComposite(Composite parent) {
		return getParentScrolledComposite(parent) != null;
	}

	private Composite fBody;

	private final String fLastOpenKey;

	private final IPreferenceStore fDialogSettingsStore;

	private ExpandableComposite fFirstChild = null;

	/**
	 * Creates a new section manager.
	 */
	public SectionManager() {
		this(null, null);
	}

	/**
	 * Creates a new section manager.
	 */
	public SectionManager(IPreferenceStore dialogSettingsStore,
			String lastOpenKey) {
		fDialogSettingsStore = dialogSettingsStore;
		fLastOpenKey = lastOpenKey;
	}

	private void manage(ExpandableComposite section) {
		if (section == null)
			throw new NullPointerException();
		if (fSections.add(section))
			section.addExpansionListener(fListener);
		makeScrollableCompositeAware(section);
	}

	/**
	 * Creates a new composite that can contain a set of expandable
	 * sections. A <code>ScrolledPageComposite</code> is created and a new
	 * composite within that, to ensure that expanding the sections will
	 * always have enough space, unless there already is a
	 * <code>ScrolledComposite</code> along the parent chain of
	 * <code>parent</code>, in which case a normal <code>Composite</code>
	 * is created.
	 * <p>
	 * The receiver keeps a reference to the inner body composite, so that
	 * new sections can be added via <code>createSection</code>.
	 * </p>
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the newly created composite
	 */
	public Composite createSectionComposite(Composite parent) {
		Assert.isTrue(fBody == null);
		boolean isNested = isNestedInScrolledComposite(parent);
		Composite composite;
		if (isNested) {
			composite = new Composite(parent, SWT.NONE);
			fBody = composite;
		} else {
			composite = new ScrolledPageContent(parent);
			fBody = ((ScrolledPageContent) composite).getBody();
		}

		fBody.setLayout(new GridLayout());

		return composite;
	}
	
	 private void makeScrollableCompositeAware(Control control) {
		ScrolledPageContent parentScrolledComposite = getParentScrolledComposite(control);
		if (parentScrolledComposite != null) {
			parentScrolledComposite.adaptChild(control);
		}
	}

	protected void updateSectionStyle(ExpandableComposite excomposite) {
		excomposite.setFont(JFaceResources.getFontRegistry().getBold(
				JFaceResources.DIALOG_FONT));
	}

	/**
	 * Creates an expandable section within the parent created previously by
	 * calling <code>createSectionComposite</code>. Controls can be added
	 * directly to the returned composite, which has no layout initially.
	 * 
	 * @param label
	 *            the display name of the section
	 * @return a composite within the expandable section
	 */
	public Composite createSection(String label) {
		Assert.isNotNull(fBody);
		final ExpandableComposite excomposite = new ExpandableComposite(
				fBody, SWT.NONE, ExpandableComposite.TWISTIE
						| ExpandableComposite.CLIENT_INDENT
						| ExpandableComposite.COMPACT);
		if (fFirstChild == null)
			fFirstChild = excomposite;
		excomposite.setText(label);
		String last = null;
		if (fLastOpenKey != null && fDialogSettingsStore != null)
			last = fDialogSettingsStore.getString(fLastOpenKey);

		if (fFirstChild == excomposite && !__NONE.equals(last)
				|| label.equals(last)) {
			excomposite.setExpanded(true);
			if (fFirstChild != excomposite)
				fFirstChild.setExpanded(false);
		} else {
			excomposite.setExpanded(false);
		}
		excomposite.setLayoutData(new GridData(GridData.FILL,
				GridData.BEGINNING, true, false));

		updateSectionStyle(excomposite);
		manage(excomposite);

		Composite contents = new Composite(excomposite, SWT.NONE);
		excomposite.setClient(contents);

		return contents;
	}
}

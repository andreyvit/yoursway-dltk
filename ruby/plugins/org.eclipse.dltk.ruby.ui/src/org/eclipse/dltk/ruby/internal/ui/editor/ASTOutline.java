package org.eclipse.dltk.ruby.internal.ui.editor;


import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IContributedContentsView;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class ASTOutline extends PageBookView implements ISelectionProvider,
        ISelectionChangedListener {



    /**
     * The plugin prefix.
     */
    public static final String PREFIX = PlatformUI.PLUGIN_ID + "."; //$NON-NLS-1$

    /**
     * Help context id used for the content outline view
     * (value <code>"org.eclipse.ui.content_outline_context"</code>).
     */
    public static final String CONTENT_OUTLINE_VIEW_HELP_CONTEXT_ID = PREFIX
            + "content_outline_context";//$NON-NLS-1$

    /**
     * Message to show on the default page.
     */
    private String defaultText = "no outline"; 

    /**
     * Creates a content outline view with no content outline pages.
     */
    public ASTOutline() {
        super();
    }

    /* (non-Javadoc)
     * Method declared on ISelectionProvider.
     */
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        getSelectionProvider().addSelectionChangedListener(listener);
    }

    /* (non-Javadoc)
     * Method declared on PageBookView.
     */
    protected IPage createDefaultPage(PageBook book) {
        MessagePage page = new MessagePage();
        initPage(page);
        page.createControl(book);
        page.setMessage(defaultText);
        return page;
    }

    /**
     * The <code>PageBookView</code> implementation of this <code>IWorkbenchPart</code>
     * method creates a <code>PageBook</code> control with its default page showing.
     */
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(getPageBook(),
                CONTENT_OUTLINE_VIEW_HELP_CONTEXT_ID);
    }

    /* (non-Javadoc)
     * Method declared on PageBookView.
     */
    protected PageRec doCreatePage(IWorkbenchPart part) {
        // Try to get an outline page.
    	if (part instanceof ScriptEditor) {
            ScriptEditor scriptEditor = (ScriptEditor) part;
			IContentOutlinePage page = new ScriptASTPage(scriptEditor);
            if (page instanceof IPageBookViewPage) {
				initPage((IPageBookViewPage) page);
			}
            page.createControl(getPageBook());
            return new PageRec(part, page);
    	}
        // There is no content outline
        return null;
    }

    /* (non-Javadoc)
     * Method declared on PageBookView.
     */
    protected void doDestroyPage(IWorkbenchPart part, PageRec rec) {
        IContentOutlinePage page = (IContentOutlinePage) rec.page;
        page.dispose();
        rec.dispose();
    }

    /* (non-Javadoc)
     * Method declared on IAdaptable.
     */
    public Object getAdapter(Class key) {
        if (key == IContributedContentsView.class) {
			return new IContributedContentsView() {
                public IWorkbenchPart getContributingPart() {
                    return getContributingEditor();
                }
            };
		}
        return super.getAdapter(key);
    }

    /* (non-Javadoc)
     * Method declared on PageBookView.
     */
    protected IWorkbenchPart getBootstrapPart() {
        IWorkbenchPage page = getSite().getPage();
        if (page != null) {
			return page.getActiveEditor();
		}

        return null;
    }

    /**
     * Returns the editor which contributed the current 
     * page to this view.
     *
     * @return the editor which contributed the current page
     * or <code>null</code> if no editor contributed the current page
     */
    private IWorkbenchPart getContributingEditor() {
        return getCurrentContributingPart();
    }

    /* (non-Javadoc)
     * Method declared on ISelectionProvider.
     */
    public ISelection getSelection() {
        // get the selection from the selection provider
        return getSelectionProvider().getSelection();
    }

    /* (non-Javadoc)
     * Method declared on PageBookView.
     * We only want to track editors.
     */
    protected boolean isImportant(IWorkbenchPart part) {
        //We only care about editors
        return (part instanceof IEditorPart);
    }

    /* (non-Javadoc)
     * Method declared on IViewPart.
     * Treat this the same as part activation.
     */
    public void partBroughtToTop(IWorkbenchPart part) {
        partActivated(part);
    }

    /* (non-Javadoc)
     * Method declared on ISelectionProvider.
     */
    public void removeSelectionChangedListener(
            ISelectionChangedListener listener) {
        getSelectionProvider().removeSelectionChangedListener(listener);
    }

    /* (non-Javadoc)
     * Method declared on ISelectionChangedListener.
     */
    public void selectionChanged(SelectionChangedEvent event) {
        getSelectionProvider().selectionChanged(event);
    }

    /* (non-Javadoc)
     * Method declared on ISelectionProvider.
     */
    public void setSelection(ISelection selection) {
        getSelectionProvider().setSelection(selection);
    }

    /**
     * The <code>ContentOutline</code> implementation of this <code>PageBookView</code> method
     * extends the behavior of its parent to use the current page as a selection provider.
     * 
     * @param pageRec the page record containing the page to show
     */
    protected void showPageRec(PageRec pageRec) {
        IPageSite pageSite = getPageSite(pageRec.page);
        ISelectionProvider provider = pageSite.getSelectionProvider();
        if (provider == null && (pageRec.page instanceof IContentOutlinePage)) {
			// This means that the page did not set a provider during its initialization 
            // so for backward compatibility we will set the page itself as the provider.
            pageSite.setSelectionProvider((IContentOutlinePage) pageRec.page);
		}
        super.showPageRec(pageRec);
    }
}

package org.eclipse.dltk.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.PositionUpdater;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlighting;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.texteditor.ITextEditor;

public abstract class AbstractDLTKUILanguageToolkit implements
		IDLTKUILanguageToolkit {

	public ScriptUILabelProvider createScriptUILabelProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	public ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		return null;
	}

	public String getDebugPreferencePage() {
		return null;
	}

	public IPreferenceStore getPreferenceStore() {
		return getUIPLugin().getPreferenceStore();
	}

	public boolean getProvideMembers(ISourceModule element) {
		return true;
	}

	public ScriptElementLabels getScriptElementLabels() {
		return new ScriptElementLabels();
	}

	protected abstract AbstractUIPlugin getUIPLugin();

	public String getEditorId(Object inputElement) {
		IDLTKLanguageToolkit toolkit = this.getCoreToolkit();
		String contentTypeID = toolkit.getLanguageContentType();
		if (contentTypeID == null) {
			return null;
		}
		IEditorRegistry editorRegistry = PlatformUI.getWorkbench()
				.getEditorRegistry();
		IContentTypeManager contentTypeManager = Platform
				.getContentTypeManager();
		IContentType contentType = contentTypeManager
				.getContentType(contentTypeID);
		if (contentType == null) {
			return null;
		}

		String fileName = null;
		if (inputElement instanceof ISourceModule) {
			fileName = ((ISourceModule) inputElement).getPath().toString();
		}
		else if( inputElement instanceof IResource ) { 
			fileName = ((IResource) inputElement).getFullPath().toString();	
		}

		IEditorDescriptor editor = editorRegistry.getDefaultEditor(fileName, contentType);
		if( editor != null ) {
			return editor.getId();
		}
		return null;
	}

	public String getInterpreterContainerId() {
		return null;
	}

	public String getInterpreterPreferencePage() {
		return null;
	}

	public String getPartitioningId() {
		return "__default_dltk_partitioning";
	}

	public ScriptTextTools getTextTools() {
		return new ScriptTextTools(getPartitioningId(), new String[0], true) {
			public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
					IPreferenceStore preferenceStore, ITextEditor editor,
					String partitioning) {
				return null;
			}

			public IPartitionTokenScanner getPartitionScanner() {
				return null;
			}

			public SemanticHighlighting[] getSemanticHighlightings() {
				return null;
			}

			public PositionUpdater getSemanticPositionUpdater() {
				return null;
			}
		};
	}

	public String[] getEditorPreferencePages() {
		return null;
	}
}

/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.dltk.internal.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.Highlighting;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlighting;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlightingPresenter;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlightingReconciler;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.IColorManagerExtension;
import org.eclipse.dltk.ui.text.ScriptPresentationReconciler;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * Semantic highlighting manager
 * 
 * @since 3.0
 */
public class SemanticHighlightingManager implements IPropertyChangeListener {

	/**
	 * Highlighted Positions.
	 */
	public static class HighlightedPosition extends Position {

		/** Highlighting of the position */
		private Highlighting fStyle;

		/** Lock object */
		private Object fLock;

		/**
		 * Initialize the styled positions with the given offset, length and
		 * foreground color.
		 * 
		 * @param offset
		 *            The position offset
		 * @param length
		 *            The position length
		 * @param highlighting
		 *            The position's highlighting
		 * @param lock
		 *            The lock object
		 */
		public HighlightedPosition(int offset, int length,
				Highlighting highlighting, Object lock) {
			super(offset, length);
			fStyle = highlighting;
			fLock = lock;
		}

		/**
		 * @return Returns a corresponding style range.
		 */
		public StyleRange createStyleRange() {
			int len = 0;
			if (fStyle.isEnabled())
				len = getLength();

			TextAttribute textAttribute = fStyle.getTextAttribute();
			int style = textAttribute.getStyle();
			int fontStyle = style & (SWT.ITALIC | SWT.BOLD | SWT.NORMAL);
			StyleRange styleRange = new StyleRange(getOffset(), len,
					textAttribute.getForeground(), textAttribute
							.getBackground(), fontStyle);
			styleRange.strikeout = (style & TextAttribute.STRIKETHROUGH) != 0;
			styleRange.underline = (style & TextAttribute.UNDERLINE) != 0;

			return styleRange;
		}

		/**
		 * Uses reference equality for the highlighting.
		 * 
		 * @param off
		 *            The offset
		 * @param len
		 *            The length
		 * @param highlighting
		 *            The highlighting
		 * @return <code>true</code> iff the given offset, length and
		 *         highlighting are equal to the internal ones.
		 */
		public boolean isEqual(int off, int len, Highlighting highlighting) {
			synchronized (fLock) {
				return !isDeleted() && getOffset() == off && getLength() == len
						&& fStyle == highlighting;
			}
		}

		/**
		 * Is this position contained in the given range (inclusive)?
		 * Synchronizes on position updater.
		 * 
		 * @param off
		 *            The range offset
		 * @param len
		 *            The range length
		 * @return <code>true</code> iff this position is not delete and
		 *         contained in the given range.
		 */
		public boolean isContained(int off, int len) {
			synchronized (fLock) {
				return !isDeleted() && off <= getOffset()
						&& off + len >= getOffset() + getLength();
			}
		}

		public void update(int off, int len) {
			synchronized (fLock) {
				super.setOffset(off);
				super.setLength(len);
			}
		}

		/*
		 * @see org.eclipse.jface.text.Position#setLength(int)
		 */
		public void setLength(int length) {
			synchronized (fLock) {
				super.setLength(length);
			}
		}

		/*
		 * @see org.eclipse.jface.text.Position#setOffset(int)
		 */
		public void setOffset(int offset) {
			synchronized (fLock) {
				super.setOffset(offset);
			}
		}

		/*
		 * @see org.eclipse.jface.text.Position#delete()
		 */
		public void delete() {
			synchronized (fLock) {
				super.delete();
			}
		}

		/*
		 * @see org.eclipse.jface.text.Position#undelete()
		 */
		public void undelete() {
			synchronized (fLock) {
				super.undelete();
			}
		}

		/**
		 * @return Returns the highlighting.
		 */
		public Highlighting getHighlighting() {
			return fStyle;
		}

		public boolean equals(Object other) {
			return this == other;
		}

		public int hashCode() {
			return System.identityHashCode(this);
		}
	}

	/**
	 * Highlighted ranges.
	 */
	public static class HighlightedRange extends Region {
		/**
		 * The highlighting key as returned by
		 * {@link SemanticHighlighting#getPreferenceKey()}.
		 */
		private String fKey;

		/**
		 * Initialize with the given offset, length and highlighting key.
		 * 
		 * @param offset
		 * @param length
		 * @param key
		 *            the highlighting key as returned by
		 *            {@link SemanticHighlighting#getPreferenceKey()}
		 */
		public HighlightedRange(int offset, int length, String key) {
			super(offset, length);
			fKey = key;
		}

		/**
		 * @return the highlighting key as returned by
		 *         {@link SemanticHighlighting#getPreferenceKey()}
		 */
		public String getKey() {
			return fKey;
		}

		/*
		 * @see org.eclipse.jface.text.Region#equals(java.lang.Object)
		 */
		public boolean equals(Object o) {
			return super.equals(o) && o instanceof HighlightedRange
					&& fKey.equals(((HighlightedRange) o).getKey());
		}

		/*
		 * @see org.eclipse.jface.text.Region#hashCode()
		 */
		public int hashCode() {
			return super.hashCode() | fKey.hashCode();
		}
	}

	/** Semantic highlighting presenter */
	private SemanticHighlightingPresenter fPresenter;
	/** Semantic highlighting reconciler */
	private SemanticHighlightingReconciler fReconciler;

	/** Semantic highlightings */
	private SemanticHighlighting[] fSemanticHighlightings;
	/** Highlightings */
	private Highlighting[] fHighlightings;

	/** The editor */
	private ScriptEditor fEditor;
	/** The source viewer */
	private ScriptSourceViewer fSourceViewer;
	/** The color manager */
	private IColorManager fColorManager;
	/** The preference store */
	private IPreferenceStore fPreferenceStore;
	/** The source viewer configuration */
	private ScriptSourceViewerConfiguration fConfiguration;
	/** The presentation reconciler */
	private ScriptPresentationReconciler fPresentationReconciler;

	/** The hard-coded ranges */
	private HighlightedRange[][] fHardcodedRanges;

	/**
	 * Install the semantic highlighting on the given editor infrastructure
	 * 
	 * @param editor
	 *            The Script editor
	 * @param sourceViewer
	 *            The source viewer
	 * @param colorManager
	 *            The color manager
	 * @param preferenceStore
	 *            The preference store
	 */
	public void install(ScriptEditor editor, ScriptSourceViewer sourceViewer,
			IColorManager colorManager, IPreferenceStore preferenceStore) {
		fEditor = editor;
		fSourceViewer = sourceViewer;
		fColorManager = colorManager;
		fPreferenceStore = preferenceStore;
		if (fEditor != null) {
			ScriptTextTools textTools = editor.getTextTools();
			if (textTools != null) {
				fConfiguration = textTools.createSourceViewerConfiguraton(
						preferenceStore, editor);
				IPresentationReconciler presReconciler = fConfiguration
						.getPresentationReconciler(sourceViewer);
				if (presReconciler instanceof ScriptPresentationReconciler) {
					fPresentationReconciler = (ScriptPresentationReconciler) presReconciler;
				}
				else {
					fPresentationReconciler = null;
				}
			} else {
				fConfiguration = null;
				fPresentationReconciler = null;
			}
		} else {
			fConfiguration = null;
			fPresentationReconciler = null;
		}

		fPreferenceStore.addPropertyChangeListener(this);

		if (isEnabled())
			enable();
	}

	/**
	 * Install the semantic highlighting on the given source viewer
	 * infrastructure. No reconciliation will be performed.
	 * 
	 * @param sourceViewer
	 *            the source viewer
	 * @param colorManager
	 *            the color manager
	 * @param preferenceStore
	 *            the preference store
	 * @param hardcodedRanges
	 *            the hard-coded ranges to be highlighted
	 */
	public void install(ScriptSourceViewer sourceViewer,
			IColorManager colorManager, IPreferenceStore preferenceStore,
			HighlightedRange[][] hardcodedRanges) {
		fHardcodedRanges = hardcodedRanges;
		install(null, sourceViewer, colorManager, preferenceStore);
	}

	/**
	 * Enable semantic highlighting.
	 */
	private void enable() {
		initializeHighlightings();

		fPresenter = new SemanticHighlightingPresenter();
		fPresenter.install(fSourceViewer, fPresentationReconciler);

		if (fEditor != null) {
			fReconciler = new SemanticHighlightingReconciler();
			fReconciler.install(fEditor, fSourceViewer, fPresenter,
					fSemanticHighlightings, fHighlightings);
		} else {
			fPresenter.updatePresentation(null, createHardcodedPositions(),
					new HighlightedPosition[0]);
		}
	}

	/**
	 * Computes the hard-coded positions from the hard-coded ranges
	 * 
	 * @return the hard-coded positions
	 */
	private HighlightedPosition[] createHardcodedPositions() {
		List positions = new ArrayList();
		for (int i = 0; i < fHardcodedRanges.length; i++) {
			HighlightedRange range = null;
			Highlighting hl = null;
			for (int j = 0; j < fHardcodedRanges[i].length; j++) {
				hl = getHighlighting(fHardcodedRanges[i][j].getKey());
				if (hl.isEnabled()) {
					range = fHardcodedRanges[i][j];
					break;
				}
			}

			if (range != null)
				positions.add(fPresenter.createHighlightedPosition(range
						.getOffset(), range.getLength(), hl));
		}
		return (HighlightedPosition[]) positions
				.toArray(new HighlightedPosition[positions.size()]);
	}

	/**
	 * Returns the highlighting corresponding to the given key.
	 * 
	 * @param key
	 *            the highlighting key as returned by
	 *            {@link SemanticHighlighting#getPreferenceKey()}
	 * @return the corresponding highlighting
	 */
	private Highlighting getHighlighting(String key) {
		for (int i = 0; i < fSemanticHighlightings.length; i++) {
			SemanticHighlighting semanticHighlighting = fSemanticHighlightings[i];
			if (key.equals(semanticHighlighting.getPreferenceKey()))
				return fHighlightings[i];
		}
		return null;
	}

	public Highlighting getHighlighting(int pos) {
		return fHighlightings[pos];
	}

	/**
	 * Uninstall the semantic highlighting
	 */
	public void uninstall() {
		disable();

		if (fPreferenceStore != null) {
			fPreferenceStore.removePropertyChangeListener(this);
			fPreferenceStore = null;
		}

		fEditor = null;
		fSourceViewer = null;
		fColorManager = null;
		fConfiguration = null;
		fPresentationReconciler = null;
		fHardcodedRanges = null;
	}

	/**
	 * Disable semantic highlighting.
	 */
	private void disable() {
		if (fReconciler != null) {
			fReconciler.uninstall();
			fReconciler = null;
		}

		if (fPresenter != null) {
			fPresenter.uninstall();
			fPresenter = null;
		}

		if (fSemanticHighlightings != null)
			disposeHighlightings();
	}

	/**
	 * @return <code>true</code> iff semantic highlighting is enabled in the
	 *         preferences
	 */
	private boolean isEnabled() {
		return true;
	}

	private TextAttribute createTextAttribute(IColorManager manager,
			IPreferenceStore ps, String colorKey, String boldKey,
			String italicKey, String strikethroughKey, String underlineKey,
			String bgKey) {

		addColor(colorKey);
		if (bgKey != null) {
			addColor(bgKey);
		}
		Color color = null;
		Color bgcolor = null;
		if (colorKey != null)
			color = manager.getColor(colorKey);
		if (bgKey != null)
			bgcolor = manager.getColor(bgKey);

		int style = ps.getBoolean(boldKey) ? SWT.BOLD : SWT.NORMAL;
		if (ps.getBoolean(italicKey))
			style |= SWT.ITALIC;

		if (ps.getBoolean(strikethroughKey))
			style |= TextAttribute.STRIKETHROUGH;

		if (ps.getBoolean(underlineKey))
			style |= TextAttribute.UNDERLINE;

		return new TextAttribute(color, bgcolor, style);
	}

	private TextAttribute createTextAttribute(IColorManager manager,
			IPreferenceStore ps, String colorKey, String bgKey) {
		return createTextAttribute(manager, ps, colorKey, colorKey
				+ PreferenceConstants.EDITOR_BOLD_SUFFIX, colorKey
				+ PreferenceConstants.EDITOR_ITALIC_SUFFIX, colorKey
				+ PreferenceConstants.EDITOR_STRIKETHROUGH_SUFFIX, colorKey
				+ PreferenceConstants.EDITOR_UNDERLINE_SUFFIX, bgKey);
	}

	/**
	 * Initialize semantic highlightings.
	 */
	private void initializeHighlightings() {
		ScriptTextTools textTools = fEditor.getTextTools();
		if (textTools != null) {
			fSemanticHighlightings = textTools.getSemanticHighlightings();
			fHighlightings = new Highlighting[fSemanticHighlightings.length];
			for (int a = 0; a < fSemanticHighlightings.length; a++) {
				fHighlightings[a] = new Highlighting(
						createTextAttribute(fColorManager, fPreferenceStore,
								fSemanticHighlightings[a].getPreferenceKey(),
								fSemanticHighlightings[a]
										.getBackgroundPreferenceKey()), true,
						fSemanticHighlightings[a]);
			}
		}
	}

	/**
	 * Dispose the semantic highlightings.
	 */
	private void disposeHighlightings() {
		for (int i = 0, n = fSemanticHighlightings.length; i < n; i++)
			removeColor(fSemanticHighlightings[i].getPreferenceKey());

		fSemanticHighlightings = null;
		fHighlightings = null;
	}

	/*
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		handlePropertyChangeEvent(event);
	}

	/**
	 * Handle the given property change event
	 * 
	 * @param event
	 *            The event
	 */
	private void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (fPreferenceStore == null)
			return; // Uninstalled during event notification

		if (fConfiguration != null)
			fConfiguration.handlePropertyChangeEvent(event);

		// if (SemanticHighlightings.affectsEnablement(fPreferenceStore, event))
		// {
		// if (isEnabled())
		// enable();
		// else
		// disable();
		// }

		if (!isEnabled())
			return;

		boolean refreshNeeded = false;

		for (int i = 0, n = fSemanticHighlightings.length; i < n; i++) {
			SemanticHighlighting semanticHighlighting = fSemanticHighlightings[i];

			String preferenceKey = semanticHighlighting.getPreferenceKey();
			if (preferenceKey != null)
				if (preferenceKey.equals(event.getProperty())) {
					adaptToTextForegroundChange(fHighlightings[i], event);
					fPresenter.highlightingStyleChanged(fHighlightings[i]);
					refreshNeeded = true;
					continue;
				}
			String bpreferenceKey = semanticHighlighting
					.getBackgroundPreferenceKey();
			if (bpreferenceKey != null)
				if (bpreferenceKey.equals(event.getProperty())) {
					adaptToTextBackgroundChange(fHighlightings[i], event);
					fPresenter.highlightingStyleChanged(fHighlightings[i]);
					refreshNeeded = true;
					continue;
				}

			String boldKey = preferenceKey
					+ PreferenceConstants.EDITOR_BOLD_SUFFIX;
			if (boldKey.equals(event.getProperty())) {
				adaptToTextStyleChange(fHighlightings[i], event, SWT.BOLD);
				fPresenter.highlightingStyleChanged(fHighlightings[i]);
				refreshNeeded = true;
				continue;
			}

			String italicKey = preferenceKey
					+ PreferenceConstants.EDITOR_ITALIC_SUFFIX;
			if (italicKey.equals(event.getProperty())) {
				adaptToTextStyleChange(fHighlightings[i], event, SWT.ITALIC);
				fPresenter.highlightingStyleChanged(fHighlightings[i]);
				refreshNeeded = true;
				continue;
			}

			String strikethroughKey = preferenceKey
					+ PreferenceConstants.EDITOR_STRIKETHROUGH_SUFFIX;
			if (strikethroughKey.equals(event.getProperty())) {
				adaptToTextStyleChange(fHighlightings[i], event,
						TextAttribute.STRIKETHROUGH);
				fPresenter.highlightingStyleChanged(fHighlightings[i]);
				refreshNeeded = true;
				continue;
			}

			String underlineKey = preferenceKey
					+ PreferenceConstants.EDITOR_UNDERLINE_SUFFIX;
			if (underlineKey.equals(event.getProperty())) {
				adaptToTextStyleChange(fHighlightings[i], event,
						TextAttribute.UNDERLINE);
				fPresenter.highlightingStyleChanged(fHighlightings[i]);
				refreshNeeded = true;
				continue;
			}

			// String enabledKey=
			// preferenceKey+PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE;
			// if (enabledKey.equals(event.getProperty())) {
			// adaptToEnablementChange(fHighlightings[i], event);
			// fPresenter.highlightingStyleChanged(fHighlightings[i]);
			// refreshNeeded= true;
			// continue;
			// }
		}

		if (refreshNeeded && fReconciler != null)
			fReconciler.refresh();
	}

	private void adaptToTextBackgroundChange(Highlighting highlighting,
			PropertyChangeEvent event) {
		RGB rgb = null;

		Object value = event.getNewValue();
		if (value instanceof RGB)
			rgb = (RGB) value;
		else if (value instanceof String)
			rgb = StringConverter.asRGB((String) value);

		if (rgb != null) {

			String property = event.getProperty();
			Color color = fColorManager.getColor(property);

			if ((color == null || !rgb.equals(color.getRGB()))
					&& fColorManager instanceof IColorManagerExtension) {
				IColorManagerExtension ext = (IColorManagerExtension) fColorManager;
				ext.unbindColor(property);
				ext.bindColor(property, rgb);
				color = fColorManager.getColor(property);
			}

			TextAttribute oldAttr = highlighting.getTextAttribute();
			highlighting.setTextAttribute(new TextAttribute(oldAttr
					.getForeground(), color, oldAttr.getStyle()));
		}
	}

	private void adaptToEnablementChange(Highlighting highlighting,
			PropertyChangeEvent event) {
		Object value = event.getNewValue();
		boolean eventValue;
		if (value instanceof Boolean)
			eventValue = ((Boolean) value).booleanValue();
		else if (IPreferenceStore.TRUE.equals(value))
			eventValue = true;
		else
			eventValue = false;
		highlighting.setEnabled(eventValue);
	}

	private void adaptToTextForegroundChange(Highlighting highlighting,
			PropertyChangeEvent event) {
		RGB rgb = null;

		Object value = event.getNewValue();
		if (value instanceof RGB)
			rgb = (RGB) value;
		else if (value instanceof String)
			rgb = StringConverter.asRGB((String) value);

		if (rgb != null) {

			String property = event.getProperty();
			Color color = fColorManager.getColor(property);

			if ((color == null || !rgb.equals(color.getRGB()))
					&& fColorManager instanceof IColorManagerExtension) {
				IColorManagerExtension ext = (IColorManagerExtension) fColorManager;
				ext.unbindColor(property);
				ext.bindColor(property, rgb);
				color = fColorManager.getColor(property);
			}

			TextAttribute oldAttr = highlighting.getTextAttribute();
			highlighting.setTextAttribute(new TextAttribute(color, oldAttr
					.getBackground(), oldAttr.getStyle()));
		}
	}

	private void adaptToTextStyleChange(Highlighting highlighting,
			PropertyChangeEvent event, int styleAttribute) {
		boolean eventValue = false;
		Object value = event.getNewValue();
		if (value instanceof Boolean)
			eventValue = ((Boolean) value).booleanValue();
		else if (IPreferenceStore.TRUE.equals(value))
			eventValue = true;

		TextAttribute oldAttr = highlighting.getTextAttribute();
		boolean activeValue = (oldAttr.getStyle() & styleAttribute) == styleAttribute;

		if (activeValue != eventValue)
			highlighting.setTextAttribute(new TextAttribute(oldAttr
					.getForeground(), oldAttr.getBackground(),
					eventValue ? oldAttr.getStyle() | styleAttribute : oldAttr
							.getStyle()
							& ~styleAttribute));
	}

	private void addColor(String colorKey) {
		if (fColorManager != null && colorKey != null
				&& fColorManager.getColor(colorKey) == null) {
			RGB rgb = PreferenceConverter.getColor(fPreferenceStore, colorKey);
			if (fColorManager instanceof IColorManagerExtension) {
				IColorManagerExtension ext = (IColorManagerExtension) fColorManager;
				ext.unbindColor(colorKey);
				ext.bindColor(colorKey, rgb);
			}
		}
	}

	private void removeColor(String colorKey) {
		if (fColorManager instanceof IColorManagerExtension)
			((IColorManagerExtension) fColorManager).unbindColor(colorKey);
	}

	/**
	 * Returns this hightlighter's reconciler.
	 * 
	 * @return the semantic highlighter reconciler or <code>null</code> if
	 *         none
	 * @since 3.3
	 */
	public SemanticHighlightingReconciler getReconciler() {
		return fReconciler;
	}
}

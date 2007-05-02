/*******************************************************************************
 * Copyright (c) 2006, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenamingNameSuggestor;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IDelegateUpdating;
import org.eclipse.dltk.internal.corext.refactoring.tagging.INameUpdating;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IQualifiedNameUpdating;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IReferenceUpdating;
import org.eclipse.dltk.internal.corext.refactoring.tagging.ISimilarDeclarationUpdating;
import org.eclipse.dltk.internal.corext.refactoring.tagging.ITextUpdating;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;


/**
 * Helper class to generate a refactoring descriptor comment.
 * 
	 *
 */
public final class ScriptRefactoringDescriptorComment {

	/** The element delimiter */
	private static final String ELEMENT_DELIMITER= RefactoringCoreMessages.ScriptRefactoringDescriptorComment_element_delimiter;

	/** The line delimiter */
	private static final String LINE_DELIMITER= System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Creates a composite setting.
	 * 
	 * @param caption
	 *            the caption
	 * @param settings
	 *            the settings
	 * @return the composite setting
	 */
	public static String createCompositeSetting(final String caption, final String[] settings) {
		Assert.isNotNull(caption);
		Assert.isNotNull(settings);
		final StringBuffer buffer= new StringBuffer(128);
		buffer.append(caption);
		for (int index= 0; index < settings.length; index++) {
			if (settings[index] != null && !"".equals(settings[index])) { //$NON-NLS-1$
				buffer.append(LINE_DELIMITER);
				buffer.append(ELEMENT_DELIMITER);
				buffer.append(settings[index]);
			} else {
				buffer.append(LINE_DELIMITER);
				buffer.append(ELEMENT_DELIMITER);
				buffer.append(RefactoringCoreMessages.ScriptRefactoringDescriptor_not_available);
			}
		}
		return buffer.toString();
	}

	/** The header of the comment */
	private final String fHeader;

	/** The settings list */
	private final List fSettings= new ArrayList(6);

	/**
	 * Creates a newscriptrefactoring descriptor comment.
	 * 
	 * @param object
	 *            the refactoring object to generate a comment for
	 * @param header
	 *            the header of the comment (typically the unique description of
	 *            the refactoring with fully qualified element names)
	 */
	public ScriptRefactoringDescriptorComment(final Object object, final String header) {
		Assert.isNotNull(object);
		Assert.isNotNull(header);
		fHeader= header;
		initializeInferredSettings(object);
	}

	/**
	 * Adds the specified setting to this comment.
	 * 
	 * @param index
	 *            the index
	 * @param setting
	 *            the setting to add
	 */
	public void addSetting(final int index, final String setting) {
		Assert.isTrue(index >= 0);
		Assert.isNotNull(setting);
		Assert.isTrue(!"".equals(setting)); //$NON-NLS-1$
		fSettings.add(index, setting);
	}

	/**
	 * Adds the specified setting to this comment.
	 * 
	 * @param setting
	 *            the setting to add
	 */
	public void addSetting(final String setting) {
		Assert.isNotNull(setting);
		Assert.isTrue(!"".equals(setting)); //$NON-NLS-1$
		fSettings.add(setting);
	}

	/**
	 * Returns this comment in a human-readable string representation.
	 * 
	 * @return this comment in string representation
	 */
	public String asString() {
		final StringBuffer buffer= new StringBuffer(256);
		buffer.append(fHeader);
		for (final Iterator iterator= fSettings.iterator(); iterator.hasNext();) {
			final String setting= (String) iterator.next();
			buffer.append(LINE_DELIMITER);
			buffer.append(Messages.format(RefactoringCoreMessages.ScriptRefactoringDescriptor_inferred_setting_pattern, setting));
		}
		return buffer.toString();
	}

	/**
	 * Returns the number of settings.
	 * 
	 * @return the number of settings
	 */
	public int getCount() {
		return fSettings.size();
	}

	/**
	 * Initializes the inferred settings.
	 * 
	 * @param object
	 *            the refactoring object
	 */
	private void initializeInferredSettings(final Object object) {
		if (object instanceof INameUpdating) {
			final INameUpdating updating= (INameUpdating) object;
			fSettings.add(Messages.format(RefactoringCoreMessages.ScriptRefactoringDescriptor_original_element_pattern, ScriptElementLabels.getDefault().getTextLabel(updating.getElements()[0], ScriptElementLabels.ALL_FULLY_QUALIFIED)));
			try {
				final Object element= updating.getNewElement();
				if (element != null)
					fSettings.add(Messages.format(RefactoringCoreMessages.ScriptRefactoringDescriptor_renamed_element_pattern, ScriptElementLabels.getDefault().getTextLabel(element, ScriptElementLabels.ALL_FULLY_QUALIFIED)));
				else {
					final String oldLabel= ScriptElementLabels.getDefault().getTextLabel(updating.getElements()[0], ScriptElementLabels.ALL_FULLY_QUALIFIED);
					final String newName= updating.getCurrentElementName();
					if (newName.length() < oldLabel.length()) {
						final String newLabel= oldLabel.substring(0, oldLabel.length() - newName.length());
						fSettings.add(Messages.format(RefactoringCoreMessages.ScriptRefactoringDescriptor_renamed_element_pattern, newLabel + updating.getNewElementName()));
					}
				}
			} catch (CoreException exception) {
				DLTKUIPlugin.log(exception);
			}
		} else if (object instanceof RefactoringProcessor) {
			final RefactoringProcessor processor= (RefactoringProcessor) object;
			final Object[] elements= processor.getElements();
			if (elements != null) {
				if (elements.length == 1 && elements[0] != null)
					fSettings.add(Messages.format(RefactoringCoreMessages.ScriptRefactoringDescriptor_original_element_pattern, ScriptElementLabels.getDefault().getTextLabel(elements[0], ScriptElementLabels.ALL_FULLY_QUALIFIED)));
				else if (elements.length > 1) {
					final StringBuffer buffer= new StringBuffer(128);
					buffer.append(RefactoringCoreMessages.ScriptRefactoringDescriptor_original_elements);
					for (int index= 0; index < elements.length; index++) {
						if (elements[index] != null) {
							buffer.append(LINE_DELIMITER);
							buffer.append(ELEMENT_DELIMITER);
							buffer.append(ScriptElementLabels.getDefault().getTextLabel(elements[index], ScriptElementLabels.ALL_FULLY_QUALIFIED));
						} else {
							buffer.append(LINE_DELIMITER);
							buffer.append(ELEMENT_DELIMITER);
							buffer.append(RefactoringCoreMessages.ScriptRefactoringDescriptor_not_available);
						}
					}
					fSettings.add(buffer.toString());
				}
			}
		}
		if (object instanceof IReferenceUpdating) {
			final IReferenceUpdating updating= (IReferenceUpdating) object;
			if (updating.canEnableUpdateReferences() && updating.getUpdateReferences())
				fSettings.add(RefactoringCoreMessages.ScriptRefactoringDescriptor_update_references);
		}
		if (object instanceof ISimilarDeclarationUpdating) {
			final ISimilarDeclarationUpdating updating= (ISimilarDeclarationUpdating) object;
			if (updating.canEnableSimilarDeclarationUpdating() && updating.getUpdateSimilarDeclarations()) {
				final int strategy= updating.getMatchStrategy();
				if (strategy == RenamingNameSuggestor.STRATEGY_EXACT)
					fSettings.add(RefactoringCoreMessages.ScriptRefactoringDescriptor_rename_similar);
				else if (strategy == RenamingNameSuggestor.STRATEGY_EMBEDDED)
					fSettings.add(RefactoringCoreMessages.ScriptRefactoringDescriptor_rename_similar_embedded);
				else if (strategy == RenamingNameSuggestor.STRATEGY_SUFFIX)
					fSettings.add(RefactoringCoreMessages.ScriptRefactoringDescriptor_rename_similar_suffix);
			}
		}
		if (object instanceof IQualifiedNameUpdating) {
			final IQualifiedNameUpdating updating= (IQualifiedNameUpdating) object;
			if (updating.canEnableQualifiedNameUpdating() && updating.getUpdateQualifiedNames()) {
				final String patterns= updating.getFilePatterns();
				if (patterns != null && !"".equals(patterns)) //$NON-NLS-1$
					fSettings.add(Messages.format(RefactoringCoreMessages.ScriptRefactoringDescriptor_qualified_names_pattern, patterns.trim()));
				else
					fSettings.add(RefactoringCoreMessages.ScriptRefactoringDescriptor_qualified_names);
			}
		}
		if (object instanceof ITextUpdating) {
			final ITextUpdating updating= (ITextUpdating) object;
			if (updating.canEnableTextUpdating())
				fSettings.add(RefactoringCoreMessages.ScriptRefactoringDescriptor_textual_occurrences);
		}
		if (object instanceof IDelegateUpdating) {
			final IDelegateUpdating updating= (IDelegateUpdating) object;
			if (updating.canEnableDelegateUpdating() && updating.getDelegateUpdating()) {
				if (updating.getDeprecateDelegates())
					fSettings.add(RefactoringCoreMessages.ScriptRefactoringDescriptor_keep_original_deprecated);
				else
					fSettings.add(RefactoringCoreMessages.ScriptRefactoringDescriptor_keep_original);
			}
		}
	}

	/**
	 * Removes the setting at the specified index.
	 * 
	 * @param index
	 *            the index
	 */
	public void removeSetting(final int index) {
		Assert.isTrue(index >= 0);
		fSettings.remove(index);
	}
}

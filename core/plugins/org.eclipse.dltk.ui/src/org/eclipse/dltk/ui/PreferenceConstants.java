/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui;

import org.eclipse.dltk.ui.text.completion.ProposalSorterRegistry;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.AbstractTextEditor;



public class PreferenceConstants {
	
	/**
	 * Preference key suffix for bold text style preference keys.
	 */
	public static final String EDITOR_BOLD_SUFFIX= "_bold"; //$NON-NLS-1$

	/**
	 * Preference key suffix for italic text style preference keys.
	 */
	public static final String EDITOR_ITALIC_SUFFIX= "_italic"; //$NON-NLS-1$
	
	/**
	 * Preference key suffix for strikethrough text style preference keys.
	 */
	public static final String EDITOR_STRIKETHROUGH_SUFFIX= "_strikethrough"; //$NON-NLS-1$
	
	/**
	 * Preference key suffix for underline text style preference keys.
	 */
	public static final String EDITOR_UNDERLINE_SUFFIX= "_underline"; //$NON-NLS-1$
	
	
	/**
	 * A named preference that controls whether the 'close strings' feature
	 *  is   enabled.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String EDITOR_CLOSE_STRINGS= "closeStrings"; //$NON-NLS-1$

	/**
	 * A named preference that controls whether the 'escape strings' feature is
	 * enabled.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String EDITOR_ESCAPE_STRINGS= "escapeStrings"; //$NON-NLS-1$

	/**
	 * A named preference that controls whether the 'close brackets' feature is
	 * enabled.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String EDITOR_CLOSE_BRACKETS= "closeBrackets"; //$NON-NLS-1$

	/**
	 * A named preference that controls whether the 'close braces' feature is
	 * enabled.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String EDITOR_CLOSE_BRACES= "closeBraces"; //$NON-NLS-1$

	/**
	 * A named preference that controls whether the 'smart paste' feature is
	 * enabled.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String EDITOR_SMART_PASTE= "smartPaste"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls whether the 'smart home-end' feature is
	 * enabled.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String EDITOR_SMART_HOME_END= AbstractTextEditor.PREFERENCE_NAVIGATION_SMART_HOME_END;

	/**
	 * A named preference that controls if temporary problems are evaluated and shown in the UI.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_EVALUTE_TEMPORARY_PROBLEMS= "handleTemporaryProblems"; //$NON-NLS-1$

	
	/**
	 * A named preference that controls whether the 'sub-word navigation' feature is
	 * enabled.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String EDITOR_SUB_WORD_NAVIGATION= "subWordNavigation"; //$NON-NLS-1$

	/**
	 * A named preference that controls the smart tab behavior.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * 
	 *
	 */
	public static final String EDITOR_SMART_TAB= "smart_tab"; //$NON-NLS-1$
	
	/**
	 * A named preference that holds the number of spaces used per tab in the editor.
	 * <p>
	 * Value is of type <code>Integer</code>: positive integer value specifying the number of
	 * spaces per tab.
	 * </p>
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants#EDITOR_TAB_WIDTH
	 */
	public final static String EDITOR_TAB_WIDTH= AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH;
	
	public final static String EDITOR_TAB_ALWAYS_INDENT= "tab_always_indent";
	
	/**
	 * A named preference that controls whether the outline view selection
	 * should stay in sync with with the element at the current cursor position.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE= "syncOutlineOnCursorMove"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls if correction indicators are shown in the UI.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_CORRECTION_INDICATION= "ScriptEditor.ShowTemporaryProblem"; //$NON-NLS-1$

	/**
	 * A named preference that controls whether new projects are generated using source and output folder.
	 * <p>
	 * Value is of type <code>Boolean</code>. if <code>true</code> new projects are created with a source and
	 * output folder. If <code>false</code> source and output folder equals to the project.
	 * </p>
	 */
	public static final String SRCBIN_FOLDERS_IN_NEWPROJ= "com.xore.dltk.ui.wizards.srcBinFoldersInNewProjects"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls whether the hierarchy view's selection is linked to the active editor.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public static final String LINK_TYPEHIERARCHY_TO_EDITOR= "org.eclipse.dltk.ui.packages.linktypehierarchytoeditor"; //$NON-NLS-1$

	/**
	 * A named preference that specifies the source folder name used when creating a new script project. Value is inactive
	 * if <code>SRCBIN_FOLDERS_IN_NEWPROJ</code> is set to <code>false</code>.
	 * <p>
	 * Value is of type <code>String</code>. 
	 * </p>
	 * 
	 * @see #SRCBIN_FOLDERS_IN_NEWPROJ
	 */
	public static final String SRC_SRCNAME= "com.xore.dltk.ui.wizards.srcFoldersSrcName"; //$NON-NLS-1$	

	public final static String EDITOR_SMART_INDENT = "editorSmartIndent";
	
	public static final String APPEARANCE_FOLD_PACKAGES_IN_PACKAGE_EXPLORER= "org.eclipse.dltk.ui.flatPackagesInPackageExplorer";//$NON-NLS-1$
	
	public static final String SHOW_SOURCE_MODULE_CHILDREN= "org.eclipse.dltk.ui.packages.cuchildren"; //$NON-NLS-1$
	
	public static final String APPEARANCE_METHOD_RETURNTYPE= "org.eclipse.dltk.ui.methodreturntype";//$NON-NLS-1$
	public static final String APPEARANCE_METHOD_TYPEPARAMETERS= "org.eclipse.dltk.ui.methodtypeparametesr";//$NON-NLS-1$
	public static final String APPEARANCE_COMPRESS_PACKAGE_NAMES= "org.eclipse.dltk.ui.compresspackagenames";//$NON-NLS-1$
	public static final String APPEARANCE_PKG_NAME_PATTERN_FOR_PKG_VIEW= "org.eclipse.dltk.ui.PackagesView.pkgNamePatternForPackagesView";//$NON-NLS-1$
	public static final String LINK_PACKAGES_TO_EDITOR= "org.eclipse.dltk.ui.packages.linktoeditor"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls if segmented view (show selected element only) is turned on or off.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public static final String EDITOR_SHOW_SEGMENTS= "com.xore.dltk.ui.editor.showSegments"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls whether folding is enabled in the Script editor.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public static final String EDITOR_FOLDING_ENABLED= "editor_folding_enabled"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls whether comments folding is enabled in the script editor.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public static final String EDITOR_COMMENTS_FOLDING_ENABLED= "editor_comments_folding_enabled"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls minimal number of lines in block to be folded.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 * 
	 */
	public static final String EDITOR_FOLDING_LINES_LIMIT= "editor_folding_lines_limit"; //$NON-NLS-1$
	
	/**
	 * A named preference that stores the content assist LRU history
	 * <p>
	 * Value is an XML encoded version of the history.
	 * </p>
	 * 
	 */
	public static final String CODEASSIST_LRU_HISTORY= "content_assist_lru_history"; //$NON-NLS-1$

	
	/**
	 * A named preference that defines whether the hint to make hover sticky should be shown.
	 *
	 * @see JavaUI
	 *
	 */
	public static final String EDITOR_SHOW_TEXT_HOVER_AFFORDANCE= "PreferenceConstants.EDITOR_SHOW_TEXT_HOVER_AFFORDANCE"; //$NON-NLS-1$

	/**
	 * A named preference that defines the key for the hover modifiers.
	 *
	 * @see JavaUI
	 *
	 */
	public static final String EDITOR_TEXT_HOVER_MODIFIERS= "hoverModifiers"; //$NON-NLS-1$

	/**
	 * A named preference that defines the key for the hover modifier state masks.
	 * The value is only used if the value of <code>EDITOR_TEXT_HOVER_MODIFIERS</code>
	 * cannot be resolved to valid SWT modifier bits.
	 * 
	 * @see JavaUI
	 * @see #EDITOR_TEXT_HOVER_MODIFIERS
	 *
	 */
	public static final String EDITOR_TEXT_HOVER_MODIFIER_MASKS= "hoverModifierMasks"; //$NON-NLS-1$

	/**
	 * The id of the best match hover contributed for extension point
	 * <code>javaEditorTextHovers</code>.
	 *
	 *
	 */
	public static final String ID_BESTMATCH_HOVER= "org.eclipse.dltk.ui.BestMatchHover"; //$NON-NLS-1$
	
	/**
	 * A named preference that stores the content assist sorter id.
	 * <p>
	 * Value is a {@link String}.
	 * </p>
	 * 
	 * @see ProposalSorterRegistry
	 *
	 */
	public static final String CODEASSIST_SORTER= "content_assist_sorter"; //$NON-NLS-1$
	
	public static void initializeDefaultValues(IPreferenceStore store) {
		store.setDefault(PreferenceConstants.SHOW_SOURCE_MODULE_CHILDREN, true);
		store.setDefault(PreferenceConstants.REFACTOR_SAVE_ALL_EDITORS, false);
		
		store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION, true);
		store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY, 200);

		store.setDefault(PreferenceConstants.CODEASSIST_AUTOINSERT, true);
		PreferenceConverter.setDefault(store, PreferenceConstants.CODEASSIST_PROPOSALS_BACKGROUND, new RGB(255, 255, 255));
		PreferenceConverter.setDefault(store, PreferenceConstants.CODEASSIST_PROPOSALS_FOREGROUND, new RGB(0, 0, 0));
		PreferenceConverter.setDefault(store, PreferenceConstants.CODEASSIST_PARAMETERS_BACKGROUND, new RGB(255, 255, 255));
		PreferenceConverter.setDefault(store, PreferenceConstants.CODEASSIST_PARAMETERS_FOREGROUND, new RGB(0, 0, 0));
		PreferenceConverter.setDefault(store, PreferenceConstants.CODEASSIST_REPLACEMENT_BACKGROUND, new RGB(255, 255, 0));
		PreferenceConverter.setDefault(store, PreferenceConstants.CODEASSIST_REPLACEMENT_FOREGROUND, new RGB(255, 0, 0));
		store.setDefault(PreferenceConstants.CODEASSIST_SHOW_VISIBLE_PROPOSALS, true);
		store.setDefault(PreferenceConstants.CODEASSIST_CASE_SENSITIVITY, false);
		store.setDefault(PreferenceConstants.CODEASSIST_ADDIMPORT, true);
		store.setDefault(PreferenceConstants.CODEASSIST_INSERT_COMPLETION, true);
		store.setDefault(PreferenceConstants.CODEASSIST_FILL_ARGUMENT_NAMES, true);
		store.setDefault(PreferenceConstants.CODEASSIST_GUESS_METHOD_ARGUMENTS, false);
		store.setDefault(PreferenceConstants.CODEASSIST_PREFIX_COMPLETION, false);
		store.setDefault(PreferenceConstants.CODEASSIST_CATEGORY_ORDER, "org.eclipse.dltk.ui.spellingProposalCategory:65545\0org.eclipse.dltk.ui.scriptTypeProposalCategory:65540\0org.eclipse.dltk.ui.scriptNoTypeProposalCategory:65539\0org.eclipse.dltk.ui.textProposalCategory:65541\0org.eclipse.dltk.ui.templateProposalCategory:2\0"); //$NON-NLS-1$
		store.setDefault(PreferenceConstants.CODEASSIST_LRU_HISTORY, ""); //$NON-NLS-1$
		store.setDefault(PreferenceConstants.CODEASSIST_SORTER, "org.eclipse.dltk.ui.RelevanceSorter"); //$NON-NLS-1$
		
		store.setDefault(PreferenceConstants.EDITOR_TEXT_HOVER_MODIFIERS, "org.eclipse.dltk.ui.BestMatchHover;0"); //$NON-NLS-1$
		store.setDefault(PreferenceConstants.EDITOR_TEXT_HOVER_MODIFIER_MASKS, "org.eclipse.dltk.ui.BestMatchHover;0"); //$NON-NLS-1$
		store.setDefault(PreferenceConstants.EDITOR_SHOW_TEXT_HOVER_AFFORDANCE, true);
		
		store.setDefault(PreferenceConstants.EDITOR_MATCHING_BRACKETS, true);
		store.setDefault(PreferenceConstants.EDITOR_TAB_ALWAYS_INDENT, false);
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR, new RGB(192, 192,192));
		
	}
	/**
	 * A named preference that controls whether all dirty editors are automatically saved before a refactoring is
	 * executed.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public static final String REFACTOR_SAVE_ALL_EDITORS= "Refactoring.savealleditors"; //$NON-NLS-1$
	
	/**
	 * A named preference that defines how member elements are ordered by the
	 * Script views using the <code>ScriptElementSorter</code>.
	 * <p>
	 * Value is of type <code>String</code>: A comma separated list of the
	 * following entries. Each entry must be in the list, no duplication. List
	 * order defines the sort order.
	 * <ul>
	 * <li><b>T</b>: Types</li>
	 * <li><b>C</b>: Constructors</li>
	 * <li><b>M</b>: Methods</li>
	 * <li><b>F</b>: Fields</li>
	 * </ul>
	 * </p>
	 *
	 */
	public static final String APPEARANCE_MEMBER_SORT_ORDER= "outlinesortoption"; //$NON-NLS-1$

	//Code Assist parameters
	/**
	 * A named preference that controls if the Script code assist gets auto activated.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String CODEASSIST_AUTOACTIVATION= "content_assist_autoactivation"; //$NON-NLS-1$

	/**
	 * A name preference that holds the auto activation delay time in milliseconds.
	 * <p>
	 * Value is of type <code>Integer</code>.
	 * </p>
	 */
	public final static String CODEASSIST_AUTOACTIVATION_DELAY= "content_assist_autoactivation_delay"; //$NON-NLS-1$

	/**
	 * A named preference that controls if code assist contains only visible proposals.
	 * <p>
	 * Value is of type <code>Boolean</code>. if <code>true<code> code assist only contains visible members. If 
	 * <code>false</code> all members are included.
	 * </p>
	 */
	public final static String CODEASSIST_SHOW_VISIBLE_PROPOSALS= "content_assist_show_visible_proposals"; //$NON-NLS-1$

	/**
	 * A named preference that controls if the Script code assist inserts a
	 * proposal automatically if only one proposal is available.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String CODEASSIST_AUTOINSERT= "content_assist_autoinsert"; //$NON-NLS-1$

	/**
	 * A named preference that controls if the Script code assist adds import
	 * statements.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String CODEASSIST_ADDIMPORT= "content_assist_add_import"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls if the Script code assist only inserts
	 * completions. If set to false the proposals can also _replace_ code.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String CODEASSIST_INSERT_COMPLETION= "content_assist_insert_completion"; //$NON-NLS-1$	

	/**
	 * A named preference that controls whether code assist proposals filtering is case sensitive or not.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String CODEASSIST_CASE_SENSITIVITY= "content_assist_case_sensitivity"; //$NON-NLS-1$
	

	/**
	 * A named preference that controls if argument names are filled in when a method is selected from as list
	 * of code assist proposal.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String CODEASSIST_FILL_ARGUMENT_NAMES= "content_assist_fill_method_arguments"; //$NON-NLS-1$

	/**
	 * A named preference that controls if method arguments are guessed when a
	 * method is selected from as list of code assist proposal.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 */
	public final static String CODEASSIST_GUESS_METHOD_ARGUMENTS= "content_assist_guess_method_arguments"; //$NON-NLS-1$
	
	public final static String CODEASSIST_AUTOACTIVATION_TRIGGERS= "content_assist_autoactivation_triggers_script"; //$NON-NLS-1$

	/**
	 * A named preference that holds the background color used in the code assist selection dialog.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String CODEASSIST_PROPOSALS_BACKGROUND= "content_assist_proposals_background"; //$NON-NLS-1$

	/**
	 * A named preference that holds the foreground color used in the code assist selection dialog.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String CODEASSIST_PROPOSALS_FOREGROUND= "content_assist_proposals_foreground"; //$NON-NLS-1$
	
	/**
	 * A named preference that holds the background color used for parameter hints.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String CODEASSIST_PARAMETERS_BACKGROUND= "content_assist_parameters_background"; //$NON-NLS-1$

	/**
	 * A named preference that holds the foreground color used in the code assist selection dialog.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String CODEASSIST_PARAMETERS_FOREGROUND= "content_assist_parameters_foreground"; //$NON-NLS-1$

	/**
	 * A named preference that holds the background color used in the code
	 * assist selection dialog to mark replaced code.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 *
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 *
	 */
	public final static String CODEASSIST_REPLACEMENT_BACKGROUND= "content_assist_completion_replacement_background"; //$NON-NLS-1$

	/**
	 * A named preference that holds the foreground color used in the code
	 * assist selection dialog to mark replaced code.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 *
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 *
	 */
	public final static String CODEASSIST_REPLACEMENT_FOREGROUND= "content_assist_completion_replacement_foreground"; //$NON-NLS-1$
	
	
	/**
	 * A named preference that controls whether annotation roll over is used or not.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> the annotation ruler column
	 * uses a roll over to display multiple annotations
	 * </p>
	 * 
	 */
	public static final String EDITOR_ANNOTATION_ROLL_OVER= "editor_annotation_roll_over"; //$NON-NLS-1$
	
	/**
	 * A named preference that controls if content assist inserts the common
	 * prefix of all proposals before presenting choices.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 * 
	 *
	 */
	public final static String CODEASSIST_PREFIX_COMPLETION= "content_assist_prefix_completion"; //$NON-NLS-1$

	/**
	 * A named preference that controls which completion proposal categories
	 * have been excluded from the default proposal list.
	 * <p>
	 * Value is of type <code>String</code>, a "\0"-separated list of identifiers.
	 * </p>
	 * 
	 *
	 */
	public static final String CODEASSIST_EXCLUDED_CATEGORIES= "content_assist_disabled_computers"; //$NON-NLS-1$

	/**
	 * A named preference that controls which the order of the specific code assist commands.
	 * <p>
	 * Value is of type <code>String</code>, a "\0"-separated list of identifiers.
	 * </p>
	 * 
	 *
	 */
	public static final String CODEASSIST_CATEGORY_ORDER= "content_assist_category_order"; //$NON-NLS-1$

	public static final String SEARCH_USE_REDUCED_MENU= "Search.usereducemenu"; //$NON-NLS-1$

	public static final String EDITOR_MATCHING_BRACKETS = "editor.matching_brackets";

	public static final String EDITOR_MATCHING_BRACKETS_COLOR = "editor.matching_brackets_color";

	//Notification messages
	public static final String NOTIFICATION_NOT_ON_BUILDPATH_MESSAGE = DLTKUIPlugin.PLUGIN_ID + ".notification.not_on_buildpath";
	
	/**
	 * A named preference that holds a list of semicolon separated fully qualified type names with wild card characters.
	 */	
	public static final String TYPEFILTER_ENABLED= "org.eclipse.dltk.ui.typefilter.enabled"; //$NON-NLS-1$

	public static final String LINK_BROWSING_MEMBERS_TO_EDITOR = "org.eclipse.dltk.ui.browsing.member.to.editor";
	public static final String LINK_BROWSING_PROJECTS_TO_EDITOR = "org.eclipse.dltk.ui.browsing.projects.to.editor";
	public static final String LINK_BROWSING_PACKAGES_TO_EDITOR = "org.eclipse.dltk.ui.browsing.packages.to.editor";
	public static final String LINK_BROWSING_TYPES_TO_EDITOR = "org.eclipse.dltk.ui.browsing.types.to.editor";
}

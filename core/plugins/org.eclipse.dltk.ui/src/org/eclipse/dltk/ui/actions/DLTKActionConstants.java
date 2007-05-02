/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.actions;

/**
 * Action ids for standard actions, for groups in the menu bar, and
 * for actions in context menus of DLTK views.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 */
public class DLTKActionConstants {

	// Navigate menu
	
	public static final String OPEN= "org.eclipse.dltk.ui.actions.Open"; //$NON-NLS-1$


	public static final String OPEN_CALL_HIERARCHY= "org.eclipse.dltk.ui.actions.OpenCallHierarchy"; //$NON-NLS-1$
	/**
	 * Navigate menu: name of standard Show in Navigator View global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ShowInNaviagtorView"</code>).
	 */
	public static final String SHOW_IN_NAVIGATOR_VIEW= "org.eclipse.dltk.ui.actions.ShowInNaviagtorView"; //$NON-NLS-1$

	// Edit menu


	/**
	 * Edit menu: name of standard Code Assist global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ContentAssist"</code>).
	 */
	public static final String CONTENT_ASSIST= "org.eclipse.dltk.ui.actions.ContentAssist"; //$NON-NLS-1$
	
	/**
	 * Edit menu: name of standard Show Documentaion global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ShowDocumentaion"</code>).
	 */
	public static final String SHOW_DOCUMENTAION= "org.eclipse.dltk.ui.actions.ShowDocumentaion"; //$NON-NLS-1$

	// Source menu	
	
	/**
	 * Source menu: name of standard Comment global action
	 * (value <code>"org.eclipse.dltk.ui.actions.Comment"</code>).
	 */
	public static final String COMMENT= "org.eclipse.dltk.ui.actions.Comment"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Uncomment global action
	 * (value <code>"org.eclipse.dltk.ui.actions.Uncomment"</code>).
	 */
	public static final String UNCOMMENT= "org.eclipse.dltk.ui.actions.Uncomment"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard ToggleComment global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ToggleComment"</code>).
	 *
	 */
	public static final String TOGGLE_COMMENT= "org.eclipse.dltk.ui.actions.ToggleComment"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Block Comment global action
	 * (value <code>"org.eclipse.dltk.ui.actions.AddBlockComment"</code>).
	 * 
	 *
	 */
	public static final String ADD_BLOCK_COMMENT= "org.eclipse.dltk.ui.actions.AddBlockComment"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Block Uncomment global action
	 * (value <code>"org.eclipse.dltk.ui.actions.RemoveBlockComment"</code>).
	 * 
	 *
	 */
	public static final String REMOVE_BLOCK_COMMENT= "org.eclipse.dltk.ui.actions.RemoveBlockComment"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Indent global action
	 * (value <code>"org.eclipse.dltk.ui.actions.Indent"</code>).
	 * 
	 *
	 */
	public static final String INDENT= "org.eclipse.dltk.ui.actions.Indent"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Shift Right action
	 * (value <code>"org.eclipse.dltk.ui.actions.ShiftRight"</code>).
	 */
	public static final String SHIFT_RIGHT= "org.eclipse.dltk.ui.actions.ShiftRight"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Shift Left global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ShiftLeft"</code>).
	 */
	public static final String SHIFT_LEFT= "org.eclipse.dltk.ui.actions.ShiftLeft"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Format global action
	 * (value <code>"org.eclipse.dltk.ui.actions.Format"</code>).
	 */
	public static final String FORMAT= "org.eclipse.dltk.ui.actions.Format"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Format Element global action
	 * (value <code>"org.eclipse.dltk.ui.actions.FormatElement"</code>).
	 *
	 */
	public static final String FORMAT_ELEMENT= "org.eclipse.dltk.ui.actions.FormatElement"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Add Import global action
	 * (value <code>"org.eclipse.dltk.ui.actions.AddImport"</code>).
	 */
	public static final String ADD_IMPORT= "org.eclipse.dltk.ui.actions.AddImport"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Organize Imports global action
	 * (value <code>"org.eclipse.dltk.ui.actions.OrganizeImports"</code>).
	 */
	public static final String ORGANIZE_IMPORTS= "org.eclipse.dltk.ui.actions.OrganizeImports"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Sort Members global action (value
	 * <code>"org.eclipse.dltk.ui.actions.SortMembers"</code>).
	 *
	 */
	public static final String SORT_MEMBERS= "org.eclipse.dltk.ui.actions.SortMembers"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Surround with try/catch block global action
	 * (value <code>"org.eclipse.dltk.ui.actions.SurroundWithTryCatch"</code>).
	 */
	public static final String SURROUND_WITH_TRY_CATCH= "org.eclipse.dltk.ui.actions.SurroundWithTryCatch"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Override Methods global action
	 * (value <code>"org.eclipse.dltk.ui.actions.OverrideMethods"</code>).
	 */
	public static final String OVERRIDE_METHODS= "org.eclipse.dltk.ui.actions.OverrideMethods"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Generate Getter and Setter global action
	 * (value <code>"org.eclipse.dltk.ui.actions.GenerateGetterSetter"</code>).
	 */
	public static final String GENERATE_GETTER_SETTER= "org.eclipse.dltk.ui.actions.GenerateGetterSetter"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard delegate methods global action (value
	 * <code>"org.eclipse.dltk.ui.actions.GenerateDelegateMethods"</code>).
	 *
	 */
	public static final String GENERATE_DELEGATE_METHODS= "org.eclipse.dltk.ui.actions.GenerateDelegateMethods"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Add Constructor From Superclass global action
	 * (value <code>"org.eclipse.dltk.ui.actions.AddConstructorFromSuperclass"</code>).
	 */
	public static final String ADD_CONSTRUCTOR_FROM_SUPERCLASS= "org.eclipse.dltk.ui.actions.AddConstructorFromSuperclass"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Generate Constructor using Fields global action
	 * (value <code>"org.eclipse.dltk.ui.actions.GenerateConstructorUsingFields"</code>).
	 */
	public static final String GENERATE_CONSTRUCTOR_USING_FIELDS= "org.eclipse.dltk.ui.actions.GenerateConstructorUsingFields"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Generate hashCode() and equals() global action
	 * (value <code>"org.eclipse.dltk.ui.actions.GenerateHashCodeEquals"</code>).
	 *
	 */
	public static final String GENERATE_HASHCODE_EQUALS= "org.eclipse.dltk.ui.actions.GenerateHashCodeEquals"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Add Javadoc Comment global action
	 * (value <code>"org.eclipse.dltk.ui.actions.AddJavadocComment"</code>).
	 */
	public static final String ADD_JAVA_DOC_COMMENT= "org.eclipse.dltk.ui.actions.AddJavadocComment"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Find Strings to Externalize global action
	 * (value <code>"org.eclipse.dltk.ui.actions.FindStringsToExternalize"</code>).
	 */
	public static final String FIND_STRINGS_TO_EXTERNALIZE= "org.eclipse.dltk.ui.actions.FindStringsToExternalize"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Externalize Strings global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ExternalizeStrings"</code>).
	 */
	public static final String EXTERNALIZE_STRINGS= "org.eclipse.dltk.ui.actions.ExternalizeStrings"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Convert Line Delimiters To Windows global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ConvertLineDelimitersToWindows"</code>).
	 */
	public static final String CONVERT_LINE_DELIMITERS_TO_WINDOWS= "org.eclipse.dltk.ui.actions.ConvertLineDelimitersToWindows"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Convert Line Delimiters To UNIX global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ConvertLineDelimitersToUNIX"</code>).
	 */
	public static final String CONVERT_LINE_DELIMITERS_TO_UNIX= "org.eclipse.dltk.ui.actions.ConvertLineDelimitersToUNIX"; //$NON-NLS-1$

	/**
	 * Source menu: name of standardConvert Line Delimiters To Mac global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ConvertLineDelimitersToMac"</code>).
	 */
	public static final String CONVERT_LINE_DELIMITERS_TO_MAC= "org.eclipse.dltk.ui.actions.ConvertLineDelimitersToMac"; //$NON-NLS-1$
	
	/**
	 * Source menu: name of standard Clean up global action 
	 * (value <code>"org.eclipse.dltk.ui.actions.CleanUp"</code>).
	 * 
	 *
	 */
	public static final String CLEAN_UP= "org.eclipse.dltk.ui.actions.CleanUp"; //$NON-NLS-1$

	// Refactor menu
	
	/**
	 * Refactor menu: name of standard Self Encapsulate Field global action
	 * (value <code>"org.eclipse.dltk.ui.actions.SelfEncapsulateField"</code>).
	 */
	public static final String SELF_ENCAPSULATE_FIELD= "org.eclipse.dltk.ui.actions.SelfEncapsulateField"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Modify Parameters global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ModifyParameters"</code>).
	 */
	public static final String MODIFY_PARAMETERS= "org.eclipse.dltk.ui.actions.ModifyParameters"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Pull Up global action
	 * (value <code>"org.eclipse.dltk.ui.actions.PullUp"</code>).
	 */
	public static final String PULL_UP= "org.eclipse.dltk.ui.actions.PullUp"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Push Down global action
	 * (value <code>"org.eclipse.dltk.ui.actions.PushDown"</code>).
	 * 
	 *
	 */
	public static final String PUSH_DOWN= "org.eclipse.dltk.ui.actions.PushDown"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Move Element global action
	 * (value <code>"org.eclipse.dltk.ui.actions.Move"</code>).
	 */
	public static final String MOVE= "org.eclipse.dltk.ui.actions.Move"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Rename Element global action
	 * (value <code>"org.eclipse.dltk.ui.actions.Rename"</code>).
	 */
	public static final String RENAME= "org.eclipse.dltk.ui.actions.Rename"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Inline Temp global action
	 * (value <code>"org.eclipse.dltk.ui.actions.InlineTemp"</code>).
	 * @deprecated Use INLINE
	 */
	public static final String INLINE_TEMP= "org.eclipse.dltk.ui.actions.InlineTemp"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Extract Temp global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ExtractTemp"</code>).
	 */
	public static final String EXTRACT_TEMP= "org.eclipse.dltk.ui.actions.ExtractTemp"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Extract Constant global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ExtractConstant"</code>).
	 * 
	 *
	 */
	public static final String EXTRACT_CONSTANT= "org.eclipse.dltk.ui.actions.ExtractConstant"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Introduce Parameter global action
	 * (value <code>"org.eclipse.dltk.ui.actions.IntroduceParameter"</code>).
	 * 
	 *
	 */
	public static final String INTRODUCE_PARAMETER= "org.eclipse.dltk.ui.actions.IntroduceParameter"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Introduce Factory global action
	 * (value <code>"org.eclipse.dltk.ui.actions.IntroduceFactory"</code>).
	 * 
	 *
	 */
	public static final String INTRODUCE_FACTORY= "org.eclipse.dltk.ui.actions.IntroduceFactory"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Extract Method global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ExtractMethod"</code>).
	 */
	public static final String EXTRACT_METHOD= "org.eclipse.dltk.ui.actions.ExtractMethod"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Replace Invocations global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReplaceInvocations"</code>).
	 * 
	 *
	 */
	public static final String REPLACE_INVOCATIONS="org.eclipse.dltk.ui.actions.ReplaceInvocations"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Introduce Indirection global action
	 * (value <code>"org.eclipse.dltk.ui.actions.IntroduceIndirection"</code>).
	 * 
	 *
	 */
	public static final String INTRODUCE_INDIRECTION= "org.eclipse.dltk.ui.actions.IntroduceIndirection"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Inline global action 
	 * (value <code>"org.eclipse.dltk.ui.actions.Inline"</code>).
	 *
	 *
	 */
	public static final String INLINE= "org.eclipse.dltk.ui.actions.Inline"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Extract Interface global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ExtractInterface"</code>).
	 * 
	 *
	 */
	public static final String EXTRACT_INTERFACE= "org.eclipse.dltk.ui.actions.ExtractInterface"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Generalize Declared Type global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ChangeType"</code>).
	 * 
	 *
	 */
	public static final String CHANGE_TYPE= "org.eclipse.dltk.ui.actions.ChangeType"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard global action to convert a nested type to a top level type
	 * (value <code>"org.eclipse.dltk.ui.actions.MoveInnerToTop"</code>).
	 * 
	 *
	 */
	public static final String CONVERT_NESTED_TO_TOP= "org.eclipse.dltk.ui.actions.ConvertNestedToTop"; //$NON-NLS-1$
	
	/**
	 * Refactor menu: name of standard Use Supertype global action
	 * (value <code>"org.eclipse.dltk.ui.actions.UseSupertype"</code>).
	 * 
	 *
	 */
	public static final String USE_SUPERTYPE= "org.eclipse.dltk.ui.actions.UseSupertype"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Infer Generic Type Arguments global action
	 * (value <code>"org.eclipse.dltk.ui.actions.InferTypeArguments"</code>).
	 * 
	 *
	 */
	public static final String INFER_TYPE_ARGUMENTS= "org.eclipse.dltk.ui.actions.InferTypeArguments"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard global action to convert a local
	 * variable to a field (value <code>"org.eclipse.dltk.ui.actions.ConvertLocalToField"</code>).
	 * 
	 *
	 */
	public static final String CONVERT_LOCAL_TO_FIELD= "org.eclipse.dltk.ui.actions.ConvertLocalToField"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Covert Anonymous to Nested global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ConvertAnonymousToNested"</code>).
	 * 
	 *
	 */
	public static final String CONVERT_ANONYMOUS_TO_NESTED= "org.eclipse.dltk.ui.actions.ConvertAnonymousToNested"; //$NON-NLS-1$
	
	// Search Menu
	
	/**
	 * Search menu: name of standard Find References in Workspace global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReferencesInWorkspace"</code>).
	 */
	public static final String FIND_REFERENCES_IN_WORKSPACE= "org.eclipse.dltk.ui.actions.ReferencesInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find References in Project global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReferencesInProject"</code>).
	 */
	public static final String FIND_REFERENCES_IN_PROJECT= "org.eclipse.dltk.ui.actions.ReferencesInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find References in Hierarchy global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReferencesInHierarchy"</code>).
	 */
	public static final String FIND_REFERENCES_IN_HIERARCHY= "org.eclipse.dltk.ui.actions.ReferencesInHierarchy"; //$NON-NLS-1$
	
	/**
	 * Search menu: name of standard Find References in Working Set global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReferencesInWorkingSet"</code>).
	 */
	public static final String FIND_REFERENCES_IN_WORKING_SET= "org.eclipse.dltk.ui.actions.ReferencesInWorkingSet"; //$NON-NLS-1$



	/**
	 * Search menu: name of standard Find Declarations in Workspace global action
	 * (value <code>"org.eclipse.dltk.ui.actions.DeclarationsInWorkspace"</code>).
	 */
	public static final String FIND_DECLARATIONS_IN_WORKSPACE= "org.eclipse.dltk.ui.actions.DeclarationsInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Declarations in Project global action
	 * (value <code>"org.eclipse.dltk.ui.actions.DeclarationsInProject"</code>).
	 */
	public static final String FIND_DECLARATIONS_IN_PROJECT= "org.eclipse.dltk.ui.actions.DeclarationsInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Declarations in Hierarchy global action
	 * (value <code>"org.eclipse.dltk.ui.actions.DeclarationsInHierarchy"</code>).
	 */
	public static final String FIND_DECLARATIONS_IN_HIERARCHY= "org.eclipse.dltk.ui.actions.DeclarationsInHierarchy"; //$NON-NLS-1$
	
	/**
	 * Search menu: name of standard Find Declarations in Working Set global action
	 * (value <code>"org.eclipse.dltk.ui.actions.DeclarationsInWorkingSet"</code>).
	 */
	public static final String FIND_DECLARATIONS_IN_WORKING_SET= "org.eclipse.dltk.ui.actions.DeclarationsInWorkingSet"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Implementors in Workspace global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ImplementorsInWorkspace"</code>).
	 */
	public static final String FIND_IMPLEMENTORS_IN_WORKSPACE= "org.eclipse.dltk.ui.actions.ImplementorsInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Implementors in Project global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ImplementorsInProject"</code>).
	 */
	public static final String FIND_IMPLEMENTORS_IN_PROJECT= "org.eclipse.dltk.ui.actions.ImplementorsInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Implementors in Working Set global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ImplementorsInWorkingSet"</code>).
	 */
	public static final String FIND_IMPLEMENTORS_IN_WORKING_SET= "org.eclipse.dltk.ui.actions.ImplementorsInWorkingSet"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Workspace global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReadAccessInWorkspace"</code>).
	 */
	public static final String FIND_READ_ACCESS_IN_WORKSPACE= "org.eclipse.dltk.ui.actions.ReadAccessInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Project global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReadAccessInProject"</code>).
	 */
	public static final String FIND_READ_ACCESS_IN_PROJECT= "org.eclipse.dltk.ui.actions.ReadAccessInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Hierarchy global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReadAccessInHierarchy"</code>).
	 */
	public static final String FIND_READ_ACCESS_IN_HIERARCHY= "org.eclipse.dltk.ui.actions.ReadAccessInHierarchy"; //$NON-NLS-1$
	
	/**
	 * Search menu: name of standard Find Read Access in Working Set global action
	 * (value <code>"org.eclipse.dltk.ui.actions.ReadAccessInWorkingSet"</code>).
	 */
	public static final String FIND_READ_ACCESS_IN_WORKING_SET= "org.eclipse.dltk.ui.actions.ReadAccessInWorkingSet"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Write Access in Workspace global action
	 * (value <code>"org.eclipse.dltk.ui.actions.WriteAccessInWorkspace"</code>).
	 */
	public static final String FIND_WRITE_ACCESS_IN_WORKSPACE= "org.eclipse.dltk.ui.actions.WriteAccessInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Write Access in Project global action
	 * (value <code>"org.eclipse.dltk.ui.actions.WriteAccessInProject"</code>).
	 */
	public static final String FIND_WRITE_ACCESS_IN_PROJECT= "org.eclipse.dltk.ui.actions.WriteAccessInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Hierarchy global action
	 * (value <code>"org.eclipse.dltk.ui.actions.WriteAccessInHierarchy"</code>).
	 */
	public static final String FIND_WRITE_ACCESS_IN_HIERARCHY= "org.eclipse.dltk.ui.actions.WriteAccessInHierarchy"; //$NON-NLS-1$
	
	/**
	 * Search menu: name of standard Find Read Access in Working Set global action
	 * (value <code>"org.eclipse.dltk.ui.actions.WriteAccessInWorkingSet"</code>).
	 */
	public static final String FIND_WRITE_ACCESS_IN_WORKING_SET= "org.eclipse.dltk.ui.actions.WriteAccessInWorkingSet"; //$NON-NLS-1$
	
	/**
	 * Search menu: name of standard Occurrences in File global action (value
	 * <code>"org.eclipse.dltk.ui.actions.OccurrencesInFile"</code>).
	 * 
	 *
	 */
	public static final String FIND_OCCURRENCES_IN_FILE= "org.eclipse.dltk.ui.actions.OccurrencesInFile"; //$NON-NLS-1$
	
	/**
	 * Search menu: name of standard Find exception occurrences global action (value
	 * <code>"org.eclipse.dltk.ui.actions.ExceptionOccurrences"</code>).
	 * 
	 *
	 */
	public static final String FIND_EXCEPTION_OCCURRENCES= "org.eclipse.dltk.ui.actions.ExceptionOccurrences"; //$NON-NLS-1$
	
	/**
	 * Search menu: name of standard Find implement occurrences global action (value
	 * <code>"org.eclipse.dltk.ui.actions.ImplementOccurrences"</code>).
	 * 
	 *
	 */
	public static final String FIND_IMPLEMENT_OCCURRENCES= "org.eclipse.dltk.ui.actions.ImplementOccurrences"; //$NON-NLS-1$		


}

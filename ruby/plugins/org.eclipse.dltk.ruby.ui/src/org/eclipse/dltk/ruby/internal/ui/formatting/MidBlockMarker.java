package org.eclipse.dltk.ruby.internal.ui.formatting;


public class MidBlockMarker extends AbstractBlockMarker {

	public MidBlockMarker(String aKeyword, int aLine) {
		super(aKeyword, aLine);
	}

	protected void indentAfterPrint(IndentationState state) {
		state.incIndentationLevel() ;
	}


	protected void indentBeforePrint(IndentationState state) {
		state.decIndentationLevel() ;
	}

}

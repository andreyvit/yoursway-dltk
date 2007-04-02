package org.eclipse.dltk.javascript.internal.ui.formatting;

import java.util.Map;

import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;

import com.xored.org.mozilla.javascript.CompilerEnvirons;
import com.xored.org.mozilla.javascript.Decompiler;
import com.xored.org.mozilla.javascript.ErrorReporter;
import com.xored.org.mozilla.javascript.EvaluatorException;
import com.xored.org.mozilla.javascript.Parser;
import com.xored.org.mozilla.javascript.UintMap;

public class OldCodeFormatter extends CodeFormatter {

	Map options;

	public OldCodeFormatter(Map options) {
		this.options = options;
	}

	public TextEdit format(int kind, String source, int offset, int length,
			StringBuffer computeIndentation, String lineSeparator) {
		String newText = formatString(source.substring(offset, offset+length),
				computeIndentation);
		return new ReplaceEdit(offset, length, newText);
	}

	public String formatString(String substring, StringBuffer computeIndentation) {
		Parser ps=new Parser(new CompilerEnvirons(),new ErrorReporter(){

			public void error(String message, String sourceName, int line,
					String lineSource, int offset) {
				// TODO Auto-generated method stub
				
			}

			public EvaluatorException runtimeError(String message,
					String sourceName, int line, String lineSource,
					int lineOffset) {
				// TODO Auto-generated method stub
				return null;
			}

			public void warning(String message, String sourceName, int line,
					String lineSource, int lineOffset) {
				// TODO Auto-generated method stub
				
			}
			
		});
		ps.parse(substring, "", 0);
		String encodedSource = ps.getEncodedSource();
		Decompiler de = new Decompiler();
		de.setIndent(computeIndentation);
		if (computeIndentation==null||computeIndentation.length()==0)
		return de.decompile(encodedSource, 0, new UintMap()).trim();
		else return de.decompile(encodedSource, 0, new UintMap());
	}

}

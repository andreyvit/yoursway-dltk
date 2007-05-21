package org.eclipse.dltk.ruby.internal.parsers.jruby;

/***** BEGIN LICENSE BLOCK *****
 * Version: CPL 1.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Common Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2002-2004 Anders Bengtsson <ndrsbngtssn@yahoo.se>
 * Copyright (C) 2002-2004 Jan Arne Petersen <jpetersen@uni-bonn.de>
 * Copyright (C) 2004 Thomas E Enebo <enebo@acm.org>
 * Copyright (C) 2004 Stefan Matthias Aust <sma@3plus4.de>
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the CPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the CPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.jruby.ast.Node;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.lexer.yacc.LexerSource;
import org.jruby.lexer.yacc.SyntaxException;
import org.jruby.parser.DefaultRubyParser;
import org.jruby.parser.RubyParserConfiguration;
import org.jruby.parser.RubyParserPool;
import org.jruby.parser.RubyParserResult;

/**
 * Serves as a simple facade for all the parsing magic.
 */
public class DLTKRubyParser {
	private boolean success;

	public DLTKRubyParser() {
	}

	public Node parse(String file, String content,
			IProblemReporter problemReporter) {
		return parse(file, new StringReader(content), problemReporter);
	}

	public Node parse(String file, Reader content,
			IProblemReporter problemReporter) {
		RubyParserConfiguration configuration = new RubyParserConfiguration();

		DefaultRubyParser parser = null;
		RubyParserResult result = null;
		success = false;
		IDLTKRubyWarnings warnings;
		if (problemReporter == null)
			warnings = new DLTKRubyNullWarnings();
		else
			warnings = new DLTKRubyWarnings(problemReporter);

		try {
			parser = RubyParserPool.getInstance().borrowParser();
			parser.setWarnings(warnings);

			LexerSource lexerSource = LexerSource.getSource(file, content);
			result = parser.parse(configuration, lexerSource);
			if (result.isEndSeen()) {
				result.setEndSeen(false);
			}
			success = true;
		} catch (SyntaxException e) {
//			StringBuffer buffer = new StringBuffer(100);
//			buffer.append(e.getPosition().getFile()).append(':');
//			buffer.append(e.getPosition().getEndLine()).append(": ");
//			buffer.append(e.getMessage());
			
			ISourcePosition position = e.getPosition();
			if( position.getStartOffset() == position.getEndOffset()) {
				if( position.getStartOffset() > 0 ) {
					position.adjustStartOffset(-1);
				}
			}
			warnings.error(position, e.getMessage());
		} finally {
			RubyParserPool.getInstance().returnParser(parser);
		}

		if (result != null) {
			result.addAppendBeginNodes();
			return result.getAST();
		}
		return null;
	}

	public boolean isSuccess() {
		return success;
	}

}

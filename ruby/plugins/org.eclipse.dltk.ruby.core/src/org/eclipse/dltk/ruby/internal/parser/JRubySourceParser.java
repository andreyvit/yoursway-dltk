/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.parser;

import java.io.CharArrayReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.dltk.ruby.core.utils.RubySyntaxUtils;
import org.eclipse.dltk.ruby.internal.parsers.jruby.DLTKRubyParser;
import org.eclipse.dltk.ruby.internal.parsers.jruby.RubyASTBuildVisitor;
import org.jruby.ast.Node;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.parser.RubyParserResult;

public class JRubySourceParser extends AbstractSourceParser {

	private static boolean silentState = true;

	public static boolean isSilentState() {
		return silentState;
	}

	/**
	 * This option allows parser to suppress errors and exceptions and in result
	 * generate possibly partially non-correct AST instead of failing with
	 * exception. For running parser tests this option are being set to
	 * <code>false</code>.
	 */
	public static void setSilentState(boolean s) {
		silentState = s;
	}

	private final class ASTPositionsCorrector extends ASTVisitor {
		public boolean visitGeneral (ASTNode node) throws Exception {
			if (node.sourceStart() < 0 || node.sourceEnd() < 0)
				return true;
			int st = 0;
			int en = 0;
			int n_st = 0;
			int n_en = 0;
			for (Iterator iterator = fixPositions.iterator(); iterator
					.hasNext();) {
				Integer pos = (Integer) iterator.next();
				int fixPos = pos.intValue();
				// starts
				if (node.sourceStart() > fixPos) {
					st++;
				}
				if (node.sourceEnd() > fixPos) {
					en++;
				}
				if (node instanceof Declaration) {
					Declaration declaration = (Declaration) node;
					if (declaration.getNameStart() > fixPos) {
						n_st++;
					}
					if (declaration.getNameEnd() > fixPos) {
						n_en++;
					}
				}
			}

			node.setStart(node.sourceStart() - st * magicLength);
			node.setEnd(node.sourceEnd() - en * magicLength);
			if (node instanceof Declaration) {
				Declaration declaration = (Declaration) node;
				declaration.setNameStart(declaration.getNameStart() - n_st
						* magicLength);
				declaration.setNameEnd(declaration.getNameEnd() - n_en
						* magicLength);
			}
//			if (st == 0 && en == 0 && n_st == 0 && n_en == 0)
//				return false;

			return true;
		}
	}

	private static final boolean TRACE_AST_JRUBY = Boolean.valueOf(
			Platform.getDebugOption("org.eclipse.dltk.core/traceAST/jruby")) //$NON-NLS-1$
			.booleanValue();

	private static final boolean TRACE_AST_DLTK = Boolean.valueOf(
			Platform.getDebugOption("org.eclipse.dltk.core/traceAST/dltk")) //$NON-NLS-1$
			.booleanValue();

	private static final Pattern DOT_FIXER = Pattern.compile("\\.(?=[\\s,\\)\\]\\}]|$)"); //$NON-NLS-1$
	private static final Pattern DOLLAR_FIXER = Pattern.compile("\\$(?=[\\s,\\)\\]\\}]|$)"); //$NON-NLS-1$
	private static final Pattern AT_FIXER = Pattern.compile("@(?=[\\s,\\)\\]\\}]|$)"); //$NON-NLS-1$
	private static final Pattern COLON_FIXER1 = Pattern.compile("::(?=[\\s,\\)\\]\\}]|$)"); //$NON-NLS-1$
	private static final Pattern COLON_FIXER2 = Pattern.compile("(?:=>.*,[\\s]*)(:)(?=[\\s]*(?=[,}\\)]))", Pattern.MULTILINE); //$NON-NLS-1$
	private static final Pattern COLON_FIXER3 = Pattern.compile(":(?=[\\s]*(?=[,}\\)]))", Pattern.MULTILINE); //$NON-NLS-1$
	private static final Pattern COLON_FIXER_UNSAFE1 = Pattern.compile("(?:=>.*,[\\s]*)(:)(?=[\\s]*$)", Pattern.MULTILINE); //$NON-NLS-1$
	private static final Pattern COLON_FIXER_UNSAFE2 = Pattern.compile(":(?=[\\s]*$)", Pattern.MULTILINE); //$NON-NLS-1$
	private static final Pattern INST_BRACK_FIXER = Pattern.compile("@(])"); //$NON-NLS-1$
	private static final Pattern GLOB_BRACK_FIXER = Pattern.compile("\\$(])"); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER1 = Pattern.compile("(?:=>.*)(,)(?=[\\s)]*do)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER2 = Pattern.compile(",(?=[\\s)]*do)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER3 = Pattern.compile("(?:=>.*,[^=>\r\n]*)([\\:a-zA-Z0-9_!?])(?=[\\s)]*do)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER4 = Pattern.compile("(?:=>.*)(,)(?=[\\s]*[)][\\s]*$)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER5 = Pattern.compile(",(?=[\\s]*[)][\\s]*$)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER6 = Pattern.compile("(?:=>.*,[^=>\r\n]*)([\\:a-zA-Z0-9_!?])(?=[\\s]*[)][\\s]*$)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER_UNSAFE1 = Pattern.compile("(?:=>.*)(,)(?=[\\s)]*$)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER_UNSAFE2 = Pattern.compile(",(?=[\\s)]*$)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern COMMA_FIXER_UNSAFE3 = Pattern.compile("(?:=>.*,[^=>\r\n]*)([\\:a-zA-Z0-9_!?])(?=[\\s)]*$)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern HASH_FIXER1 = Pattern.compile("=>(?=[\\s)]*do)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern HASH_FIXER2 = Pattern.compile("=>(?=[\\s]*[,}\\)])", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern HASH_FIXER_UNSAFE1 = Pattern.compile("=>(?=[\\s)]*$)", Pattern.MULTILINE); //$NON-NLS-1$
    private static final Pattern HASH_FIXER_UNSAFE2 = Pattern.compile("^(?:\\s*)(\\s)(?:[a-zA-Z0-9_\"\':]+[\\s]*=>.*$)", Pattern.MULTILINE); //$NON-NLS-1$
	private IProblemReporter problemReporter;
	private static final String missingName  = "_missing_method_name_"; //$NON-NLS-1$
	private static final String missingName2 = "NoConstant___________"; //$NON-NLS-1$
	private static final String missingName3 = "_missing_param_name__"; //$NON-NLS-1$
	private static final String missingName4 = "_m_key_ => _m_value__"; //$NON-NLS-1$
	private static final int magicLength = missingName.length(); // missingName.len should == missingName2.len

	private final List fixPositions = new ArrayList();

	private String fixBrokenThings(Pattern pattern, String content, String replacement, int delta) {
		Matcher matcher = pattern.matcher(content);
		StringBuffer result = new StringBuffer();
		int regionStart = 0;
		while (matcher.find(regionStart)) {
			int offset = matcher.start(matcher.groupCount());
			if (offset > regionStart)
				result.append(content.subSequence(regionStart, offset));
			fixPositions.add(new Integer( result.length() ));
			result.append(replacement);
//			fixPositions.add(new Integer(offset + fixPositions.size() * magicLength));			
			regionStart = offset + delta; //2
		}
		if (regionStart < content.length() - 1)
			result.append(content.subSequence(regionStart, content.length()));
		if (regionStart == 0)
			return content; // nothing fixed
		else
			return result.toString();
	}
	
	private String fixBrokenDots(String content) {
		return fixBrokenThings(DOT_FIXER, content, "." + missingName, 1); //$NON-NLS-1$
	}
	
	private String fixBrokenColons(String content) {
		String content2 = fixBrokenThings(COLON_FIXER1, content, "::" + missingName2, 2); //$NON-NLS-1$
		content2 = fixBrokenThings(COLON_FIXER2, content2, ":" + missingName4, 1); //$NON-NLS-1$
		return fixBrokenThings(COLON_FIXER3, content2, ":" + missingName2, 1); //$NON-NLS-1$
	}

    private String fixBrokenColonsUnsafe(String content) {
      String content2 = fixBrokenThings(COLON_FIXER_UNSAFE1, content, ":" + missingName4, 1); //$NON-NLS-1$
      return fixBrokenThings(COLON_FIXER_UNSAFE2, content2, ":" + missingName2, 1); //$NON-NLS-1$
    }

	private String fixBrokenDollars(String content) {
		return fixBrokenThings(DOLLAR_FIXER, content, "$" + missingName, 1); //$NON-NLS-1$
	}
	
	private String fixBrokenAts(String content) {
		return fixBrokenThings(AT_FIXER, content, "@" + missingName, 1); //$NON-NLS-1$
	}
	
	private String fixBrokenInstbracks(String content) {
		return fixBrokenThings(INST_BRACK_FIXER, content, "@" + missingName, 1); //$NON-NLS-1$
	}
	
	private String fixBrokenGlobbracks(String content) {
		return fixBrokenThings(GLOB_BRACK_FIXER, content, "$" + missingName, 1); //$NON-NLS-1$
	}

    private String fixBrokenParens(String content) {
      char[] contents = content.toCharArray();
      StringBuffer buffer = new StringBuffer(contents.length);
      int depth = 0;
      int start = contents.length;

      for (int cnt = (start - 1); cnt >= 0; cnt--) {
        if (contents[cnt] == '(') {
          depth--;

          if (depth < 0) {
            int eol = cnt;
            for (int cnt2 = cnt; cnt2 < start; cnt2++) {
              if ((contents[cnt2] == '\r') || (contents[cnt2] == '\n')) {
                eol = cnt2;

                break;
              }
            }

            buffer.insert(0, contents, eol, (start - eol));
            buffer.insert(0, ')');
            buffer.insert(0, contents, cnt, (eol - cnt));
            start = cnt;
            depth = 0;
          }
        }
        else if (contents[cnt] == ')') {
          depth++;
        }
      }

      if (start > 0) {
    	  buffer.insert(0, contents, 0, start);
      }

      return buffer.toString();
    }

    private String fixBrokenCommas(String content) {
      String content2 = content;
      content2 = fixBrokenThings(COMMA_FIXER1, content2, "," + missingName4 + " ", 1); //$NON-NLS-1$ //$NON-NLS-2$
      content2 = fixBrokenThings(COMMA_FIXER2, content2, "," + missingName3 + " ", 1); //$NON-NLS-1$ //$NON-NLS-2$
      content2 = fixBrokenThings(COMMA_FIXER3, content2, missingName4 + " ", 1); //$NON-NLS-1$
      content2 = fixBrokenThings(COMMA_FIXER4, content2, "," + missingName4, 1); //$NON-NLS-1$
      content2 = fixBrokenThings(COMMA_FIXER5, content2, "," + missingName3, 1); //$NON-NLS-1$
      return fixBrokenThings(COMMA_FIXER6, content2, missingName4 + " ", 1); //$NON-NLS-1$
    }

    private String fixBrokenCommasUnsafe(String content) {
      String content2 = content;
      content2 = fixBrokenThings(COMMA_FIXER_UNSAFE1, content2, "," + missingName4, 1); //$NON-NLS-1$
      content2 = fixBrokenThings(COMMA_FIXER_UNSAFE2, content2, "," + missingName3, 1); //$NON-NLS-1$
      return fixBrokenThings(COMMA_FIXER_UNSAFE3, content2, missingName4 + " ", 1); //$NON-NLS-1$
    }

    private String fixBrokenHashes(String content) {
      String content2 = content;
      content2 = fixBrokenThings(HASH_FIXER1, content2, "=>" + missingName3 + " ", 2); //$NON-NLS-1$ //$NON-NLS-2$
      return fixBrokenThings(HASH_FIXER2, content2, "=>" + missingName3, 2); //$NON-NLS-1$
    }

    private String fixBrokenHashesUnsafe(String content) {
      String content2 = content;
      content2 = fixBrokenThings(HASH_FIXER_UNSAFE1, content2, "=>" + missingName3, 2); //$NON-NLS-1$
      return fixBrokenThings(HASH_FIXER_UNSAFE2, content2, " " + missingName + " ", 1); //$NON-NLS-1$ //$NON-NLS-2$
    }

	private final boolean[] errorState = new boolean[1];

  private RubyParserResult parserResult;
  public RubyParserResult getParserResult() {
    return parserResult;
  }

	private class ProxyProblemReporter implements IProblemReporter {

		private final IProblemReporter original;

		public ProxyProblemReporter(IProblemReporter original) {
			super();
			this.original = original;
		}

		public IMarker reportProblem(IProblem problem) throws CoreException {
			IMarker m = null;
			if (original != null)
				m = original.reportProblem(problem);
			if (problem.isError()) {
				errorState[0] = true;
			}
			return m;
		}

		public void clearMarkers() {
			this.original.clearMarkers();
		}

		public boolean isMarkersCleaned() {
			return original.isMarkersCleaned();
		}
	}
	
	public JRubySourceParser() {
		this.problemReporter = null;
	}

	/**
	 * Should return visitor for creating ModuleDeclaration from JRuby's AST
	 * @param module
	 * @param content
	 * @return
	 */
	protected NodeVisitor getASTBuilderVisitor(ModuleDeclaration module,
			char[] content) {
		return new RubyASTBuildVisitor(module, content);
	}

	private boolean isMethodNameChar(char inputChar, char prevChar) {
	  return (((inputChar >= 'a') && (inputChar <= 'z')) ||
              ((inputChar >= '0') && (inputChar <= '9')) ||
              ((inputChar >= 'A') && (inputChar <= 'Z')) ||
              (inputChar == '_') ||
              ((inputChar == '?') && isMethodNameChar(prevChar, '@')) ||
              ((inputChar == '!') && isMethodNameChar(prevChar, '@')));
	}

	private boolean isPrefixKeyword(char[] content, int endOffset) {
	  boolean isPrefixKeyword = false;
	  int startOffset = -1;

	  if (endOffset >= 5) {
        startOffset = (endOffset - 5);
        isPrefixKeyword = "return".equals(String.valueOf(content, startOffset, 6)); //$NON-NLS-1$

        if (!isPrefixKeyword) {
          isPrefixKeyword = "unless".equals(String.valueOf(content, startOffset, 6)); //$NON-NLS-1$
        }
	  }

      if (!isPrefixKeyword && endOffset >= 4) {
        startOffset = (endOffset - 4);
        isPrefixKeyword = "while".equals(String.valueOf(content, startOffset, 5)); //$NON-NLS-1$

        if (!isPrefixKeyword) {
          isPrefixKeyword = "elsif".equals(String.valueOf(content, startOffset, 5)); //$NON-NLS-1$
        }

        if (!isPrefixKeyword) {
          isPrefixKeyword = "until".equals(String.valueOf(content, startOffset, 5)); //$NON-NLS-1$
        }
      }

      if (!isPrefixKeyword && endOffset >= 3) {
        startOffset = (endOffset - 3);
        isPrefixKeyword = "then".equals(String.valueOf(content, startOffset, 4)); //$NON-NLS-1$

        if (!isPrefixKeyword) {
          isPrefixKeyword = "case".equals(String.valueOf(content, startOffset, 4)); //$NON-NLS-1$
        }
      }

      if (!isPrefixKeyword && endOffset >= 2) {
        startOffset = (endOffset - 2);
        isPrefixKeyword = "and".equals(String.valueOf(content, startOffset, 3)); //$NON-NLS-1$
      }

	  if (!isPrefixKeyword && endOffset >= 1) {
	    startOffset = (endOffset - 1);
	    isPrefixKeyword = "if".equals(String.valueOf(content, startOffset, 2)); //$NON-NLS-1$

	    if (!isPrefixKeyword) {
	      isPrefixKeyword = "or".equals(String.valueOf(content, startOffset, 2)); //$NON-NLS-1$
	    }
	  }

	  if (isPrefixKeyword) {
	    isPrefixKeyword = ((startOffset == 0) ||
	                       !isMethodNameChar(content[startOffset - 1],
	                                         (startOffset > 1) ? content[startOffset - 2] : '@'));
	  }

	  return isPrefixKeyword;
	}

	private char[] fixSpacedParens(char[] content) {
	  char[] fixedContent = new char[content.length];
	  System.arraycopy(content, 0, fixedContent, 0, content.length);

	  boolean inComment = false;
	  boolean inSingleString = false;
	  boolean inDoubleString = false;
	  boolean inBackQuoteString = false;
	  boolean inBraceString = false;
	  boolean inInterpString = false;
	  boolean inRegexp = false;
	  for (int cnt = 0, max = fixedContent.length; cnt < max; cnt++) {
	    //ssanders - If there is a space between a method name and its opening parenthesis
	    if ((cnt > 1) && !inComment && !inSingleString && !inDoubleString && !inBackQuoteString && !inBraceString && !inRegexp && (fixedContent[cnt] == '(') && (fixedContent[cnt - 1] == ' ')) {
	      if (isMethodNameChar(fixedContent[cnt - 2], (cnt > 2) ? fixedContent[cnt - 3] : '@')) {
	        if (!isPrefixKeyword(fixedContent, (cnt - 2))) {
	          //ssanders - Invert the space and parenthesis to correct warning and position info
	          fixedContent[cnt - 1] = '(';
	          fixedContent[cnt] = ' ';
	        }
	      }
	    }
	    else if (fixedContent[cnt] == '#') {
	      if ((inSingleString || inDoubleString || inBackQuoteString || inBraceString || inRegexp) && (fixedContent[cnt + 1] == '{')) {
	        inInterpString = true;
	      }
	      else if (!inSingleString && !inDoubleString && !inBackQuoteString && !inBraceString) {
	        inComment = true;
	      }
	    }
	    else if ((fixedContent[cnt] == '\r') || (fixedContent[cnt] == '\n')) {
	      inComment = false;
	    }
        else if (fixedContent[cnt] == '"') {
          if ((cnt < 1) || (fixedContent[cnt - 1] != '\\')) {
            if (!inComment & !inInterpString) {
              inDoubleString = !inDoubleString;
            }
          }
        }
        else if (fixedContent[cnt] == '\'') {
          if ((cnt < 1) || (fixedContent[cnt - 1] != '\\')) {
            if (!inComment) {
              inSingleString = !inSingleString;
            }
          }
        }
        else if (fixedContent[cnt] == '`') {
          if ((cnt < 1) || (fixedContent[cnt - 1] != '\\')) {
            if (!inComment) {
              inBackQuoteString = !inBackQuoteString;
            }
          }
        }
        else if (fixedContent[cnt] == '{') {
          if ((cnt < 1) || (fixedContent[cnt - 1] == '%')) {
            if (!inComment) {
              inBraceString = true;
            }
          }
        }
        else if (fixedContent[cnt] == '}') {
          if ((cnt < 1) || (fixedContent[cnt - 1] != '\\')) {
            if (!inComment) {
              if (inInterpString) {
                inInterpString = false;
              }
              else {
                inBraceString = false;
              }
            }
          }
        }
        else if (fixedContent[cnt] == '/') {
          if ((cnt < 1) || (fixedContent[cnt - 1] != '\\')) {
            if (!inComment && !inSingleString && !inDoubleString && !inBackQuoteString && !inBraceString) {
              inRegexp = !inRegexp;
            }
          }
        }
	  }
	  
	  return fixedContent;
	}

	public ModuleDeclaration parse(final char[] fileName, char[] content, IProblemReporter reporter) {
		this.problemReporter = reporter;
		try {
			DLTKRubyParser parser = new DLTKRubyParser();
			ProxyProblemReporter proxyProblemReporter = new ProxyProblemReporter(
					problemReporter);
			errorState[0] = false;

			long timeStart = 0;
			if (TRACE_AST_DLTK)
			  timeStart = System.currentTimeMillis();

			char[] fixedContent = fixSpacedParens(content);
			Node node;
			if (Arrays.equals(fixedContent, content) != true) {
			  //ssanders - Parse with reporter to collect parenthesis warnings
			  parser.parse("", new CharArrayReader(content), proxyProblemReporter); //$NON-NLS-1$
			  //ssanders - However, use modified content to have corrected position info 
			  node = parser.parse("", new CharArrayReader(fixedContent), null); //$NON-NLS-1$
			}
			else {
			  node = parser.parse("", new CharArrayReader(content), proxyProblemReporter); //$NON-NLS-1$
			}
			fixPositions.clear();
			if (!parser.isSuccess() || errorState[0]) {
				String content2 = fixBrokenDots(String.valueOf(fixedContent));
				content2 = fixBrokenColons(content2);
				content2 = fixBrokenDollars(content2);
				content2 = fixBrokenAts(content2);
				content2 = fixBrokenInstbracks(content2);
				content2 = fixBrokenGlobbracks(content2);

				content2 = fixBrokenParens(content2);
                content2 = fixBrokenCommas(content2);
                content2 = fixBrokenHashes(content2);

                Node node2 = parser.parse("", new StringReader(content2), null); //$NON-NLS-1$
                if (node2 != null)
                    node = node2;
                else {
                    fixPositions.clear();

                    content2 = fixBrokenColonsUnsafe(content2);
                    content2 = fixBrokenCommasUnsafe(content2);
                    content2 = fixBrokenHashesUnsafe(content2);

                    node2 = parser.parse("", new StringReader(content2), new IProblemReporter() { //$NON-NLS-1$

                      public IMarker reportProblem(IProblem problem) {
                        if (DLTKCore.DEBUG) {
                          String strFileName = ""; //$NON-NLS-1$
                          if (fileName != null) {
                            strFileName = String.valueOf(fileName);
                          }

                          System.out.println("JRubySourceParser.parse(): Fallback Parse Problem - fileName=" + strFileName + //$NON-NLS-1$
                                             ", message=" + problem.getMessage() + ", line=" + problem.getSourceLineNumber()); //$NON-NLS-1$ //$NON-NLS-2$
                        }

                        return null;
                      }

              		  public void clearMarkers() {
            		  }

              		  public boolean isMarkersCleaned() {
              			  return true;
              		  }

                    });
                    if (node2 != null)
                      node = node2;
                    else
                      fixPositions.clear();
                }
				content = content2.toCharArray();
			}

			ModuleDeclaration module = new ModuleDeclaration(content.length);
			NodeVisitor visitor = getASTBuilderVisitor(module, content);
			if (node != null)
				node.accept(visitor);

			if (node != null) {
				if (TRACE_AST_JRUBY || TRACE_AST_DLTK)
					System.out.println("\n\nAST rebuilt\n"); //$NON-NLS-1$
				if (TRACE_AST_JRUBY)
					System.out.println("JRuby AST:\n" + node.toString()); //$NON-NLS-1$
				if (TRACE_AST_DLTK)
					System.out.println("DLTK AST:\n" + module.toString()); //$NON-NLS-1$
			}

			if (!fixPositions.isEmpty())
				try {
					module.traverse(new ASTPositionsCorrector());
				} catch (Exception e) {
					RubyPlugin.log(e);
				}

			long timeEnd = System.currentTimeMillis();
			if (TRACE_AST_DLTK)
				System.out.println("Parsing took " + (timeEnd - timeStart) //$NON-NLS-1$
						+ " ms"); //$NON-NLS-1$
			this.parserResult = parser.getParserResult();
			
            if (!parser.isSuccess() && module.isEmpty()) {
               minimumParse(content, module);
			}
			
			return module;
		} catch (Throwable t) {
//			if( DLTKCore.DEBUG ) {
				t.printStackTrace();
//			}
			if (isSilentState()) {
				ModuleDeclaration mdl = new ModuleDeclaration(1);
				return mdl;
			}
			throw new RuntimeException(t);
		}
	}

	public ModuleDeclaration parse(String source) {
		return this.parse(null, source.toCharArray(), null);
	}
	
  /**
   * Really basic parse to find the first class or module definition, the intent is that 
   * a module declaration has at least a type in it (if one exists or can be parsed).
   * TODO(mhowe) make the name position more robust
   * 
   * @param content
   * @param md
   */	
  private static void minimumParse(char[] content, ModuleDeclaration md) {
    StringTokenizer toker = new StringTokenizer(new String(content));
    while (toker.hasMoreTokens()) {
      String token = toker.nextToken();
      if (token.equals("class") || token.equals("module")) { //$NON-NLS-1$ //$NON-NLS-2$
        String className = toker.nextToken();
        
        if (RubySyntaxUtils.isValidClass(className)) {
          String source = new String(content);
          int indexOf = source.indexOf(className);  //This isn't robust but don't think it actually matters
          int nameEnd = indexOf + className.length();
          TypeDeclaration type = new TypeDeclaration(className, indexOf, nameEnd, indexOf, source.length()-1);
          md.addStatement(type);
          if (toker.nextToken().equals("<")) { //$NON-NLS-1$
            String superClass = toker.nextToken();
            if (RubySyntaxUtils.isValidClass(superClass)) {
              indexOf = source.indexOf(className); 
              type.addSuperClass(new SimpleReference(indexOf, indexOf + superClass.length(), superClass));
            }
          }
          type.setBody(new Block(indexOf + nameEnd, source.length()-1));
          return;
        }
      }
    }
  }

}

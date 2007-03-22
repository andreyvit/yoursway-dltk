package org.eclipse.dltk.compiler.problem;

import java.util.Locale;


public class DefaultProblemFactory implements IProblemFactory {
	private Locale locale;
	
	public DefaultProblemFactory() {
		this(Locale.getDefault());
	}
	
	public DefaultProblemFactory(Locale loc) {
		this.locale = loc;
		if (Locale.getDefault().equals(loc)){
			// if (DEFAULT_LOCALE_TEMPLATES == null){
				// DEFAULT_LOCALE_TEMPLATES = loadMessageTemplates(loc);
			// }
			// this.messageTemplates = DEFAULT_LOCALE_TEMPLATES;
		} else {
			// this.messageTemplates = loadMessageTemplates(loc);
		}
	}
	
	
	public IProblem createProblem(String originatingFileName, int problemId,
			String[] problemArguments, String[] messageArguments, int severity,
			int startPosition, int endPosition, int lineNumber, int columnNumber ) {

		String message = getLocalizedMessage(problemId, messageArguments);
						
		return new DefaultProblem(null, message, problemId,
				problemArguments, severity, startPosition, endPosition, lineNumber, columnNumber );
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLocalizedMessage(int problemId, String[] messageArguments) {
		
		return "Problem message";
	/*		String message = (String) this.messageTemplates.get(keyFromID(id & IProblem.IgnoreCategoriesMask)); 
			if (message == null) {
				return "Unable to retrieve the error message for problem id: " //$NON-NLS-1$
					+ (id & IProblem.IgnoreCategoriesMask)
					+ ". Check compiler resources.";  //$NON-NLS-1$
			}

			// for compatibility with MessageFormat which eliminates double
			// quotes in original message
			char[] messageWithNoDoubleQuotes =
				CharOperation.replace(message.toCharArray(), DOUBLE_QUOTES, SINGLE_QUOTE);

			if (problemArguments == null) 
				return new String(messageWithNoDoubleQuotes);

			int length = messageWithNoDoubleQuotes.length;
			int start = 0;
			int end = length;
			StringBuffer output = null;
			if ((id & IProblem.Javadoc) != 0) {
				if (output == null) output = new StringBuffer(10+length+problemArguments.length*20);
				output.append((String) this.messageTemplates.get(keyFromID(IProblem.JavadocMessagePrefix & IProblem.IgnoreCategoriesMask)));
			}
			while (true) {
				if ((end = CharOperation.indexOf('{', messageWithNoDoubleQuotes, start)) > -1) {
					if (output == null) output = new StringBuffer(length+problemArguments.length*20);
					output.append(messageWithNoDoubleQuotes, start, end - start);
					if ((start = CharOperation.indexOf('}', messageWithNoDoubleQuotes, end + 1)) > -1) {
						int index = -1;
						String argId = new String(messageWithNoDoubleQuotes, end + 1, start - end - 1);
						try {
							index = Integer.parseInt(argId);
							output.append(problemArguments[index]);
						} catch (NumberFormatException nfe) {
							output.append(messageWithNoDoubleQuotes, end + 1, start - end);
						} catch (ArrayIndexOutOfBoundsException e) {
							return "Cannot bind message for problem (id: " //$NON-NLS-1$
								+ (id & IProblem.IgnoreCategoriesMask)
								+ ") \""  //$NON-NLS-1$
								+ message
								+ "\" with arguments: {" //$NON-NLS-1$
								+ Util.toString(problemArguments)
								+"}"; //$NON-NLS-1$
						}
						start++;
					} else {
						output.append(messageWithNoDoubleQuotes, end, length);
						break;
					}
				} else {
					if (output == null) return new String(messageWithNoDoubleQuotes);
					output.append(messageWithNoDoubleQuotes, start, length - start);
					break;
				}
			}

			// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=120410
			return new String(output.toString());
		}*/
	}

}

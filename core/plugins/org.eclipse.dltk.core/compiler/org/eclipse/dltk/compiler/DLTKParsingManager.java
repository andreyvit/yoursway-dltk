package org.eclipse.dltk.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceElementParser;

public class DLTKParsingManager {

	private static class ParserTuple {
		public final Object parser;
		public final int priority;

		public ParserTuple(Object parser, int priority) {
			super();
			this.parser = parser;
			this.priority = priority;
		}

	}

	private static final String EXT_POINT = "org.eclipse.dltk.core.sourceParsers";

	private static final String EXT_POINT_2 = "org.eclipse.dltk.core.sourceElementParsers";

	private final static Map sourceParsersByNature = new HashMap();

	private final static Map sourceElementParsersByNature = new HashMap();

	private static void fillinParsers (String extPoint, Map map) {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(
				extPoint);
		if (extensionPoint == null)
			return;
		IExtension[] ext = extensionPoint.getExtensions();
		for (int a = 0; a < ext.length; a++) {
			IConfigurationElement[] elements = ext[a]
					.getConfigurationElements();
			IConfigurationElement myElement = elements[0];
			try {
				String nature = myElement.getAttribute("nature");
				List list = (List) map.get(nature);
				if (list == null) {
					list = new ArrayList();
					map.put(nature, list);
				}
				Object parser =  myElement.createExecutableExtension("class");

				String pr = myElement.getAttribute("priority");
				int priority = 0;
				try {
					priority = Integer.parseInt(pr);
				} catch (NumberFormatException e) {
				}
				list.add(new ParserTuple(parser, priority));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static {
		// Source parsers
		fillinParsers(EXT_POINT, sourceParsersByNature);
		// Source element parsers
		fillinParsers(EXT_POINT_2, sourceElementParsersByNature);
	}
	
	private static Object findMostCoolParser (String nature, Map map) {
		List t = (List) map.get(nature);
		if (t != null) {
			Object parser = null;
			int maxPriority = -1;
			for (Iterator iterator = t.iterator(); iterator.hasNext();) {
				ParserTuple tuple = (ParserTuple) iterator.next();
				if (tuple.priority > maxPriority) {
					parser = tuple.parser;
				}
			}
			if (parser != null)
				return parser;
		}
		return null;
	}

	public static ISourceParser createParser(String langNature) {
		return (ISourceParser) findMostCoolParser(langNature, sourceParsersByNature);
	}

	public static ISourceParser createParser(IDLTKLanguageToolkit toolkit) {
		return createParser(toolkit.getNatureID());
	}

	public static ISourceElementParser createSourceElementParser(String langNature, char[] content,
			ISourceElementRequestor requestor, IProblemReporter reporter) {
		ISourceElementParser parser = (ISourceElementParser) findMostCoolParser(langNature, sourceElementParsersByNature);
		if (parser != null)
			return parser;
		try {
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager.getLanguageToolkit(langNature);
			return toolkit.createSourceElementParser(requestor, reporter, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ISourceElementParser createSourceElementParser(IDLTKLanguageToolkit toolkit, char[] content,
			ISourceElementRequestor requestor, IProblemReporter reporter) {
		ISourceElementParser parser = createSourceElementParser(toolkit.getNatureID(), content, requestor, reporter);
		if (parser != null)
			return parser;
		try {
			return toolkit.createSourceElementParser(requestor, reporter, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

}

package org.eclipse.dltk.ruby.core;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class PredefinedVariables {
	private static final String MESSAGE_PROPERTIES = "variables.properties";

	private static final String NAME = "name";

	private static final String TYPE = "type";

	private static final String DOC = "doc";

	static Map parseProperties(Properties props, String[] postfixes) {
		Map entries = new HashMap();

		Iterator it = props.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();

			for (int i = 0; i < postfixes.length; ++i) {
				final String postfix = postfixes[i];

				int index = key.indexOf("_" + postfix);
				if (index != -1) {
					String name = key.substring(0, index);

					Map entry = (Map) entries.get(name);

					if (entry == null) {
						entry = new HashMap();
						entries.put(name, entry);
					}

					entry.put(postfix, props.get(key));
				}
			}
		}

		return entries;
	}

	private static Map nameToTypeMap = new HashMap();
	private static Map nameToDocMap = new HashMap();

	public static String getTypeOf(String name) {
		return (String) nameToDocMap.get(name);
	}

	public static String getDocOf(String name) {
		return (String) nameToDocMap.get(name);
	}

	static {
		try {
			URL url = RubyPlugin.getDefault().getBundle().getEntry(
					MESSAGE_PROPERTIES);
			InputStream input = null;
			try {
				input = new BufferedInputStream(url.openStream());
				Properties props = new Properties();
				props.load(input);

				Map parsedProps = parseProperties(props, new String[] { NAME,
						TYPE, DOC });

				Iterator it = parsedProps.keySet().iterator();
				while (it.hasNext()) {
					Object key = it.next();
					Map entry = (Map) parsedProps.get(key);

					String name = (String) entry.get(NAME);
					String type = (String) entry.get(TYPE);
					String doc = (String) entry.get(DOC);

					nameToTypeMap.put(name, type);
					nameToDocMap.put(name, doc);

					// System.out.println("Name: " + name + "; Type: " + type + "; Doc: " + doc);
				}
			} finally {
				if (input != null) {
					input.close();
				}
			}
		} catch (IOException e) {
			IStatus status = new Status(IStatus.ERROR, RubyPlugin.PLUGIN_ID, 0,
					"Can't load ruby predefined variables", e);
			RubyPlugin.getDefault().getLog().log(status);
		}
	}
}

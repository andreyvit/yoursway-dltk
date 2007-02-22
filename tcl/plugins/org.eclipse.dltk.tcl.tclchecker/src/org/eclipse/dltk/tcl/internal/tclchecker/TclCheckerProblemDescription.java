package org.eclipse.dltk.tcl.internal.tclchecker;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class TclCheckerProblemDescription {

	// Resource file name
	private static final String MESSAGE_PROPERTIES = "tclchecker.properties";

	// Postifxes in resource file
	private static final String TYPE_POSTFIX = "_type";

	private static final String EXPLANATION_POSTFIX = "_explanation";

	// Tcl problem types
	public static final int ERROR = 0;

	private static final String ERROR_STR = "error";

	public static final int UPGRADE_ERROR = 1;

	private static final String UPGRADE_ERROR_STR = "upgrade error";

	public static final int WARNING = 2;

	private static final String WARNING_STR = "warning";

	public static final int NON_PORTABLE_WARNING = 3;

	private static final String NON_PORTABLE_WARNING_STR = "non-portable warning";

	public static final int UPGRADE_WARNING = 4;

	private static final String UPGRADE_WARNING_STR = "upgrade warning";

	public static final int PERFORMANCE_WARNING = 5;

	private static final String PERFORMANCE_WARNING_STR = "performance warning";

	public static final int USAGE_WARNING = 6;

	private static final String USAGE_WARNING_STR = "usage warning";

	private static Properties props = new Properties();

	static {
		try {
			URL url = TclCheckerPlugin.getDefault().getBundle().getEntry(
					MESSAGE_PROPERTIES);
			InputStream input = null;
			try {
				input = new BufferedInputStream(url.openStream());
				props.load(input);
			} finally {
				if (input != null) {
					input.close();
				}
			}
		} catch (IOException e) {
			IStatus status = new Status(IStatus.ERROR,
					TclCheckerPlugin.PLUGIN_ID, 0,
					"Can't load TclChecker problem descriptions", e);
			TclCheckerPlugin.getDefault().getLog().log(status);
		}
	}

	public static boolean isError(int category) {		
		return category == ERROR || category == UPGRADE_ERROR;
	}

	public static boolean isWarning(int category) {
		return !isError(category);
	}

	public static List getProblemIdentifiers() {
		List list = new ArrayList();
		Iterator it = props.keySet().iterator();
		while (it.hasNext()) {
			String s = (String) it.next();
			int index = s.indexOf("_type");
			if (index != -1) {
				list.add(s.substring(0, index));
			}
		}
		return list;
	}

	public static String getProblemType(String messageId) {
		return props.getProperty(messageId + TYPE_POSTFIX);
	}

	public static String getProblemExplanation(String messageId) {
		return props.getProperty(messageId + EXPLANATION_POSTFIX);
	}

	public static int matchProblemCategory(String type) {
		if (type.equalsIgnoreCase(ERROR_STR))
			return ERROR;
		else if (type.equalsIgnoreCase(UPGRADE_ERROR_STR))
			return UPGRADE_ERROR;
		else if (type.equalsIgnoreCase(WARNING_STR))
			return WARNING;
		else if (type.equalsIgnoreCase(NON_PORTABLE_WARNING_STR))
			return NON_PORTABLE_WARNING;
		else if (type.equalsIgnoreCase(UPGRADE_WARNING_STR))
			return UPGRADE_WARNING;
		else if (type.equalsIgnoreCase(PERFORMANCE_WARNING_STR))
			return PERFORMANCE_WARNING;
		else if (type.equalsIgnoreCase(USAGE_WARNING_STR))
			return USAGE_WARNING;

		return -1;
	}

	static public TclCheckerProblemDescription getProblemDescription(
			String messageId, String message) {
		int category = ERROR;
		String explanation = "";

		String type = getProblemType(messageId);

		if (type == null) {
			if (messageId.indexOf("warn") != -1) {
				category = WARNING;
			}
		} else {
			category = matchProblemCategory(type);
			explanation = getProblemExplanation(messageId);
		}

		return new TclCheckerProblemDescription(category, message, explanation);
	}

	private int category;

	private String message;

	private String explanation;

	protected TclCheckerProblemDescription(int category, String message,
			String explanation) {
		super();
		this.category = category;
		this.message = message;
		this.explanation = explanation;
	}

	public int getCategory() {
		return category;
	}

	public String getExplanation() {
		return explanation;
	}

	public String getMessage() {
		return message;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + category;
		result = PRIME * result
				+ ((explanation == null) ? 0 : explanation.hashCode());
		result = PRIME * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TclCheckerProblemDescription other = (TclCheckerProblemDescription) obj;
		if (category != other.category)
			return false;
		if (explanation == null) {
			if (other.explanation != null)
				return false;
		} else if (!explanation.equals(other.explanation))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}

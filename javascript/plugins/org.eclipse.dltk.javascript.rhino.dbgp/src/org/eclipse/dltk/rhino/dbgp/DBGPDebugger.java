package org.eclipse.dltk.rhino.dbgp;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaArray;
import org.mozilla.javascript.NativeJavaClass;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.UniqueTag;
import org.mozilla.javascript.debug.DebugFrame;
import org.mozilla.javascript.debug.DebuggableScript;
import org.mozilla.javascript.debug.Debugger;

public class DBGPDebugger extends Thread implements Debugger, Observer {

	private Socket socket;
	private PrintStream out;
	private HashMap strategies = new HashMap();
	private String runTransctionId;

	private final class FeatureGetCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			printResponse("<response command=\"feature_get\"\r\n"
					+ "          feature_name=\"supports_async\"\r\n"
					+ "          supported=\"1\"\r\n"
					+ "          transaction_id=\"" + options.get("-i") + "\">"
					+ "1</response>\r\n" + "");
		}
	}

	private final class FeatureSetCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			printResponse("<response command=\"feature_set\"\r\n"
					+ "          feature_name=\"max_children\"\r\n"
					+ "          success=\"1\"\r\n"
					+ "          transaction_id=\"" + options.get("-i")
					+ "\">\r\n" + "</response>\r\n" + "");
		}
	}

	private final class StdInCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			printResponse("<response command=\"srdin\"\r\n"
					+ "          success=\"1\"\r\n"
					+ "          transaction_id=\"" + options.get("-i")
					+ "\">\r\n" + "</response>\r\n" + "");
		}
	}

	private final class StdOutCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			printResponse("<response command=\"stdout\"\r\n"
					+ "          success=\"1\"\r\n"
					+ "          transaction_id=\"" + options.get("-i")
					+ "\">\r\n" + "</response>\r\n" + "");
		}
	}

	private final class StdErrCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			printResponse("<response command=\"stderr\"\r\n"
					+ "          success=\"1\"\r\n"
					+ "          transaction_id=\"" + options.get("-i")
					+ "\">\r\n" + "</response>\r\n" + "");
		}
	}

	private final class RunCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			String object = (String) options.get("-i");
			runTransctionId = object;
			synchronized (DBGPDebugger.this) {
				DBGPDebugger.this.notify();
			}
			cmanager.resume();
			// printResponse("<response command=\"run\"\r\n"
			// + "status=\"starting\"" + " reason=\"ok\""
			// + " transaction_id=\"" + object + "\">\r\n"
			// + "</response>\r\n" + "");
		}
	}

	private final class StopCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			printResponse("<response command=\"run\"\r\n"
					+ "status=\"stopped\"" + " reason=\"ok\""
					+ " transaction_id=\"" + options.get("-i") + "\">\r\n"
					+ "</response>\r\n" + "");
			System.exit(0);
		}
	}

	private final class StepOverCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			Object tid = options.get("-i");
			runTransctionId = (String) tid;
			cmanager.stepOver();

		}
	}

	private final class StepOutCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			Object tid = options.get("-i");
			runTransctionId = (String) tid;
			cmanager.stepOut();
		}
	}

	private final class StepIntoCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			Object tid = options.get("-i");
			runTransctionId = (String) tid;
			cmanager.stepIn();
		}
	}

	private final class StackGetCommand extends Command {
		void parseAndExecute(String command, final HashMap options) {
			int level = Integer.parseInt((String) options.get("-d"));
			StringBuffer stack = new StringBuffer();
			if (cmanager.getStackDepth() >= level) {
				DBGPDebugFrame stackFrame = cmanager.getStackFrame(level);

				stack.append("<stack level=\""
						+ level
						+ "\"\r\n"
						+ "           type=\"file\"\r\n"
						+ "           filename=\""
						+ new File(stackFrame.getSourceName()).toURI()
								.toASCIIString() + "\"\r\n"
						+ "           lineno=\""
						+ (stackFrame.getLineNumber() + 1) + "\"\r\n"
						+ "           where=\"" + stackFrame.getWhere()
						+ "\"\r\n" + "           cmdbegin=\"1:0\"\r\n"
						+ "           cmdend=\"" + "1" + ":10\"/>");
				printResponse("<response command=\"stack_get\"\r\n" + "\r\n"
						+ "          transaction_id=\"" + options.get("-i")
						+ "\">\r\n" +

						stack + "</response>\r\n" + "");
			}

		}
	}

	private final class StackDepthCommand extends Command {
		void parseAndExecute(String command, final HashMap options) {
			printResponse("<response command=\"stack_depth\"\r\n"
					+ "          depth=\"" + (cmanager.getStackDepth())
					+ "\"\r\n" + "          transaction_id=\""
					+ options.get("-i") + "\">\r\n" + "</response>\r\n" + "");

		}
	}

	private final class BreakCommand extends Command {
		void parseAndExecute(String command, final HashMap options) {
			cmanager.suspend();
			printResponse("<response command=\"break\"\r\n"
					+ "          success=\"1\"\r\n"
					+ "          transaction_id=\"" + options.get("-i")
					+ "\">\r\n" + "</response>\r\n" + "");

		}
	}

	private final class EvalCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			String value = Base64Helper
					.decodeString((String) options.get("--"));
			DBGPDebugFrame fr = cmanager.getStackFrame(0);
			Object evaluated = fr.eval(value);
			StringBuffer valueBuffer = new StringBuffer();
			printProperty("eval", "eval", evaluated, valueBuffer, 0, true);
			printResponse("<response command=\"eval\"\r\n"
					+ " transaction_id=\"" + options.get("-i")
					+ "\" success=\"1\" " + ">\r\n" + valueBuffer
					+ "</response>\r\n" + "");
		}
	}

	private final class PropertySetCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			String name = ((String) options.get("-n"));
			int num = Integer.parseInt((String) options.get("-d"));
			String value = Base64Helper
					.decodeString((String) options.get("--"));
			DBGPDebugFrame fr = cmanager.getStackFrame(num);
			fr.setValue(name, value);
			printResponse("<response command=\"property_set\"\r\n"
					+ " transaction_id=\"" + options.get("-i")
					+ "\" success=\"1\" " + ">\r\n" + "</response>\r\n" + "");
		}
	}

	private final class ContextGetCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			StringBuffer properties = new StringBuffer();
			int level = Integer.parseInt((String) options.get("-d"));
			DBGPDebugFrame stackFrame = cmanager.getStackFrame(level);
			String[] propertyIds = stackFrame.getParametersAndVars();
			for (int a = 0; a < propertyIds.length; a++) {
				String id = propertyIds[a].toString();
				Object value = stackFrame.getValue(a);
				printProperty(id, id, value, properties, 0, true);

			}
			Scriptable this1 = stackFrame.getThis();
			if (this1 != null) {
				String id = "this";
				printProperty(id, id, this1, properties, 0, false);
			}
			printResponse("<response command=\"context_get\"\r\n"
					+ "status=\"starting\"" + " reason=\"ok\""
					+ " transaction_id=\"" + options.get("-i") + "\">\r\n"
					+ properties + "</response>\r\n" + "");
		}
	}

	private final class UpdateBreakPointCommand extends Command {
		void parseAndExecute(String command, HashMap options) {

			String id = (String) options.get("-d");
			String newState = (String) options.get("-s");
			String newLine = (String) options.get("-n");
			String hitValue = (String) options.get("-h");
			String hitCondition = (String) options.get("-o");
			String condEString = (String) options.get("--");
			
			if (condEString!=null)condEString=Base64Helper.decodeString(condEString);
			
			cmanager.updateBreakpoint(id, newState, newLine, hitValue,
					hitCondition,condEString);
			String enabled=newState;
			printResponse("<response command=\"breakpoint_update\"\r\n"
					+ " transaction_id=\"" + options.get("-i") + "\">\r\n"
					+ " id=\"" + id + "\" state=\""+enabled+"\" "
					+ "</response>\r\n" + "");
		}
	}

	private final class RemoveBreakPointCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			cmanager.removeBreakpoint((String) options.get("-d"));
			printResponse("<response command=\"breakpoint_remove\"\r\n"
					+ " transaction_id=\"" + options.get("-i") + "\" />");
		}
	}

	private final class SetBreakPointCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			BreakPoint p = new BreakPoint(options);
			cmanager.registerBreakPoint(p);
			printResponse("<response command=\"breakpoint_set\"\r\n"
					+ " transaction_id=\"" + options.get("-i") + "\" "
					+ " id=\"p" + p.id + "\" state=\"enabled\" > "
					+ "</response>\r\n" + "");
		}
	}

	private final class PropertyGetCommand extends Command {
		void parseAndExecute(String command, HashMap options) {
			String longName = (String) options.get("-n");
			int level = 0;
			String depth = (String) options.get("-d");
			if (depth != null) {
				level = Integer.parseInt(depth);
			}
			StringBuffer properties = new StringBuffer();
			DBGPDebugFrame stackFrame = cmanager.getStackFrame(level);
			Object value = stackFrame.getValue(longName);
			int shName = longName.indexOf('.');
			if (shName == -1)
				shName = longName.length();
			String shortName = longName.substring(0, shName);
			printProperty(shortName, longName, value, properties, 0, true);
			printResponse("<response command=\"property_get\"\r\n"
					+ " transaction_id=\"" + options.get("-i") + "\">\r\n"
					+ properties + "</response>\r\n" + "");
		}
	}

	abstract class Command {
		abstract void parseAndExecute(String command, HashMap options);
	}

	void printResponse(String response) {
		String encodeString = response;
		out.print(encodeString.length());
		out.write(0);
		out.print(encodeString);
		out.write(0);
	}

	DBGPStackManager cmanager;

	public DBGPDebugger(Socket socket, String file, String string, Context ct)
			throws IOException {
		super();
		this.socket = socket;

		cmanager = DBGPStackManager.getManager(ct);
		cmanager.suspend();
		out = new PrintStream(socket.getOutputStream());
		cmanager.setDebugger(this);
		String response = "<init appid=\"APPID\"\r\n" + "      idekey=\""
				+ string + "\"\r\n" + "      session=\"" + string + "\"\r\n"
				+ "      thread=\"THREAD_ID\"\r\n"
				+ "      parent=\"PARENT_APPID\"\r\n"
				+ "      language=\"javascript\"\r\n"
				+ "      protocol_version=\"1.0\"\r\n"
				+ "      fileuri=\"file://" + file + "\"\r\n" + "/>";
		printResponse(response);
		strategies.put("feature_get", new FeatureGetCommand());
		strategies.put("feature_set", new FeatureSetCommand());
		strategies.put("stdin", new StdInCommand());
		strategies.put("stdout", new StdOutCommand());
		strategies.put("stderr", new StdErrCommand());
		strategies.put("run", new RunCommand());
		strategies.put("stop", new StopCommand());
		strategies.put("step_over", new StepOverCommand());
		strategies.put("step_into", new StepIntoCommand());
		strategies.put("step_out", new StepOutCommand());
		strategies.put("breakpoint_set", new SetBreakPointCommand());
		strategies.put("breakpoint_remove", new RemoveBreakPointCommand());
		strategies.put("breakpoint_update", new UpdateBreakPointCommand());
		strategies.put("context_get", new ContextGetCommand());
		strategies.put("property_set", new PropertySetCommand());
		strategies.put("eval", new EvalCommand());
		strategies.put("property_get", new PropertyGetCommand());
		strategies.put("break", new BreakCommand());
		strategies.put("stack_depth", new StackDepthCommand());
		strategies.put("stack_get", new StackGetCommand());
		out.flush();
	}

	protected void printProperty(String id, String fullName, Object value,
			StringBuffer properties, int level, boolean addChilds) {
		boolean hasChilds = false;

		int numC = 0;
		String vlEncoded;
		String name_of_object_class = "";
		String data_type="Object";
		if (value instanceof Function){
			data_type="function";
		}
		else if (value instanceof NativeJavaObject){
			data_type="javaobject";
		}
		else if (value instanceof NativeJavaClass){
			data_type="javaclass";
		}
		else if (value instanceof NativeJavaArray){
			data_type="javaarray";
		}	
		if (value instanceof Scriptable) {
			hasChilds = true;
			StringBuffer stringBuffer = new StringBuffer();
			Scriptable p = (Scriptable) value;
			value = stringBuffer;
			Object[] ids = p.getIds();
			numC = ids.length;
			String nv = p.getClassName();
			name_of_object_class = nv;
			if (p instanceof NativeJavaObject) {

				NativeJavaObject obj = (NativeJavaObject) p;
				
				Object unwrap = obj.unwrap();
				if (unwrap instanceof Class){
					nv=((Class)unwrap).getName();
				}
				else if (unwrap.getClass().isArray()) {
					String string = unwrap.getClass().getName();
					int len=Array.getLength(unwrap);
					int q=string.indexOf('[');					
					if (q!=-1) string=string.substring(0,q);
					int q1=string.indexOf(']');
					nv = string + "[" + len + "]";
					if (q1!=-1)nv+=string.substring(q1);
				} else {
					if (unwrap instanceof String) {
						nv = "JavaString " + '"' + unwrap.toString() + '"';
					} else {
						String string = unwrap.toString();		
						
						nv = unwrap.getClass().getName()+ "(" + string + ")";
					}
				}

			}

			stringBuffer.append(Base64Helper.encodeString(nv));
			if (addChilds) {
				for (int a = 0; a < ids.length; a++) {
					Object pvalue;
					if (ids[a] instanceof Integer) {
						pvalue = p.get(((Integer) ids[a]).intValue(), p);
					} else
						pvalue = p.get(ids[a].toString(), p);
					printProperty(ids[a].toString(), fullName + "." + ids[a],
							pvalue, stringBuffer, level + 1, false);
				}
			}
			vlEncoded = stringBuffer.toString();
		} else {
			if (!(value instanceof Undefined)) {
				if (value == UniqueTag.NOT_FOUND) {
					vlEncoded = "";
				} else
					vlEncoded = Base64Helper.encodeString(value != null ? value
							.toString() : "null");
			} else {
				vlEncoded = Base64Helper.encodeString("Undefined");
			}
			if (value != null)
				name_of_object_class = value.getClass().getName();
		}
					
		properties.append("<property\r\n" + "    name=\"" + id + "\"\r\n"
				+ "    fullname=\"" + fullName + "\"\r\n"
				+ "    type=\""+data_type+"\"\r\n" + "    classname=\""
				+ name_of_object_class + "\"\r\n" + "    constant=\"0\"\r\n"
				+ "    children=\"" + (hasChilds ? 1 : 0) + "\"\r\n"
				+ "    encoding=\"base64\"\r\n" + "    numchildren=\"" + numC
				+ "\">\r\n" + vlEncoded + "</property>\r\n");
	}

	public void run() {
		try {
			DataInputStream ds = new DataInputStream(socket.getInputStream());
			StringBuffer buf = new StringBuffer();
			Context.enter();
			while (ds.available() >= 0) {
				int c = ds.read();
				if (c < 0)
					break;
				if (c < 32) {
					String s = buf.toString();

					int indexOf = s.indexOf(' ');
					if (indexOf != -1)

					{
						String commandId = buf.substring(0, indexOf);
						Command object = (Command) strategies.get(commandId);

						HashMap options = new HashMap();

						String result = buf.substring(indexOf + 1);
						String[] split = result.split(" ");

						try {
							for (int a = 0; a < split.length; a++) {
								options.put(split[a], split[++a]);
							}
						} catch (Exception e) {

						}
						object.parseAndExecute(result, options);
					}
					buf = new StringBuffer();
				} else {
					buf.append((char) c);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public DebugFrame getFrame(Context cx, DebuggableScript fnOrScript) {
		return new DBGPDebugFrame(cx, fnOrScript);
	}

	public void handleCompilationDone(Context cx, DebuggableScript fnOrScript,
			String source) {
	}

	public void update(Observable arg0, Object arg1) {
		if (runTransctionId != null)
			printResponse("<response command=\"run\"\r\n" + "status=\"break\""
					+ " reason=\"ok\"" + " transaction_id=\"" + runTransctionId
					+ "\">\r\n" + "</response>\r\n" + "");
	}

	public void notifyEnd() {
		printResponse("<response command=\"run\"\r\n" + "status=\"stopped\""
				+ " reason=\"ok\"" + " transaction_id=\"" + runTransctionId
				+ "\">\r\n" + "</response>\r\n" + "");
		System.exit(0);
	}
}

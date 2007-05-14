package org.eclipse.dltk.javascript.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptVariable;
import org.eclipse.dltk.debug.ui.ScriptDebugImageDescriptor;
import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.dltk.internal.debug.ui.DLTKDebugImages;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;

public class JavaScriptDebugModelPresentation extends
		ScriptDebugModelPresentation {
	private static final String JS_EDITOR_ID = "org.eclipse.dltk.javascript.ui.editor.JavascriptEditor";

	private static final String MAIN_THREAD_NAME = "Main thread";

	protected String getThreadText(IScriptThread thread) {
		return MAIN_THREAD_NAME;
	}

	

	static {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				DLTKPluginImages
						.get(DLTKDebugImages.IMG_OBJS_CONTENDED_MONITOR);
			}

		});
	}
	static ImageRegistry registry=new ImageRegistry();
	
	public JavaScriptDebugModelPresentation() {
		
	}

	public Image getImage(Object element) {
		if (element instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint ll = (IScriptMethodEntryBreakpoint) element;
			int flags=0;
			if (ll.shouldBreakOnEntry())flags|=ScriptDebugImageDescriptor.ENTRY;
			if (ll.shouldBreakOnExit())flags|=ScriptDebugImageDescriptor.EXIT;
			try {
				if (flags==0)return DebugUITools.getImage(IDebugUIConstants.IMG_OBJS_BREAKPOINT_DISABLED);		
				if (ll.isEnabled())
				{
					String key=flags+"enabled";
					Image image=registry.get(key);
					if (image==null){
						registry.put(key,new ScriptDebugImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_BREAKPOINT), 
								flags));
						return registry.get(key);
					}
					return image;
				}
				else{
					String key=flags+"disabled";
					Image image=registry.get(key);
					if (image==null){
						registry.put(key,new ScriptDebugImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_BREAKPOINT_DISABLED), 
								flags));
						return registry.get(key);
					}
					return image;
				}
			} catch (CoreException e) {
				DLTKDebugPlugin.log(e);
				return super.getImage(element);
			}
		}
		// TODO Auto-generated method stub
		if (element instanceof IScriptVariable) {
			IScriptVariable v = (IScriptVariable) element;
			String typeString = v.getTypeString();
			if (typeString.equals("function"))
				return DLTKPluginImages
						.get(DLTKPluginImages.IMG_METHOD_PRIVATE);
			if (typeString.equals("javaclass"))
				return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_CLASS);
			if (typeString.equals("javaobject"))
				return DLTKPluginImages
						.get(DLTKPluginImages.IMG_METHOD_PROTECTED);
			if (typeString.equals("javaarray"))
				return DLTKPluginImages
						.get(DLTKPluginImages.IMG_METHOD_DEFAULT);
			String fullName = v.getFullName();
			if (fullName.indexOf('.') >= 0 || (fullName.equals("this")))
				return DLTKPluginImages.get(DLTKPluginImages.IMG_METHOD_PUBLIC);
			else
				return DLTKDebugImages
						.get(DLTKDebugImages.IMG_OBJS_LOCAL_VARIABLE);
		}
		return super.getImage(element);
	}

	public String getEditorId(IEditorInput input, Object element) {
		return JS_EDITOR_ID;
	}
}

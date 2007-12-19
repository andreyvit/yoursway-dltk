package org.eclipse.dltk.ruby.internal.debug;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.debug.core.ISmartStepEvaluator;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.launching.sourcelookup.ScriptSourceLookupDirector;

public class RubySmartStepEvaluator implements ISmartStepEvaluator {
	public static class CodeModel {
		private String[] codeLines;

		private int[] codeLineLengths;

		public CodeModel(String code) {
			this.codeLines = code.split("\n");
			int count = this.codeLines.length;

			this.codeLineLengths = new int[count];

			int sum = 0;
			for (int i = 0; i < count; ++i) {
				this.codeLineLengths[i] = sum;
				sum += this.codeLines[i].length() + 1;
			}
		}

		public int[] getBounds(int lineNumber) {
			String codeLine = this.codeLines[lineNumber];

			int start = this.codeLineLengths[lineNumber];
			int end = start + codeLine.length();

			return new int[] { start, end };
		}
	}

	public RubySmartStepEvaluator() {
	}

	public boolean skipSuspend(String[] filters, IScriptThread thread) {
		IDebugTarget debugTarget = thread.getDebugTarget();
		ScriptSourceLookupDirector lookup = new ScriptSourceLookupDirector();
		try {
			lookup.initializeDefaults(debugTarget.getLaunch()
					.getLaunchConfiguration());
		} catch (CoreException e1) {
			if (DLTKCore.DEBUG) {
				e1.printStackTrace();
			}
			return false;
		}
		try {
			IStackFrame topStackFrame = thread.getTopStackFrame();
			Object sourceElement = lookup.getSourceElement(topStackFrame);
			if (sourceElement instanceof IFile) {
				ISourceModule module = (ISourceModule) DLTKCore
						.create((IFile) sourceElement);
				CodeModel codeModel = new CodeModel(module.getSource());
				if (module.exists()) {
					int[] bounds = codeModel.getBounds(topStackFrame
							.getLineNumber());
					IModelElement elementAt = module
							.getElementAt(bounds[0] + 1);
					if (elementAt != null) {
						IModelElement ancestor = elementAt
								.getAncestor(IModelElement.TYPE);
						if (ancestor != null) {
							IType type = (IType) ancestor;
							String typeQualifiedName = type.getTypeQualifiedName(".");
							if( filter(typeQualifiedName, filters ) ) {
								return false;
							}
						}
					}
				}
			}

		} catch (DebugException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private boolean filter(String typeQualifiedName, String[] filters) {
		for (int i = 0; i < filters.length; i++) {
			if( filters[i].equals(typeQualifiedName)) {
				return true;
			}
		}
		return false;
	}

}

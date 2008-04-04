package org.eclipse.dltk.tcl.internal.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceElementParserExtension;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.core.TclParseUtil.CodeModel;

public class TclSourceElementParser extends AbstractSourceElementParser
		implements ISourceElementParserExtension {

	private IScriptProject scriptProject = null;

	/*
	 * @see org.eclipse.dltk.core.AbstractSourceElementParser#createVisitor()
	 */
	protected SourceElementRequestVisitor createVisitor() {
		return new TclSourceElementRequestVisitor(this.getRequestor(), this
				.getProblemReporter());
	}

	public void parseSourceModule(char[] contents, ISourceModuleInfo astCache,
			char[] filename) {

		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(filename, contents, getNatureId(),
						getProblemReporter(), astCache);

		TclSourceElementRequestVisitor requestor = (TclSourceElementRequestVisitor) createVisitor();
		requestor.setScriptProject(null);
		if (getProblemReporter() != null) {
			if (this.scriptProject != null) {
				boolean markersCleaned = getProblemReporter()
						.isMarkersCleaned();
				if (markersCleaned) {
					CodeModel model = new CodeModel(new String(contents));
					requestor.setCodeModel(model);
					requestor.setScriptProject(this.scriptProject);
				}
			}
		}
		try {
			moduleDeclaration.traverse(requestor);
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * @see org.eclipse.dltk.core.AbstractSourceElementParser#getNatureId()
	 */
	protected String getNatureId() {
		return TclNature.NATURE_ID;
	}

	public void setScriptProject(IScriptProject project) {
		this.scriptProject = project;
	}
}

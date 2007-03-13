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
 * Copyright (C) 2004 Jan Arne Petersen <jpetersen@uni-bonn.de>
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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.compiler.DefaultProblem;
import org.eclipse.dltk.compiler.IProblem;
import org.eclipse.dltk.compiler.IProblemReporter;
import org.eclipse.dltk.compiler.ProblemSeverities;
import org.eclipse.dltk.ruby.internal.parser.Activator;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.lexer.yacc.SourcePosition;

/** 
 *
 */
public class DLTKRubyWarnings implements IDLTKRubyWarnings {

    private final IProblemReporter problemReporter;

	public DLTKRubyWarnings(IProblemReporter problemReporter) {
		this.problemReporter = problemReporter;
    }

    public void warn(ISourcePosition position, String message) {
		DefaultProblem problem = new DefaultProblem(position.getFile(),
				message, IProblem.Unclassified, new String[0], ProblemSeverities.Warning, 
				position.getStartOffset(), position.getEndOffset(), position.getStartLine()
				);
		try {
			problemReporter.reportProblem(problem);
		} catch (CoreException e) {
			Activator.log(e);
		}
    }

    public boolean isVerbose() {
    	return false;
    }

    public void warning(ISourcePosition position, String message) {
        if (isVerbose()) {
    		DefaultProblem problem = new DefaultProblem(position.getFile(),
    				message, IProblem.Unclassified, new String[0], ProblemSeverities.Warning, 
    				position.getStartOffset(), position.getEndOffset(), position.getStartLine()
    				);
    		try {
    			problemReporter.reportProblem(problem);
    		} catch (CoreException e) {
    			Activator.log(e);
    		}
        }
    }

	public void error(ISourcePosition position, String message) {
		DefaultProblem problem = new DefaultProblem(position.getFile(),
				message, IProblem.Unclassified, new String[0], ProblemSeverities.Error, 
				position.getStartOffset(), position.getEndOffset(), position.getStartLine()
				);
		try {
			problemReporter.reportProblem(problem);
		} catch (CoreException e) {
			Activator.log(e);
		}
	}

	public void warn(String message) {
		warn(new SourcePosition(), message);		
	}

	public void warning(String message) {
		warning(new SourcePosition(), message);
	}
}

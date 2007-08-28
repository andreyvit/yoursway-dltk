package org.eclipse.dltk.python.activestatedebugger;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;

/**
 * Factory responsible for creating instances of Active State's ruby
 * debugging engine.
 */
public class PythonActiveStateDebuggerRunnerFactory implements IInterpreterRunnerFactory
{
    /*
     * @see org.eclipse.dltk.launching.IInterpreterRunnerFactory#createRunner(org.eclipse.dltk.launching.IInterpreterInstall)
     */
    public IInterpreterRunner createRunner(IInterpreterInstall install)
    {
        return new PythonActiveStateDebuggerRunner(install);
    }
    
}

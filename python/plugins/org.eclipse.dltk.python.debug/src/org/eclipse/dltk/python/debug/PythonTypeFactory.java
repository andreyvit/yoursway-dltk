package org.eclipse.dltk.python.debug;

import org.eclipse.dltk.debug.core.model.ComplexScriptType;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptTypeFactory;

public class PythonTypeFactory implements IScriptTypeFactory
{
    public IScriptType buildType(String type)
    {
        // TODO: implement the right way :)
        return new ComplexScriptType(type);
    }

}

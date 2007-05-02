package org.eclipse.dltk.ruby.debug.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.debug.core.DebugException;

public class RubyVariableUpdater {
	private static Map mapNameToVariable(RubyVariable[] variables)
			throws DebugException {
		Map map = new HashMap();

		for (int i = 0; i < variables.length; ++i) {
			RubyVariable variable = variables[i];
			map.put(variable.getName(), variable);
		}

		return map;
	}

	public static void update(RubyVariable[] oldVariables,
			RubyVariable[] newVariables) throws DebugException {
		Map map = mapNameToVariable(oldVariables);

		for (int i = 0; i < newVariables.length; ++i) {
			RubyVariable newVariable = newVariables[i];

			RubyVariable oldVariable = (RubyVariable) map.get(newVariable
					.getName());

			if (oldVariable != null) {
				final String oldValue = oldVariable.getValueString();
				final String newValue = newVariable.getValueString();
				
				//newVariables[i] = oldVariable.clone(newVariable);
					
				//if (!oldValue.equals(newValue)) {					
					//newVariables[i].setModified();
				//}
			}
		}
	}
}

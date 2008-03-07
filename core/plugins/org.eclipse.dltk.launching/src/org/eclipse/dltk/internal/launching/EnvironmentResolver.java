package org.eclipse.dltk.internal.launching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.launching.EnvironmentVariable;

public final class EnvironmentResolver {
	private static class REnvironmentVariable {
		EnvironmentVariable var;
		Set dependencies = new HashSet();

		public REnvironmentVariable(EnvironmentVariable var) {
			this.var = var;
		}
	}

	/*
	 * Resolves specified set of environment variables with system environment
	 */
	public static EnvironmentVariable[] resolve(Map penv,
			EnvironmentVariable[] variables) {
		if (variables == null) {
			return null;
		}
		Map env = new HashMap();
		Map selfDep = new HashMap();
		for (Iterator iterator = penv.keySet().iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			String value = (String) penv.get(name);
			env.put(name, value);
		}

		for (int i = 0; i < variables.length; i++) {
			String name = variables[i].getName();
			if (env.containsKey(name)) {
				selfDep.put(name, env.get(name));
				env.remove(name);
			}
		}
		Map resolved = new HashMap();
		List result = new ArrayList();
		// 1) replace all top level environment variables
		List unresolved = new ArrayList();
		for (int i = 0; i < variables.length; i++) {
			REnvironmentVariable var = new REnvironmentVariable(
					new EnvironmentVariable(variables[i]));
			fillDependencies(var, variables);
			unresolved.add(var);
		}
		// For be shure we exit from while
		int maxCycles = 1000;
		while (unresolved.size() > 0) {
			maxCycles--;
			if( maxCycles < 0 ) {
				break;
			}
			for (Iterator iterator = unresolved.iterator(); iterator.hasNext();) {
				REnvironmentVariable var = (REnvironmentVariable) iterator.next();;
				if (isResolved(var.var)) {
					result.add(var.var);
					resolved.put(var.var.getName(), var.var.getValue());
					iterator.remove();
				} else {
					if (isCyclick(var, unresolved)) {
						// Resolve self cycles to environment
						if (isSelfCyclick(var)) {
							resolveVariable(var, env);
							resolveVariable(var, selfDep);
							if (isResolved(var.var)) {
								continue;
							}
						}
						iterator.remove();
						continue;
					}
					resolveVariable(var, resolved);
					resolveVariable(var, env);
					if (isResolved(var.var)) {
						continue;
					}
					if (isUnresolvable(var, unresolved)) {
						iterator.remove();
					}
				}
			}
		}

		return (EnvironmentVariable[]) result
				.toArray(new EnvironmentVariable[result.size()]);
	}

	private static boolean isSelfCyclick(REnvironmentVariable var) {
		if (var.dependencies.size() == 0) {
			return false;
		}
		if (var.dependencies.contains(var.var.getName())) {
			return true;
		}
		return false;
	}

	private static void fillDependencies(REnvironmentVariable var,
			EnvironmentVariable[] variables) {
		for (int j = 0; j < variables.length; j++) {
			if (containVar(var.var, variables[j].getName())) {
				var.dependencies.add(variables[j].getName());
			}
		}
	}

	private static boolean isUnresolvable(REnvironmentVariable var,
			List unresolved) {
		EnvironmentVariable t = var.var;
		while (true) {
			boolean step = false;
			for (Iterator iterator = unresolved.iterator(); iterator.hasNext();) {
				REnvironmentVariable rvar = (REnvironmentVariable) iterator
						.next();
				if (!rvar.var.getName().equals(t.getName())
						&& containVar(t, rvar.var.getName())) {
					t = resolveVariable(t, rvar.var.getName(), rvar.var
							.getValue());
					step = true;
				}
			}
			if (!step) {
				break;
			}
		}
		if (!isResolved(t)) {
			return true;
		}
		return false;
	}

	private static EnvironmentVariable resolveVariable(EnvironmentVariable var,
			String name, String value) {
		String result = var.getValue();
		String pattern = "$" + name;
		if (value.indexOf(pattern) != -1) {
			return null;
		}
		int pos = result.indexOf(pattern);
		while (pos != -1) {
			result = result.substring(0, pos) + value
					+ result.substring(pos + pattern.length());
			pos = result.indexOf(pattern);
		}
		return new EnvironmentVariable(var.getName(), result);
	}

	private static boolean isCyclick(REnvironmentVariable var, List unresolved) {
		// Detect direct cycles
		if (var.dependencies.size() == 0) {
			return false;
		}
		for (Iterator iterator2 = unresolved.iterator(); iterator2.hasNext();) {
			REnvironmentVariable env2 = (REnvironmentVariable) iterator2.next();
			if (var.dependencies.contains(env2.var.getName())
					&& env2.dependencies.contains(var.var.getName())) {
				return true;
			}
		}
		return false;
	}

	private static void resolveVariable(REnvironmentVariable var, Map env) {
		EnvironmentVariable v = var.var;
		for (Iterator iterator = env.keySet().iterator(); iterator.hasNext();) {
			String varName = (String) iterator.next();
			if (containVar(v, varName)) {
				v = resolveVariable(v, varName, (String) env.get(varName));
			}
		}
		var.var = v;
	}

	public static boolean isResolved(EnvironmentVariable var) {
		if (var == null) {
			throw new IllegalArgumentException();
		}
		String name = var.getValue();
		if (name.indexOf("$") == -1) {
			return true;
		}
		return false;
	}

	public static boolean containVar(EnvironmentVariable var, String vName) {
		if (var == null) {
			throw new IllegalArgumentException();
		}
		String name = var.getValue();
		if (name.indexOf("$" + vName) != -1) {
			return true;
		}
		return false;
	}
}

package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.ti.IGoalEvaluatorFactory;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class RubyEvaluatorFactory implements IGoalEvaluatorFactory {

	private static final String GOAL_EVALUATOR_FACTORIES_EXT = "org.eclipse.dltk.ruby.core.goalEvaluatorFactories"; //$NON-NLS-1$
	private final static FactoryInfo[] f;

	private static class FactoryInfo {
		int priority;
		IGoalEvaluatorFactory factory;

		public FactoryInfo(int priority, IGoalEvaluatorFactory factory) {
			super();
			this.priority = priority;
			this.factory = factory;
		}
	}

	private static int getPriority(IConfigurationElement element) {
		String priority = element.getAttribute("priority"); //$NON-NLS-1$
		if (priority == null) {
			return 0;
		}
		try {
			int parseInt = Integer.parseInt(priority);
			return parseInt;
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	static {
		List factories = new ArrayList();
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(GOAL_EVALUATOR_FACTORIES_EXT);
		for (int i = 0; i < elements.length; i++) {
			IConfigurationElement element = elements[i];
			try {
				int priority = getPriority(element);
				IGoalEvaluatorFactory factory = (IGoalEvaluatorFactory) element
						.createExecutableExtension("class"); //$NON-NLS-1$
				if (factory != null)
					factories.add(new FactoryInfo(priority, factory));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		f = (FactoryInfo[]) factories.toArray(new FactoryInfo[factories.size()]);
		Arrays.sort(f, new Comparator() {

			public int compare(Object arg0, Object arg1) {
				FactoryInfo f1 = (FactoryInfo) arg0;
				FactoryInfo f2 = (FactoryInfo) arg1;
				return f2.priority - f1.priority; 
			}
			
		});
	}

	public GoalEvaluator createEvaluator(IGoal goal) {
		if (f == null)
			return null;
		for (int i = 0; i < f.length; i++) {
			GoalEvaluator evaluator = f[i].factory.createEvaluator(goal);
			if (evaluator != null)
				return evaluator;
		}
		return null;
	}

}

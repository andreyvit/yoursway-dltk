/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal;

import org.eclipse.dltk.typeinference.IDependentTypedElement;
import org.eclipse.dltk.typeinference.ITypeDescriptor;

public interface IReparsableElementTypeEvaluator {

	ITypeDescriptor reparse(IDependentTypedElement element, IContext context);

}
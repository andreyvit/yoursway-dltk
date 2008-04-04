/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IModelElement;

public class ReorgDestinationFactory {

	private static class Destination implements IReorgDestination {

		private final Object fDestination;
		private final int fLocation;

		public Destination(Object destination, int location) {
			Assert.isNotNull(destination);
			Assert.isLegal(location == LOCATION_AFTER
					|| location == LOCATION_BEFORE || location == LOCATION_ON);

			fDestination = destination;
			fLocation = location;
		}

		/**
		 * {@inheritDoc}
		 */
		public Object getDestination() {
			return fDestination;
		}

		/**
		 * {@inheritDoc}
		 */
		public int getLocation() {
			return fLocation;
		}

	}

	static final class ResourceDestination extends Destination {

		private ResourceDestination(IResource destination, int location) {
			super(destination, location);
		}

		public IResource getResource() {
			return (IResource) getDestination();
		}

	}

	static final class ModelElementDestination extends Destination {

		private ModelElementDestination(IModelElement destination, int location) {
			super(destination, location);
		}

		public IModelElement getJavaElement() {
			return (IModelElement) getDestination();
		}

	}

	/**
	 * Wrap the given object into a destination
	 * 
	 * @param destination
	 *            the object to wrap
	 * @return a reorg destination if possible reorg destination or <b>null</b>
	 *         otherwise
	 */
	public static IReorgDestination createDestination(Object destination) {
		return createDestination(destination, IReorgDestination.LOCATION_ON);
	}

	public static IReorgDestination createDestination(Object destination,
			int location) {
		if (destination instanceof IModelElement) {
			return new ModelElementDestination((IModelElement) destination,
					location);
		}
		if (destination instanceof IResource) {
			return new ResourceDestination((IResource) destination, location);
		}

		return null;
	}

}

/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;


public class ModelElementTransfer extends ByteArrayTransfer {

	/**
	 * Singleton instance.
	 */
	private static final ModelElementTransfer fInstance= new ModelElementTransfer();

	// Create a unique ID to make sure that different Eclipse
	// applications use different "types" of <code>ScriptElementTransfer</code>
	private static final String TYPE_NAME= "model-element-transfer-format:" + System.currentTimeMillis() + ":" + fInstance.hashCode(); //$NON-NLS-2$//$NON-NLS-1$

	private static final int TYPEID= registerType(TYPE_NAME);

	private ModelElementTransfer() {
	}

	/**
	 * Returns the singleton instance.
	 *
	 * @return the singleton instance
	 */
	public static ModelElementTransfer getInstance() {
		return fInstance;
	}

	/* (non-Javadoc)
	 * Method declared on Transfer.
	 */
	protected int[] getTypeIds() {
		return new int[] { TYPEID };
	}
	/* (non-Javadoc)
	 * Returns the type names.
	 *
	 * @return the list of type names
	 */
	protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.dnd.Transfer#javaToNative(java.lang.Object, org.eclipse.swt.dnd.TransferData)
	 */
	protected void javaToNative(Object data, TransferData transferData) {
		if (!(data instanceof IModelElement[]))
			return;

		IModelElement[] modelElements= (IModelElement[]) data;
		/*
		 * The element serialization format is:
		 *  (int) number of element
		 * Then, the following for each element:
		 *  (String) handle identifier
		 */

		try {
			ByteArrayOutputStream out= new ByteArrayOutputStream();
			DataOutputStream dataOut= new DataOutputStream(out);

			//write the number of elements
			dataOut.writeInt(modelElements.length);

			//write each element
			for (int i= 0; i < modelElements.length; i++) {
				writeScriptElement(dataOut, modelElements[i]);
			}

			//cleanup
			dataOut.close();
			out.close();
			byte[] bytes= out.toByteArray();
			super.javaToNative(bytes, transferData);
		} catch (IOException e) {
			//it's best to send nothing if there were problems
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.dnd.Transfer#nativeToScript(org.eclipse.swt.dnd.TransferData)
	 */
	protected Object nativeToJava(TransferData transferData) {
		/*
		 * The element serialization format is:
		 *  (int) number of element
		 * Then, the following for each element:
		 *  (String) handle identifier
		 */

		byte[] bytes= (byte[]) super.nativeToJava(transferData);
		if (bytes == null)
			return null;
		DataInputStream in= new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			int count= in.readInt();
			IModelElement[] results= new IModelElement[count];
			for (int i= 0; i < count; i++) {
				results[i]= readScriptElement(in);
			}
			return results;
		} catch (IOException e) {
			return null;
		}
	}

	private IModelElement readScriptElement(DataInputStream dataIn) throws IOException {
		String handleIdentifier= dataIn.readUTF();
		return DLTKCore.create(handleIdentifier);
	}

	private static void writeScriptElement(DataOutputStream dataOut, IModelElement element) throws IOException {
		dataOut.writeUTF(element.getHandleIdentifier());
	}
}

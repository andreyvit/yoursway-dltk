/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.builder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.compiler.util.SimpleLookupTable;

public class State {
	// NOTE: this state cannot contain types that are not defined in this
	// project

	String scriptProjectName;

	int buildNumber;
	long lastStructuralBuildTime;
	SimpleLookupTable structuralBuildTimes;

	public static final byte VERSION = 0x0015; // changed access rule
	// presentation

	Set externalFolderLocations = new HashSet();

	boolean noCleanExternalFolders = false;

	static final byte SOURCE_FOLDER = 1;
	static final byte BINARY_FOLDER = 2;
	static final byte EXTERNAL_JAR = 3;
	static final byte INTERNAL_JAR = 4;

	private State() {
		// constructor with no argument
	}

	public State(IProject project) {
		this.scriptProjectName = project.getName();

		this.buildNumber = 0; // indicates a full build
		this.lastStructuralBuildTime = System.currentTimeMillis();
		this.structuralBuildTimes = new SimpleLookupTable(3);
		this.noCleanExternalFolders = false;
	}

	protected State(ScriptBuilder scriptBuilder) {
		this.scriptProjectName = scriptBuilder.currentProject.getName();

		this.buildNumber = 0; // indicates a full build
		this.lastStructuralBuildTime = System.currentTimeMillis();
		this.structuralBuildTimes = new SimpleLookupTable(3);
		this.noCleanExternalFolders = false;
	}

	void copyFrom(State lastState) {
		this.buildNumber = lastState.buildNumber + 1;
		this.lastStructuralBuildTime = lastState.lastStructuralBuildTime;
		this.structuralBuildTimes = lastState.structuralBuildTimes;

		this.externalFolderLocations.addAll(lastState.externalFolderLocations);
		this.noCleanExternalFolders = false;
	}

	public Set getExternalFolders() {
		return this.externalFolderLocations;
	}

	static State read(IProject project, DataInputStream in) throws IOException {
		if (ScriptBuilder.DEBUG)
			System.out.println("About to read state " + project.getName()); //$NON-NLS-1$
		if (VERSION != in.readByte()) {
			if (ScriptBuilder.DEBUG)
				System.out
						.println("Found non-compatible state version... answered null for " + project.getName()); //$NON-NLS-1$
			return null;
		}

		State newState = new State();
		newState.scriptProjectName = in.readUTF();
		if (!project.getName().equals(newState.scriptProjectName)) {
			if (ScriptBuilder.DEBUG)
				System.out
						.println("Project's name does not match... answered null"); //$NON-NLS-1$
			return null;
		}
		newState.buildNumber = in.readInt();
		newState.lastStructuralBuildTime = in.readLong();

		int length = in.readInt();
		newState.externalFolderLocations.clear();
		for (int i = 0; i < length; i++) {
			String folderName;
			if ((folderName = in.readUTF()).length() > 0)
				newState.externalFolderLocations.add(new Path(folderName));
		}
		try {
			boolean state = in.readBoolean();
			newState.noCleanExternalFolders = state;
		} catch (EOFException e) {
			newState.noCleanExternalFolders = false;
		}
		if (ScriptBuilder.DEBUG)
			System.out
					.println("Successfully read state for " + newState.scriptProjectName); //$NON-NLS-1$
		return newState;
	}

	// private static char[] readName(DataInputStream in) throws IOException {
	// int nLength = in.readInt();
	// char[] name = new char[nLength];
	// for (int j = 0; j < nLength; j++)
	// name[j] = in.readChar();
	// return name;
	// }

	// private static char[][] readNames(DataInputStream in) throws IOException
	// {
	// int length = in.readInt();
	// char[][] names = new char[length][];
	// for (int i = 0; i < length; i++)
	// names[i] = readName(in);
	// return names;
	// }

	void tagAsNoopBuild() {
		this.buildNumber = -1; // tag the project since it has no source
		// folders and can be skipped
	}

	boolean wasNoopBuild() {
		return buildNumber == -1;
	}

	boolean wasStructurallyChanged(IProject prereqProject, State prereqState) {
		if (prereqState != null) {
			Object o = structuralBuildTimes.get(prereqProject.getName());
			long previous = o == null ? 0 : ((Long) o).longValue();
			if (previous == prereqState.lastStructuralBuildTime)
				return false;
		}
		return true;
	}

	void write(DataOutputStream out) throws IOException {
		int length;

		/*
		 * byte VERSION String project name int build number int last structural
		 * build number
		 */
		out.writeByte(VERSION);
		out.writeUTF(scriptProjectName);
		out.writeInt(buildNumber);
		out.writeLong(lastStructuralBuildTime);

		/*
		 * ClasspathMultiDirectory[] int id String path(s)
		 */
		out.writeInt(length = externalFolderLocations.size());
		for (Iterator iterator = this.externalFolderLocations.iterator(); iterator
				.hasNext();) {
			IPath path = (IPath) iterator.next();
			out.writeUTF(path.toString());
		}
		out.writeBoolean(this.noCleanExternalFolders);
	}

	/**
	 * Returns a string representation of the receiver.
	 */
	public String toString() {
		return "State for " + scriptProjectName //$NON-NLS-1$
				+ " (#" + buildNumber //$NON-NLS-1$
				+ " @ " + new Date(lastStructuralBuildTime) //$NON-NLS-1$
				+ ")"; //$NON-NLS-1$
	}

	/**
	 * 
	 */
	public void setNoCleanExternalFolders() {
		this.noCleanExternalFolders = true;
	}
}

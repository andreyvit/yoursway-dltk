/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.mapping.RemoteResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.browsing.LogicalPackage;
import org.eclipse.dltk.ui.DLTKUIPlugin;


/**
 * An abstract super class to describe mappings from a model element to a
 * set of resources. The class also provides factory methods to create
 * resource mappings.
 * 
	 *
 */
public abstract class DLTKElementResourceMapping extends ResourceMapping {
	
	protected DLTKElementResourceMapping() {
	}
	
	public IModelElement getModelElement() {
		Object o= getModelObject();
		if (o instanceof IModelElement)
			return (IModelElement)o;
		return null;
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof DLTKElementResourceMapping))
			return false;
		return getModelElement().equals(((DLTKElementResourceMapping)obj).getModelElement());
	}
	
	public int hashCode() {
		IModelElement modelElement= getModelElement();
		if (modelElement == null)
			return super.hashCode();
		
		return modelElement.hashCode();
	}
	
	public String getModelProviderId() {
		return DLTKModelProvider.DLTK_MODEL_PROVIDER_ID;
	}
	
	public boolean contains(ResourceMapping mapping) {
		if (mapping instanceof DLTKElementResourceMapping) {
			DLTKElementResourceMapping javaMapping = (DLTKElementResourceMapping) mapping;
			IModelElement element = getModelElement();
			IModelElement other = javaMapping.getModelElement();
			if (other != null && element != null)
				return element.getPath().isPrefixOf(other.getPath());
		}
		return false;
	}
	
	//---- the factory code ---------------------------------------------------------------
	
	private static final class ScriptModelResourceMapping extends DLTKElementResourceMapping {
		private final IScriptModel fModel;
		private ScriptModelResourceMapping(IScriptModel model) {
			Assert.isNotNull(model);
			fModel= model;
		}
		public Object getModelObject() {
			return fModel;
		}
		public IProject[] getProjects() {
			IDLTKProject[] projects= null;
			try {
				projects= fModel.getScriptProjects();
			} catch (ModelException e) {
				DLTKUIPlugin.log(e);
				return new IProject[0];
			}
			IProject[] result= new IProject[projects.length];
			for (int i= 0; i < projects.length; i++) {
				result[i]= projects[i].getProject();
			}
			return result;
		}
		public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
			IDLTKProject[] projects= fModel.getScriptProjects();
			ResourceTraversal[] result= new ResourceTraversal[projects.length];
			for (int i= 0; i < projects.length; i++) {
				result[i]= new ResourceTraversal(new IResource[] {projects[i].getProject()}, IResource.DEPTH_INFINITE, 0);
			}
			return result;
		}
	}
	
	private static final class ScriptProjectResourceMapping extends DLTKElementResourceMapping {
		private final IDLTKProject fProject;
		private ScriptProjectResourceMapping(IDLTKProject project) {
			Assert.isNotNull(project);
			fProject= project;
		}
		public Object getModelObject() {
			return fProject;
		}
		public IProject[] getProjects() {
			return new IProject[] {fProject.getProject() };
		}
		public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
			return new ResourceTraversal[] {
				new ResourceTraversal(new IResource[] {fProject.getProject()}, IResource.DEPTH_INFINITE, 0)
			};
		}
	}
	
	private static final class PackageFragementRootResourceMapping extends DLTKElementResourceMapping {
		private final IProjectFragment fRoot;
		private PackageFragementRootResourceMapping(IProjectFragment root) {
			Assert.isNotNull(root);
			fRoot= root;
		}
		public Object getModelObject() {
			return fRoot;
		}
		public IProject[] getProjects() {
			return new IProject[] {fRoot.getScriptProject().getProject() };
		}
		public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
			return new ResourceTraversal[] {
				new ResourceTraversal(new IResource[] {fRoot.getResource()}, IResource.DEPTH_INFINITE, 0)
			};
		}
	}
	
	private static final class LocalPackageFragementTraversal extends ResourceTraversal {
		private final IScriptFolder fPack;
		public LocalPackageFragementTraversal(IScriptFolder pack) throws CoreException {
			super(new IResource[] {pack.getResource()}, IResource.DEPTH_ONE, 0);
			fPack= pack;
		}
		public void accept(IResourceVisitor visitor) throws CoreException {
			IFile[] files= getPackageContent(fPack);
			final IResource resource= fPack.getResource();
			if (resource != null)
				visitor.visit(resource);
			for (int i= 0; i < files.length; i++) {
				visitor.visit(files[i]);
			}
		}
	}
	
	private static final class ScriptFolderResourceMapping extends DLTKElementResourceMapping {
		private final IScriptFolder fPack;
		private ScriptFolderResourceMapping(IScriptFolder pack) {
			Assert.isNotNull(pack);
			fPack= pack;
		}
		public Object getModelObject() {
			return fPack;
		}
		public IProject[] getProjects() {
			return new IProject[] { fPack.getScriptProject().getProject() };
		}
		public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
			if (context instanceof RemoteResourceMappingContext) {
				return new ResourceTraversal[] {
					new ResourceTraversal(new IResource[] {fPack.getResource()}, IResource.DEPTH_ONE, 0)
				};
			} else {
				return new ResourceTraversal[] { new LocalPackageFragementTraversal(fPack) };
			}
		}
		public void accept(ResourceMappingContext context, IResourceVisitor visitor, IProgressMonitor monitor) throws CoreException {
			if (context instanceof RemoteResourceMappingContext) {
				super.accept(context, visitor, monitor);
			} else {
				// We assume a local context.
				IFile[] files= getPackageContent(fPack);
				if (monitor == null)
					monitor= new NullProgressMonitor();
				monitor.beginTask("", files.length + 1); //$NON-NLS-1$
				final IResource resource= fPack.getResource();
				if (resource != null)
					visitor.visit(resource);
				monitor.worked(1);
				for (int i= 0; i < files.length; i++) {
					visitor.visit(files[i]);
					monitor.worked(1);
				}
			}
		}
	}
	
	private static IFile[] getPackageContent(IScriptFolder pack) throws CoreException {
		List result= new ArrayList();
		IContainer container= (IContainer)pack.getResource();
		if (container != null) {
			IResource[] members= container.members();
			for (int m= 0; m < members.length; m++) {
				IResource member= members[m];
				if (member instanceof IFile) {
					IFile file= (IFile)member;
					if ("class".equals(file.getFileExtension()) && file.isDerived()) //$NON-NLS-1$
						continue;
					result.add(member);
				}
			}
		}
		return (IFile[])result.toArray(new IFile[result.size()]);
	}
	
	
	private static final class SourceModuleResourceMapping extends DLTKElementResourceMapping {
		private final ISourceModule fUnit;
		private SourceModuleResourceMapping(ISourceModule unit) {
			Assert.isNotNull(unit);
			fUnit= unit;
		}
		public Object getModelObject() {
			return fUnit;
		}
		public IProject[] getProjects() {
			return new IProject[] {fUnit.getScriptProject().getProject() };
		}
		public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
			return new ResourceTraversal[] {
				new ResourceTraversal(new IResource[] {fUnit.getResource()}, IResource.DEPTH_ONE, 0)
			};
		}
	}	
	
	private static final class LogicalPackageResourceMapping extends ResourceMapping {
		private final IScriptFolder[] fFragments;
		private LogicalPackageResourceMapping(IScriptFolder[] fragments) {
			fFragments= fragments;
		}
		public Object getModelObject() {
			return fFragments;
		}
		public IProject[] getProjects() {
			Set result= new HashSet();
			for (int i= 0; i < fFragments.length; i++) {
				result.add(fFragments[i].getScriptProject().getProject());
			}
			return (IProject[])result.toArray(new IProject[result.size()]);
		}
		public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
			List result= new ArrayList();
			if (context instanceof RemoteResourceMappingContext) {
				for (int i= 0; i < fFragments.length; i++) {
					result.add(new ResourceTraversal(
						new IResource[] {fFragments[i].getResource()}, IResource.DEPTH_ONE, 0));
				}
			} else {
				for (int i= 0; i < fFragments.length; i++) {
					result.add(new LocalPackageFragementTraversal(fFragments[i]));
				}
			}
			return (ResourceTraversal[])result.toArray(new ResourceTraversal[result.size()]);
		}
		
		public String getModelProviderId() {
			return DLTKModelProvider.DLTK_MODEL_PROVIDER_ID;
		}
	}
	
	public static ResourceMapping create(IModelElement element) {
		switch (element.getElementType()) {
			case IModelElement.TYPE:
				return create((IType)element);
			case IModelElement.SOURCE_MODULE:
				return create((ISourceModule)element);			
			case IModelElement.SCRIPT_FOLDER:
				return create((IScriptFolder)element);
			case IModelElement.PROJECT_FRAGMENT:
				return create((IProjectFragment)element);
			case IModelElement.SCRIPT_PROJECT:
				return create((IDLTKProject)element);
			case IModelElement.SCRIPT_MODEL:
				return create((IScriptModel)element);
			default:
				return null;
		}		
		
	}

	public static ResourceMapping create(final IScriptModel model) {
		return new ScriptModelResourceMapping(model);
	}
	
	public static ResourceMapping create(final IDLTKProject project) {
		return new ScriptProjectResourceMapping(project);
	}
	
	public static ResourceMapping create(final IProjectFragment root) {
		if (root.isExternal())
			return null;
		return new PackageFragementRootResourceMapping(root);
	}
	
	public static ResourceMapping create(final IScriptFolder pack) {
		// test if in an archive
		IProjectFragment root= (IProjectFragment)pack.getAncestor(IModelElement.PROJECT_FRAGMENT);
		if (!root.isArchive()) {
			return new ScriptFolderResourceMapping(pack);
		}
		return null;
	}
	
	public static ResourceMapping create(ISourceModule unit) {
		if (unit == null)
			return null;
		return new SourceModuleResourceMapping(unit.getPrimary());
	}	
	
	public static ResourceMapping create(IType type) {
		// top level types behave like the CU
		IModelElement parent= type.getParent();
		if (parent instanceof ISourceModule) {
			return create((ISourceModule)parent);
		}
		return null;
	}
	
	public static ResourceMapping create(LogicalPackage logicalPackage) {
		IScriptFolder[] fragments= logicalPackage.getFragments();
		List toProcess= new ArrayList(fragments.length);
		for (int i= 0; i < fragments.length; i++) {
			// only add if not part of an archive
			IProjectFragment root= (IProjectFragment)fragments[i].getAncestor(IModelElement.PROJECT_FRAGMENT );
			if (!root.isArchive()) {
				toProcess.add(fragments[i]);
			}
		}
		if (toProcess.size() == 0)
			return null;
		return new LogicalPackageResourceMapping((IScriptFolder[])toProcess.toArray(new IScriptFolder[toProcess.size()]));
	}
}

package org.eclipse.dltk.internal.ui.editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProblemRequestor;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.text.DefaultLineTracker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ILineTracker;
import org.eclipse.jface.text.ISynchronizable;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelListener;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.texteditor.AbstractMarkerAnnotationModel;
import org.eclipse.ui.texteditor.IDocumentProvider;


public class SourceModuleDocumentProvider extends TextFileDocumentProvider implements ISourceModuleDocumentProvider {

	/** Indicates whether the save has been initialized by this provider */
	private boolean fIsAboutToSave = false;

	/** The save policy used by this provider */
	private ISavePolicy fSavePolicy;

	/**
	 * Element information of all connected elements with a fake CU but no file
	 * info.
	 * 
	 *
	 */
	private final Map fFakeCUMapForMissingInfo = new HashMap();

	public SourceModuleDocumentProvider() {

		IDocumentProvider provider = new TextFileDocumentProvider();
		provider = new SourceForwardingDocumentProvider(provider);
		if (DLTKCore.DEBUG) {
			System.out.println("Don't know how to put stuff like this into language depend core");
		}
		setParentDocumentProvider(provider);
		if (DLTKCore.DEBUG) {
			System.out.println("SourceModuleDocumentProvider: Add preference store listener here.");
		}
	}

	/**
	 * Bundle of all required informations to allow working copy management.
	 */
	static protected class SourceModuleInfo extends FileInfo {
		
		public SourceModuleInfo() {
		}

		public ISourceModule fCopy;
	}

	public void shutdown() {

		Iterator e = getConnectedElementsIterator();
		while (e.hasNext()) {
			disconnect(e.next());
		}
	}

	public ISourceModule getWorkingCopy(Object element) {
		FileInfo fileInfo = getFileInfo(element);
		if (fileInfo instanceof SourceModuleInfo) {
			SourceModuleInfo info = (SourceModuleInfo) fileInfo;
			return info.fCopy;
		}
		SourceModuleInfo cuInfo = (SourceModuleInfo) fFakeCUMapForMissingInfo.get(element);
		if (cuInfo != null)
			return cuInfo.fCopy;

		return null;
		// if( element instanceof IScriptFileEditorInput ) {
		// FileInfo fileInfo = getFileInfo( element );
		// if( fileInfo instanceof SourceModuleInfo ) {
		// SourceModuleInfo info = ( SourceModuleInfo )fileInfo;
		// return info.fCopy;
		// }
		// }
		// return null;
	}

	public void saveDocumentContent(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite) throws CoreException {

		System.out.println("Todo: Add save document operation here. to correct commit of model changes.");
		if (!fIsAboutToSave) {
			return;
		}
		super.saveDocument(monitor, element, document, overwrite);
	}

	public ILineTracker createLineTracker(Object element) {

		return new DefaultLineTracker();
	}

	public void setSavePolicy(ISavePolicy savePolicy) {

		// TODO Auto-generated method stub

	}

	public void addGlobalAnnotationModelListener(IAnnotationModelListener listener) {

		// TODO Auto-generated method stub

	}

	public void removeGlobalAnnotationModelListener(IAnnotationModelListener listener) {

		// TODO Auto-generated method stub

	}

	/**
	 * Creates a source module from the given file.
	 * 
	 * @param file
	 *            the file from which to create the source module
	 */
	protected ISourceModule createSourceModule(IFile file) {

		Object element = DLTKCore.create(file);
		if (element instanceof ISourceModule) {
			return (ISourceModule) element;
		}
		return null;
	}

	/*
	 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider#createEmptyFileInfo()
	 */
	protected FileInfo createEmptyFileInfo() {

		return new SourceModuleInfo();
	}

	private void setUpSynchronization(SourceModuleInfo cuInfo) {

		IDocument document = cuInfo.fTextFileBuffer.getDocument();
		IAnnotationModel model = cuInfo.fModel;

		if (document instanceof ISynchronizable && model instanceof ISynchronizable) {
			Object lock = ((ISynchronizable) document).getLockObject();
			((ISynchronizable) model).setLockObject(lock);
		}
	}

	/*
	 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider#createFileInfo(java.lang.Object)
	 */
	protected FileInfo createFileInfo(Object element) throws CoreException {

		ISourceModule original = null;

		if (element instanceof IFileEditorInput) {
			IFileEditorInput input = (IFileEditorInput) element;
			original = createSourceModule(input.getFile());
			if (original == null)
				return null;
		} else if (element instanceof ExternalStorageEditorInput) {
			IModelElement modelE = (IModelElement) ((ExternalStorageEditorInput) element).getAdapter(IModelElement.class);
			if (modelE != null && modelE instanceof ISourceModule) {
				original = (ISourceModule) modelE;
			}
			if (original == null) {
				return null;
			}
		}

		FileInfo info = super.createFileInfo(element);

		if (!(info instanceof SourceModuleInfo))
			return null;

		if (original == null)
			original = createFakeSourceModule(element, false);
		if (original == null)
			return null;

		SourceModuleInfo cuInfo = (SourceModuleInfo) info;
		setUpSynchronization(cuInfo);

		IProblemRequestor requestor = cuInfo.fModel instanceof IProblemRequestor ? (IProblemRequestor) cuInfo.fModel : null;
		// if (requestor instanceof IProblemRequestorExtension) {
		// IProblemRequestorExtension extension= (IProblemRequestorExtension)
		// requestor;
		// extension.setIsActive(false);
		// extension.setIsHandlingTemporaryProblems(isHandlingTemporaryProblems());
		// }

		if (DLTKModelUtil.isPrimary(original))
			original.becomeWorkingCopy(requestor, getProgressMonitor());
		cuInfo.fCopy = original;

		// if (cuInfo.fModel instanceof SourceModuleAnnotationModel) {
		// SourceModuleAnnotationModel model=
		// (SourceModuleAnnotationModel) cuInfo.fModel;
		// model.setSourceModule(cuInfo.fCopy);
		// }

		// if (cuInfo.fModel != null)
		// cuInfo.fModel.addAnnotationModelListener(fGlobalAnnotationModelListener);

		return cuInfo;
	}

	/*
	 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider#disposeFileInfo(java.lang.Object,
	 *      org.eclipse.ui.editors.text.TextFileDocumentProvider.FileInfo)
	 */
	protected void disposeFileInfo(Object element, FileInfo info) {

		if (info instanceof SourceModuleInfo) {
			SourceModuleInfo cuInfo = (SourceModuleInfo) info;

			try {
				cuInfo.fCopy.discardWorkingCopy();
			} catch (ModelException x) {
				handleCoreException(x, x.getMessage());
			}

			// if( cuInfo.fModel != null )
			// cuInfo.fModel.removeAnnotationModelListener(
			// fGlobalAnnotationModelListener );
		}
		super.disposeFileInfo(element, info);
	}

	/**
	 * Creates and returns a new sub-progress monitor for the given parent
	 * monitor.
	 * 
	 * @param monitor
	 *            the parent progress monitor
	 * @param ticks
	 *            the number of work ticks allocated from the parent monitor
	 * @return the new sub-progress monitor
	 */
	private IProgressMonitor getSubProgressMonitor(IProgressMonitor monitor, int ticks) {

		if (monitor != null)
			return new SubProgressMonitor(monitor, ticks, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);

		return new NullProgressMonitor();
	}

	/*
	 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider#createSaveOperation(java.lang.Object,
	 *      org.eclipse.jface.text.IDocument, boolean)
	 */
	protected DocumentProviderOperation createSaveOperation(final Object element, final IDocument document, final boolean overwrite)
			throws CoreException {

		final FileInfo info = getFileInfo(element);
		if (info instanceof SourceModuleInfo) {

//			 Delegate handling of non-primary SourceModules
			ISourceModule cu= ((SourceModuleInfo)info).fCopy;
			if (cu != null && !DLTKModelUtil.isPrimary(cu))
				return super.createSaveOperation(element, document, overwrite);
			
			if (info.fTextFileBuffer.getDocument() != document) {
				// the info exists, but not for the given document
				// -> saveAs was executed with a target that is already open
				// in another editor
				// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=85519
				System.out.println("SourceModuleDocumentProvider: need to replace with messages api");
				Status status = new Status(IStatus.WARNING, EditorsUI.PLUGIN_ID, IStatus.ERROR, "Save as Target open in editor", null);
				throw new CoreException(status);
			}

			return new DocumentProviderOperation() {
				/*
				 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider.DocumentProviderOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
				 */
				protected void execute(IProgressMonitor monitor) throws CoreException {

					commitWorkingCopy(monitor, element, (SourceModuleInfo) info, overwrite);
				}

				/*
				 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider.DocumentProviderOperation#getSchedulingRule()
				 */
				public ISchedulingRule getSchedulingRule() {

					if (info.fElement instanceof IFileEditorInput) {
						IFile file = ((IFileEditorInput) info.fElement).getFile();
						return computeSchedulingRule(file);
					} else
						return null;
				}
			};
		}
		return null;
	}

	protected void commitWorkingCopy(IProgressMonitor monitor, Object element, final SourceModuleInfo info, boolean overwrite) throws CoreException {

		if (monitor == null)
			monitor = new NullProgressMonitor();

		monitor.beginTask("", 100); //$NON-NLS-1$

		try {
			final IProgressMonitor subMonitor1 = getSubProgressMonitor(monitor, 50);

			try {
				SafeRunner.run(new ISafeRunnable() {
					public void run() {
						try {
							info.fCopy.reconcile(false, null, subMonitor1);
						} catch (ModelException ex) {
							handleException(ex);
						} catch (OperationCanceledException ex) {
							// do not log this
						}
					}

					public void handleException(Throwable ex) {
						IStatus status = new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, IStatus.OK,
								"Error in DLTK Core during reconcile while saving", ex); //$NON-NLS-1$
						DLTKUIPlugin.getDefault().getLog().log(status);
					}
				});
			} finally {
				subMonitor1.done();
			}

			IDocument document = info.fTextFileBuffer.getDocument();
			IResource resource = info.fCopy.getResource();

			Assert.isTrue(resource instanceof IFile);

			boolean isSynchronized = resource.isSynchronized(IResource.DEPTH_ZERO);
			/*
			 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=98327 Make sure
			 * file gets save in commit() if the underlying file has been
			 * deleted
			 */
			if (!isSynchronized && isDeleted(element))
				info.fTextFileBuffer.setDirty(true);

			if (!resource.exists()) {
				// underlying resource has been deleted, just recreate file,
				// ignore the rest
				IProgressMonitor subMonitor2 = getSubProgressMonitor(monitor, 50);
				try {
					createFileFromDocument(subMonitor2, (IFile) resource, document);
				} finally {
					subMonitor2.done();
				}
				return;
			}

			if (fSavePolicy != null)
				fSavePolicy.preSave(info.fCopy);

			IProgressMonitor subMonitor3 = getSubProgressMonitor(monitor, 50);
			try {
				fIsAboutToSave = true;
				info.fCopy.commitWorkingCopy(isSynchronized || overwrite, subMonitor3);
			} catch (CoreException x) {
				// inform about the failure
				fireElementStateChangeFailed(element);
				throw x;
			} catch (RuntimeException x) {
				// inform about the failure
				fireElementStateChangeFailed(element);
				throw x;
			} finally {
				fIsAboutToSave = false;
				subMonitor3.done();
			}

			// If here, the dirty state of the editor will change to "not
			// dirty".
			// Thus, the state changing flag will be reset.
			if (info.fModel instanceof AbstractMarkerAnnotationModel) {
				AbstractMarkerAnnotationModel model = (AbstractMarkerAnnotationModel) info.fModel;
				model.updateMarkers(document);
			}

			if (fSavePolicy != null) {
				ISourceModule unit = fSavePolicy.postSave(info.fCopy);
				if (unit != null && info.fModel instanceof AbstractMarkerAnnotationModel) {
					IResource r = unit.getResource();
					IMarker[] markers = r.findMarkers(IMarker.MARKER, true, IResource.DEPTH_ZERO);
					if (markers != null && markers.length > 0) {
						AbstractMarkerAnnotationModel model = (AbstractMarkerAnnotationModel) info.fModel;
						for (int i = 0; i < markers.length; i++)
							model.updateMarker(document, markers[i], null);
					}
				}
			}
		} finally {
			monitor.done();
		}
	}

	/*
	 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider#connect(java.lang.Object)
	 *
	 */
	public void connect(Object element) throws CoreException {
		super.connect(element);
		if (getFileInfo(element) != null)
			return;

		SourceModuleInfo info = (SourceModuleInfo) fFakeCUMapForMissingInfo.get(element);
		if (info == null) {
			ISourceModule cu = null;
			if (element instanceof ExternalStorageEditorInput) {
				IModelElement e = (IModelElement) ((ExternalStorageEditorInput) element).getAdapter(IModelElement.class);
				if (e != null && e instanceof ISourceModule) {
					cu = (ISourceModule) e;
				}
			}
			if (cu == null) {
				cu = createFakeSourceModule(element, true);
			}
			if (cu == null)
				return;
			info = new SourceModuleInfo();
			info.fCopy = cu;
			info.fElement = element;
			info.fModel = new AnnotationModel();
			fFakeCUMapForMissingInfo.put(element, info);
		}
		info.fCount++;
	}

	/*
	 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider#getAnnotationModel(java.lang.Object)
	 *
	 */
	public IAnnotationModel getAnnotationModel(Object element) {
		IAnnotationModel model = super.getAnnotationModel(element);
		if (model != null)
			return model;

		FileInfo info = (FileInfo) fFakeCUMapForMissingInfo.get(element);
		if (info != null) {
			if (info.fModel != null)
				return info.fModel;
			if (info.fTextFileBuffer != null)
				return info.fTextFileBuffer.getAnnotationModel();
		}

		return null;
	}

	/*
	 * @see org.eclipse.ui.editors.text.TextFileDocumentProvider#disconnect(java.lang.Object)
	 *
	 */
	public void disconnect(Object element) {
		SourceModuleInfo info = (SourceModuleInfo) fFakeCUMapForMissingInfo.get(element);
		if (info != null) {
			if (info.fCount == 1) {
				fFakeCUMapForMissingInfo.remove(element);
				info.fModel = null;
				// Destroy and unregister fake working copy
				try {
					info.fCopy.discardWorkingCopy();
				} catch (ModelException ex) {
					handleCoreException(ex, ex.getMessage());
				}
			} else
				info.fCount--;
		}
		super.disconnect(element);
	}

	/**
	 * Creates a fake compilation unit.
	 * 
	 * @param element
	 *            the element
	 * @param setContents
	 *            tells whether to read and set the contents to the new CU
	 *
	 */
	private ISourceModule createFakeSourceModule(Object element, boolean setContents) {
		if (!(element instanceof IStorageEditorInput))
			return null;

		final IStorageEditorInput sei = (IStorageEditorInput) element;

		try {
			final IStorage storage = sei.getStorage();
			final IPath storagePath = storage.getFullPath();
			if (storage.getName() == null || storagePath == null)
				return null;

			final IPath documentPath;
			if (storage instanceof IFileState)
				documentPath = storagePath.append(Long.toString(((IFileState) storage).getModificationTime()));
			else
				documentPath = storagePath;

			WorkingCopyOwner woc = new WorkingCopyOwner() {				
				public IBuffer createBuffer(ISourceModule workingCopy) {
					return new DocumentAdapter(workingCopy, documentPath);
				}
			};

			IBuildpathEntry[] cpEntries = null;
			IDLTKProject jp = findScriptProject(storagePath);
			if (jp != null)
				cpEntries = jp.getResolvedBuildpath(true);

			if (cpEntries == null || cpEntries.length == 0)
				cpEntries = new IBuildpathEntry[] { DLTKCore.newSourceEntry(storagePath) };

			final ISourceModule cu = woc.newWorkingCopy(storage.getName(), cpEntries, null, getProgressMonitor());
			if (setContents) {
				int READER_CHUNK_SIZE = 2048;
				int BUFFER_SIZE = 8 * READER_CHUNK_SIZE;
				Reader in = new BufferedReader(new InputStreamReader(storage.getContents()));
				StringBuffer buffer = new StringBuffer(BUFFER_SIZE);
				char[] readBuffer = new char[READER_CHUNK_SIZE];
				int n;
				try {
					n = in.read(readBuffer);
					while (n > 0) {
						buffer.append(readBuffer, 0, n);
						n = in.read(readBuffer);
					}
				} catch (IOException e) {
					DLTKUIPlugin.log(e);
				}
				cu.getBuffer().setContents(buffer.toString());
			}

			if (!isModifiable(element))
				DLTKModelUtil.reconcile(cu);

			return cu;
		} catch (CoreException ex) {
			return null;
		}
	}

	/**
	 * Fuzzy search for script project in the workspace that matches the given
	 * path.
	 * 
	 * @param path
	 *            the path to match
	 * @return the matching script project or <code>null</code>
	 *
	 */
	private IDLTKProject findScriptProject(IPath path) {
		if (path == null)
			return null;

		String[] pathSegments = path.segments();
		IScriptModel model = DLTKCore.create(DLTKUIPlugin.getWorkspace().getRoot());
		IDLTKProject[] projects;
		try {
			projects = model.getScriptProjects();
		} catch (ModelException e) {
			return null; // ignore - use default RE
		}
		for (int i = 0; i < projects.length; i++) {
			IPath projectPath = projects[i].getProject().getFullPath();
			String projectSegment = projectPath.segments()[0];
			for (int j = 0; j < pathSegments.length; j++)
				if (projectSegment.equals(pathSegments[j]))
					return projects[i];
		}
		return null;
	}
}

package org.eclipse.dltk.validators.internal.ui.eternalchecker;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.eclipse.dltk.validators.internal.core.externalchecker.CustomWildcard;

public class CustomWildcardsList {
	
	private Vector wcards = new Vector();
	private Set changeListeners = new HashSet();
	
	
	public void addWcard(){
		CustomWildcard r = new CustomWildcard('x', "Enter your pattern here");
		wcards.add(r);
		Iterator iterator = changeListeners.iterator();
		while(iterator.hasNext()){
			((IWildcardListViewer)iterator.next()).addWildcard(r);
		}
	}
	public void removeChangeListener(IWildcardListViewer viewer) {
		changeListeners.remove(viewer);
	}

	public void addChangeListener(IWildcardListViewer viewer) {
		changeListeners.add(viewer);
	}
	
	public Vector getWcards(){
		return wcards;
	}
	
	public void wcardChanged(CustomWildcard r) {
		Iterator iterator = changeListeners.iterator();
		while (iterator.hasNext())
			((IWildcardListViewer) iterator.next()).updateWildcard(r);
	}
	
	public  void addWcard(CustomWildcard r){
		wcards.add(r);
		Iterator iterator = changeListeners.iterator();
		while(iterator.hasNext()){
			((IWildcardListViewer)iterator.next()).addWildcard(r);
		}
	}
	
	public void removeWcard(CustomWildcard task) {
		wcards.remove(task);
		Iterator iterator = changeListeners.iterator();
		while (iterator.hasNext())
			((IWildcardListViewer) iterator.next()).removeWildcard(task);
	}
	

}

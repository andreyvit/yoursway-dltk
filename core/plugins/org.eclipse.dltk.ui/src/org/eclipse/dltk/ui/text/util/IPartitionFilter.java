/**
 * 
 */
package org.eclipse.dltk.ui.text.util;

/**
 * Tests whether given partitions satisfy an arbitrary criteria.
 * 
 * @author Andrey Tarantsov
 */
public interface IPartitionFilter {
	
	boolean allowPartition(String partition);
	
}
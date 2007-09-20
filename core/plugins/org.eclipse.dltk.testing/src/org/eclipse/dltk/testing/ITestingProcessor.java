package org.eclipse.dltk.testing;

public interface ITestingProcessor {
	public void start(); 
	public void done();	
	public void processLine(String line);
}

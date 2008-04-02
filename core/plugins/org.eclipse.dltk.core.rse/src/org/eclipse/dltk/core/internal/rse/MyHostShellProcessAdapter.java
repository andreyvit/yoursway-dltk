package org.eclipse.dltk.core.internal.rse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.eclipse.rse.services.shells.HostShellOutputStream;
import org.eclipse.rse.services.shells.IHostOutput;
import org.eclipse.rse.services.shells.IHostShell;
import org.eclipse.rse.services.shells.IHostShellChangeEvent;
import org.eclipse.rse.services.shells.IHostShellOutputListener;


/**
 * This class represents a host shell process. It does not 
 * represent one process running in the shell.  This means 
 * that the output of multiple shell commands will be returned
 * until the shell exits.
 * 
 * @author Ewa Matejska
 */
public class MyHostShellProcessAdapter extends Process implements
IHostShellOutputListener {

	private IHostShell hostShell;
	private PipedInputStream inputStream = null;
	private PipedInputStream errorStream = null;
	private HostShellOutputStream outputStream = null;
	
	private PipedOutputStream hostShellInput = null;
	private PipedOutputStream hostShellError = null;
	private boolean started = false;
	private String command;
	
	/**
	 * Constructor.
	 * @param hostShell  An instance of the IHostShell class.
	 * @throws java.io.IOException
	 */
	public MyHostShellProcessAdapter(String command, IHostShell hostShell) throws java.io.IOException {
		this.hostShell = hostShell;
		this.command = command;
		hostShellInput = new PipedOutputStream();
		hostShellError = new PipedOutputStream();
		inputStream = new PipedInputStream(hostShellInput);
		errorStream = new PipedInputStream(hostShellError);
		outputStream = new HostShellOutputStream(hostShell);
		this.hostShell.getStandardOutputReader().addOutputListener(this);
		this.hostShell.getStandardErrorReader().addOutputListener(this);
	}
	
	/**
	 * Exits the shell.
	 * @see java.lang.Process#destroy()
	 */
	public synchronized void destroy() {
		hostShell.exit();
		notifyAll();
		try {
			hostShellInput.close();
			hostShellError.close();
			inputStream.close();
			errorStream.close();
			outputStream.close();
		} catch (IOException e) {
			//FIXME IOException when closing one of the streams will leave others open
			// Ignore
		}	
	}

	/**
	 * There is no relevant exit value to return when the shell exits.
	 * This always returns 0.
	 */
	public synchronized int exitValue() {
		if(hostShell.isActive())
			throw new IllegalThreadStateException();
		// No way to tell what the exit value was.
		// TODO it would be possible to get the exit value
		// when the remote process is started like this:
		//   sh -c 'remotecmd ; echo "-->RSETAG<-- $?\"'
		// Then the output steram could be examined for -->RSETAG<-- to get the exit value.
		return 0;
	}

	/**
	 * Returns the error stream of the shell.
	 * @see java.lang.Process#getErrorStream()
	 */
	public InputStream getErrorStream() {
		return errorStream;
	}

	/**
	 * Returns the input stream for the shell.
	 * @see java.lang.Process#getInputStream()
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * Returns the output stream for the shell.
	 * @see java.lang.Process#getOutputStream()
	 */
	public OutputStream getOutputStream() {
		return outputStream;
	}

	/**
	 * Waits for the shell to exit.
	 * @see java.lang.Process#waitFor()
	 */
	public synchronized int waitFor() throws InterruptedException {
		
		while(hostShell.isActive()) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				// ignore because we're polling to see if shell is still active.
			}
		}
		
		try {
			// Wait a second to try to get some more output from the target shell before closing.
			wait(1000);
			// Allow for the data from the stream to be read if it's available
			if (inputStream.available() != 0 || errorStream.available() != 0)
				throw new InterruptedException();
	
			hostShellInput.close();
			hostShellError.close();
			inputStream.close();
			errorStream.close();
			outputStream.close();
		} catch (IOException e) {
			// Ignore
		}
		return 0;
	}
	
	/**
	 * Process an RSE Shell event, by writing the lines of text contained
	 * in the event into the adapter's streams.
	 * @see org.eclipse.rse.services.shells.IHostShellOutputListener#shellOutputChanged(org.eclipse.rse.services.shells.IHostShellChangeEvent)
	 */
	public void shellOutputChanged(IHostShellChangeEvent event) {
		IHostOutput[] input = event.getLines();
		OutputStream outputStream = event.isError() ? hostShellError : hostShellInput;
		try {
		for(int i = 0; i < input.length; i++) {
			String string = input[i].getString();
			if (!started && string.indexOf(command) >= 0) {
				started = true;
				continue;
			}
			if (!started) {
				continue;
			}
			if (string.indexOf('$') >= 0) {
				destroy();
				continue;
			}
			outputStream.write(string.getBytes());
			outputStream.write('\n');
			outputStream.flush();
		}
		} catch(IOException e) {
			// Ignore
		}
	}

}

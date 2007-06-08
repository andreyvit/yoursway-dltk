package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.commands.IDbgpStatckCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public class ScriptStack {
	private ScriptStackFrame[] frames;

	private ScriptThread thread;

	protected ScriptStackFrame[] readFrames(IDbgpStatckCommands commands)
			throws DbgpException {
		IDbgpStackLevel[] levels = commands.getStackLevels();
		ScriptStackFrame[] frames = new ScriptStackFrame[levels.length];

		for (int i = 0; i < levels.length; ++i) {
			IDbgpStackLevel level = levels[i];
			frames[level.getLevel()] = new ScriptStackFrame(thread, level);
		}

		return frames;
	}

	public ScriptStack(ScriptThread thread) {
		this.thread = thread;

		this.frames = new ScriptStackFrame[0];
	}

	public void update() {
		this.frames = new ScriptStackFrame[0];

		try {
			this.frames = readFrames(thread.getDbgpSession().getCoreCommands());
		} catch (DbgpException e) {
		}
	}

	public ScriptThread getThread() {
		return thread;
	}

	public int size() {
		return frames.length;
	}

	public boolean hasFrames() {
		return frames.length > 0;
	}

	public ScriptStackFrame[] getFrames() {
		return (ScriptStackFrame[]) frames.clone();
	}

	public ScriptStackFrame getTopFrame() {
		ScriptStackFrame[] frames = getFrames();
		return frames.length > 0 ? frames[0] : null;
	}
}
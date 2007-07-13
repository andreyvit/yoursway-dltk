package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.commands.IDbgpStatckCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;

public class ScriptStack {
	public static final IScriptStackFrame[] NO_STACK_FRAMES = new IScriptStackFrame[0];

	private IScriptStackFrame[] frames;

	private final ScriptThread thread;

	protected IScriptStackFrame[] readFrames(IDbgpStatckCommands commands)
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
		this.frames = NO_STACK_FRAMES;

		try {
			this.frames = readFrames(thread.getDbgpSession().getCoreCommands());
		} catch (DbgpException e) {
			// TODO: log exception
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

	public IScriptStackFrame[] getFrames() {
		return (IScriptStackFrame[]) frames.clone();
	}

	public IScriptStackFrame getTopFrame() {
		final IScriptStackFrame[] frames = getFrames();
		return frames.length > 0 ? frames[0] : null;
	}
}
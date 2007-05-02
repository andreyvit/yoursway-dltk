package org.eclipse.dltk.ruby.debug.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpStackLevel;
import org.eclipse.dltk.dbgp.internal.commands.DbgpStackCommands;

import sun.security.action.GetLongAction;

public class RubyStack {
	private RubyStackFrame[] frames;

	private RubyThread thread;

	protected RubyStackFrame[] readFrames() throws Exception {
		IDbgpSession session = thread.getSession();
		
		IDbgpStackLevel[] levels = null;
		
		synchronized (session) {
					
		
		List temp = session.getCoreCommands().getStackLevels();
		levels = (IDbgpStackLevel[]) temp
				.toArray(new IDbgpStackLevel[temp.size()]);
		}

		List newFrames = new ArrayList();

		int length = Math.min(frames.length, levels.length);

		int i = 1;
		while (i <= length) {
			RubyStackFrame frame = frames[frames.length - i];
			IDbgpStackLevel level = levels[levels.length - i];

			if (frame.equals(level)) {
				frame.setLevel(level);
				newFrames.add(frame);
			} else {
				break;
			}

			++i;
		}

		if (frames.length == levels.length && length == levels.length) {
			RubyStackFrame topFrame = frames[0];
			topFrame.setDirty();
			topFrame.setLevel(levels[0]);
			newFrames.add(topFrame);
		} else {
			while (i <= levels.length) {
				RubyStackFrame frame = new RubyStackFrame(thread,
						levels[levels.length - i]);
				newFrames.add(frame);

				++i;
			}
		}

		Collections.reverse(newFrames);

		return (RubyStackFrame[]) newFrames
				.toArray(new RubyStackFrame[newFrames.size()]);
	}

	public RubyStack(RubyThread thread) {
		this.thread = thread;
		this.frames = new RubyStackFrame[0];
		this.dirty = true;
	}

	public RubyThread getThread() {
		return thread;
	}

	private boolean dirty;

	public void setDirty() {
		dirty = true;
	}

	protected void checkFrames() {
		if (!dirty) {
			return;
		}

		try {
			frames = readFrames();
		} catch (Exception e) {
			frames = new RubyStackFrame[0];
			e.printStackTrace();
		}

		dirty = false;
	}

	public int size() {
		checkFrames();

		return frames.length;
	}

	public boolean hasFrames() {
		checkFrames();

		return frames.length > 0;
	}

	public RubyStackFrame[] getFrames() {
		checkFrames();

		System.out.println("RubyStack.getFrames()");
		RubyStackFrame[] temp = (RubyStackFrame[]) frames.clone();

		for (int i = 0; i < temp.length; ++i) {
			System.out.println(temp[i]);
		}

		return temp;
	}

	public RubyStackFrame getTopFrame() {
		return hasFrames() ? frames[0] : null;
	}
}

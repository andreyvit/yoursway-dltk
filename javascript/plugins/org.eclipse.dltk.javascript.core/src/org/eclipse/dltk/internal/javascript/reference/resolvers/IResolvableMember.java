package org.eclipse.dltk.internal.javascript.reference.resolvers;

import java.io.Reader;

public interface IResolvableMember {

	Reader getInfo(boolean lookIntoParents,
			boolean lookIntoExternal);

}

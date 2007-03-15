package org.eclipse.dltk.javascript.internal.core.codeassist;

import java.util.HashMap;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.javascript.reference.resolvers.ReferenceResolverContext;
import org.eclipse.dltk.internal.javascript.reference.resolvers.ResolverManager;
import org.eclipse.dltk.internal.javascript.typeinference.HostCollection;
import org.eclipse.dltk.internal.javascript.typeinference.PositionReachedException;
import org.eclipse.dltk.internal.javascript.typeinference.TypeInferencer;

import com.xored.org.mozilla.javascript.CompilerEnvirons;
import com.xored.org.mozilla.javascript.Parser;
import com.xored.org.mozilla.javascript.ScriptOrFnNode;

public class AssitUtils {

	public static ReferenceResolverContext buildContext(ISourceModule module, int position, String content,
			char[] fileName2) {
		HashMap settings=new HashMap();
		ReferenceResolverContext createResolverContext = ResolverManager.createResolverContext(module,settings);
		Parser p = new Parser(new CompilerEnvirons(), new NullReporter());
		ScriptOrFnNode parse = p
				.parse(content, new String(fileName2), 0);
		TypeInferencer inf = new TypeInferencer((ModelElement) module,createResolverContext);
		try {
			inf.doInterferencing(parse, position);
		} catch (PositionReachedException ex) {

		}	
		createResolverContext.init();
		HostCollection collection = inf.getCollection();
		createResolverContext.setHostCollection(collection);
		return createResolverContext;
	}
	
	public static class PositionCalculator{
		
		private boolean isMember = false;
		private boolean needDot = false;
		private int lastDot = -1;
		private int nestLevel = 0;
		private int position;
		private int pos;
		private String content;
		
		public PositionCalculator(String conString,int pos,boolean bothSides){
			this.position=pos;
			this.pos=calculatePos(conString, pos,bothSides);
			
			this.content=conString;
			if (position>conString.length())position=conString.length();
		}
		
		public String getCompletion(){
			return content.substring(pos, position).trim();
		}
		
		private int calculatePos(String content, int pos, boolean bothSides) {
			int k=pos;
			if (bothSides)
			{
			int maxPos = content.length()-1;
			if (pos<maxPos)
			{
			l1:while (pos<maxPos){
				pos++;
				char charAt = content.charAt(pos);
				if (charAt == ']') {
					nestLevel++;
					continue;
				}
				if (charAt == '[') {
					nestLevel--;
					continue;
				}
				if (nestLevel > 0)
					continue;
				if (Character.isWhitespace(charAt)) {
					
					continue l1;
				}


				if (!needDot && Character.isJavaIdentifierPart(charAt))
					continue l1;
				else {
					pos += 1;
					// isMember = false;
					break l1;
				}
			}
			position=pos-1;
			}
			else
			position=pos+1;
			
			}
			pos=k;
			l2: while (pos > 0) {
				pos--;
				char charAt = content.charAt(pos);
				if (charAt == ']') {
					nestLevel++;
					continue;
				}
				if (charAt == '[') {
					nestLevel--;
					continue;
				}
				if (nestLevel > 0)
					continue;
				if (Character.isWhitespace(charAt)) {
					needDot = true;
					continue l2;
				}

				if (charAt == '.') {
					isMember = true;
					needDot = false;
					if (lastDot == -1)
						lastDot = pos + 1;

					continue l2;
				}
				if (!needDot && Character.isJavaIdentifierPart(charAt))
					continue l2;
				else {
					pos += 1;
					// isMember = false;
					break l2;
				}
			}
			return pos;
		}

		public String getCompletionPart() {
			return content.substring(lastDot, position).trim();
		}

		public String getCorePart() {
			return content. substring(pos, lastDot - 1).trim();
		}

		public boolean isMember() {
			return isMember;
		}
	}
	
	
}

package org.eclipse.dltk.rhino.dbgp;

import java.io.IOException;
import java.io.Reader;

public class CombinedReader extends Reader{

	private final Reader bf;
	
	public CombinedReader(Reader bf) {
		super();
		this.bf = bf;
	}

	public void close() throws IOException {
		bf.close();
	}
	int count=0;

	public int read(char[] cbuf, int off, int len) throws IOException {
		if (count<1){
			cbuf[off++]='\r';
			len--;
			count=1;
			if (len==0)return 1;
			
			cbuf[off++]='\n';
			len--;
			count=2;
			if (len==0)return 2;
		}
		return bf.read(cbuf, off, len);
	}

}

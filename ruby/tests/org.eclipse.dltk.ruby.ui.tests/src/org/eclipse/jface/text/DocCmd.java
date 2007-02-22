package org.eclipse.jface.text;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.eclipse.jface.text.DocumentCommand;

public class DocCmd extends DocumentCommand{
    public DocCmd(int offset, int length, String text){
        this.offset = offset;
        this.length = length;
        this.text   = text;
        this.doit = true;
        this.caretOffset = -1;
        this.shiftsCaret = true;
    }

}
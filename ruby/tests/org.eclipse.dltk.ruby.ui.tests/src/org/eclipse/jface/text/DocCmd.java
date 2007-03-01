package org.eclipse.jface.text;


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
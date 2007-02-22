package org.eclipse.dltk.ui.text.blocks;

public abstract class KeywordRole {

	private final String name;

	private final int id;

	private KeywordRole(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public int id() {
		return id;
	}

	public static final int ID_BEGINNING = 0;

	public static final int ID_ENDING = 1;

	public static final int ID_MIDDLE = 2;

	public static final int COUNT = 3;

	public static final KeywordRole BEGINNING = new KeywordRole(ID_BEGINNING, "BEGINNING") {
	};

	public static final KeywordRole ENDING = new KeywordRole(ID_ENDING, "ENDING") {
	};

	public static final KeywordRole MIDDLE = new KeywordRole(ID_MIDDLE, "MIDDLE") {
	};

}

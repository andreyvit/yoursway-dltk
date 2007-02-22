package org.eclipse.dltk.tcl.internal.parsers.raw;

public class VariableSubstitution extends TclElement implements ISubstitution {

	public static final int VAR_SIMPLE = 0;
	public static final int VAR_ARRAY = 1;
	public static final int VAR_NAME = 2;

	private String name;
	private TclWord index;
	private int kind;

	public VariableSubstitution() {
	}

	public TclWord getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public static boolean iAm(CodeScanner scanner) {
		int c = scanner.read();
		if (c == -1)
			return false;
		if (c != '$') {
			scanner.unread();
			return false;
		}
		int c2 = scanner.read();
		scanner.unread();
		if (c2 != -1)
			scanner.unread();
		if (!TclTextUtils.isIdentifier(c2) && c2 != '(' && c2 != '{') {
			return false;
		}
		return true;
	}

	public boolean readMe(CodeScanner input) throws TclParseException {
		if (!iAm(input))
			return false;
		setStart(input.getPosition());
		this.name = "";
		this.kind = VAR_SIMPLE;
		input.read();
		int c = input.read();
		if (c == '{') {
			this.kind = VAR_NAME;
			while (true) {
				c = input.read();
				if (c == -1) {
					throw new TclParseException(
							"unexpected EOF while processing braces variable name",
							input.getPosition());
				}
				if (c == '}')
					break;
				this.name += (char) c;
			}
		} else {
			do {
				if (c == CodeScanner.EOF) {
					break; // stop!
				}
				if (TclTextUtils.isIdentifier(c)) {
					this.name += (char) c;
					c = input.read();
				} else {
					if (c == '(') { // read index part
						this.kind = VAR_ARRAY;
						TclWord cvb = new TclWord();
						int ch;
						while (true) {
							ISubstitution s = SimpleTclParser.getCVB(input);

							if (s != null) {
								s.readMe(input);
								cvb.add(s);
							} else {
								ch = input.read();
								if (ch == CodeScanner.EOF) {
									boolean cont = SimpleTclParser
											.handleError(new ErrorDescription(
													"unexpected EOF while processing variable index",
													input.getPosition(),
													ErrorDescription.ERROR));
									if (!cont)
										throw new TclParseException(
												"unexpected EOF while processing variable index",
												input.getPosition());
									else
										break; // stop!
								}

								if (ch == ')') {
									break;
								} else
									cvb.add((char) ch);
							}
						}
						this.index = cvb;
						break;
					} else {
						input.unread();
						break;
					}
				}
			} while (true);
		}
		if (!input.isEOF()) {
			setEnd(input.getPosition() - 1);
		} else
			setEnd(input.getPosition());
		return true;
	}

	public int getKind() {
		return kind;
	}
}

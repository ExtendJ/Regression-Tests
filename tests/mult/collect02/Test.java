public class Test {
	public @any String strings;

	public Test(String... init) {
		for (String str: init) {
			strings += str;
		}
	}

	public static void main(String[] args) {
		@option Test test = new Test("one", "two");
		String buf = "";
		for (String string: test.strings) {
			buf += string;
			buf += ",";
		}
		String expected = "one,two,";
		if (!buf.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + buf + "\"");
			System.exit(1);
		}
	}
}

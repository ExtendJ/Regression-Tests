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
		check("one,two,", buf);
	}

	private static void check(String expected, String actual) {
		if (!actual.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + actual + "\"");
			System.exit(1);
		}
	}
}

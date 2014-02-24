public class Test {
	public @option String string;

	public Test(String init) {
		string = init;
	}

	public Test() {
	}

	public static void main(String[] args) {
		@any Test tests;
		tests += new Test("one");
		tests += new Test();
		tests += new Test("three");
		String buf = "";
		for (String string: tests.string) {
			buf += string;
			buf += ",";
		}
		check("one,three,", buf);
	}

	private static void check(String expected, String actual) {
		if (!actual.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + actual + "\"");
			System.exit(1);
		}
	}
}

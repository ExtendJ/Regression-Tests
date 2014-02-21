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
		String expected = "one,three,";
		if (!buf.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + buf + "\"");
			System.exit(1);
		}
	}
}

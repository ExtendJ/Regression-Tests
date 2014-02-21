public class Test {
	public @option String string;

	public Test(String init) {
		string = init;
	}

	public Test() {
	}

	public static void main(String[] args) {
		String buf = "";
		@option Test test;
		test = null;
		for (String string: test.string) {
			buf += string;
			buf += ",";
		}
		test = new Test("one");
		for (String string: test.string) {
			buf += string;
			buf += ",";
		}
		String expected = "one,";
		if (!buf.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + buf + "\"");
			System.exit(1);
		}
	}
}

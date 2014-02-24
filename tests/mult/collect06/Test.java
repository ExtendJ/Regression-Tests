public class Test {
	public @option String s;

	public Test(String init) {
		s = init;
	}

	@option String s() {
		return s;
	}

	public static void main(String[] args) {
		@any Test tests;
		tests += new Test("1");
		tests += new Test(null);
		tests += new Test("3");
		String actual = "";
		for (String string: tests.s()) {
			actual += string;
			actual += ",";
		}
		check("1,3,", actual);
	}

	private static void check(String expected, String actual) {
		if (!actual.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + actual + "\"");
			System.exit(1);
		}
	}
}

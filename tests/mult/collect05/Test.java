public class Test {
	public @any String strings;

	public Test(String... init) {
		strings = [[java.util.Arrays.asList(init)]];
	}

	@any String strings() {
		return strings;
	}

	public static void main(String[] args) {
		@any Test tests;
		tests += new Test("1", "2");
		tests += new Test("3", "M");
		String actual = "";
		for (String string: tests.strings()) {
			actual += string;
			actual += ",";
		}
		check("1,2,3,M,", actual);
	}

	private static void check(String expected, String actual) {
		if (!actual.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + actual + "\"");
			System.exit(1);
		}
	}
}

public class Test {
	static class Inner {
		public @any String strings;

		public Inner(String... init) {
			for (String str: init) {
				strings += str;
			}
		}
	}
	public static void main(String[] args) {
		@any Inner inners;
		inners += new Inner("one", "two");
		inners += new Inner("three", "four");
		String buf = "";
		for (String string: inners.strings) {
			buf += string;
			buf += ",";
		}
		check("one,two,three,four,", buf);
	}

	private static void check(String expected, String actual) {
		if (!actual.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + actual + "\"");
			System.exit(1);
		}
	}
}

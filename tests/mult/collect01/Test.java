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
		String expected = "one,two,three,four,";
		if (!buf.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + buf + "\"");
			System.exit(1);
		}
	}
}

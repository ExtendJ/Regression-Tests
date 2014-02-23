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
		check(2, [[tests.s()]].size());
	}

	private static void check(int expected, int actual) {
		if (actual != expected) {
			System.err.println("Error: expected " + expected +
					" actual: " + actual);
			System.exit(1);
		}
	}
}

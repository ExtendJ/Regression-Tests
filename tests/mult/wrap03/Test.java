public class Test {

	public static void main(String[] args) {
		@option String s = "hello";
		// cannot wrap option-type
		check(1, [[s]].size());
	}

	private static void check(int expected, int actual) {
		if (actual != expected) {
			System.err.println("Error: expected " + expected +
					" actual: " + actual);
			System.exit(1);
		}
	}
}

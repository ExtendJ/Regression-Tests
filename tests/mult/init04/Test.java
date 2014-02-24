// Tests internal semantics of any-mult initialization
public class Test {
	static @any String a = [[new java.util.ArrayList<String>() { { add("x"); } }]];
	public static void main(String[] args) {
		String buf = "";
		for (String str: a) {
			buf += str;
		}
		check("xx", buf);
	}

	private static void check(String expected, String actual) {
		if (!actual.equals(expected)) {
			System.err.println("Error: expected \"" + expected +
					"\" actual: \"" + actual + "\"");
			System.exit(1);
		}
	}
}

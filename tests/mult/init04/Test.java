// Tests internal semantics of any-mult initialization
public class Test {
	static @any String a = [[new java.util.ArrayList<String>() { { add("x"); } }]];
	public static void main(String[] args) {
		String buf = "";
		for (String str: a) {
			buf += str;
		}
		if (!buf.equals("xxx")) {
			System.err.println(
					"Error: expected \"xxx\", but was \"" +
					buf + "\"");
			System.exit(1);
		}
	}
}

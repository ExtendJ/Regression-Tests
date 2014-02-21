// Tests internal semantics of any-mult initialization
public class Test {
	static @any String a = [[new java.util.ArrayList<String>() { { add("init"); } }]];
	public static void main(String[] args) {
		String buf = "";
		for (String str: a) {
			buf += str;
		}
		if (!buf.equals("initinitinit")) {
			System.err.println(
					"Error: expected \"initinitinit\", but was \"" +
					buf + "\"");
			System.exit(1);
		}
	}
}

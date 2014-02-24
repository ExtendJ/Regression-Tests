// Tests default collection initialization
public class Test {
	static @any String a = [[new java.util.HashSet<String>()]];
	public static void main(String[] args) {
		@any String b = a;
		java.util.Collection coll = [[b]];
		if (!(coll instanceof java.util.HashSet)) {
			System.err.println(
					"Error: container collection should be java.util.HashSet");
			System.exit(1);
		}
	}
}

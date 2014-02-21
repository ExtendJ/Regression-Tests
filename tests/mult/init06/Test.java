// Tests default collection initialization
public class Test {
	static @any String a;
	public static void main(String[] args) {
		java.util.Collection coll = [[a]];
		if (!(coll instanceof java.util.ArrayList)) {
			System.err.println(
					"Error: container collection should be java.util.ArrayList");
			System.exit(1);
		}
	}
}

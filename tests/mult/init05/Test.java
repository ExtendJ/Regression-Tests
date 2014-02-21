// Tests default collection initialization
public class Test {
	static @any(java.util.LinkedList) String a;
	public static void main(String[] args) {
		java.util.Collection coll = [[a]];
		if (!(coll instanceof java.util.LinkedList)) {
			System.err.println(
					"Error: container collection should be java.util.LinkedList");
			System.exit(1);
		}
	}
}

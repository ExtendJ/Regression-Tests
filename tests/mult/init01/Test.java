// Tests initialization of multiplicity type
public class Test {
	public static void main(String[] args) {
		@any(java.util.Collection) String a = null;
		if (![[a]].isEmpty()) {
			System.err.println("Error: expected empty any-relation");
			System.exit(1);
		}
	}
}

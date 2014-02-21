// Tests initialization of multiplicity type
public class Test {
	public static void main(String[] args) {
		@any(java.util.Collection) String a = "init";
		if (![[a]].contains("init")) {
			System.err.println("Error: expected one element in any-relation");
			System.exit(1);
		}
	}
}

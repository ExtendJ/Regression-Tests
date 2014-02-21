// Tests initialization of multiplicity type
public class Test {
	public static void main(String[] args) {
		@any(java.util.Collection) String a = "init";
		a += "add";
		@any(java.util.Collection) String b = a;
		if ([[b]].size() != 2) {
			System.err.println("Error: expected two elements in any-relation");
			System.exit(1);
		}
	}
}

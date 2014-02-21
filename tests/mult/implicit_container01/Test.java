// Tests implicit any container type (=java.util.Collection)
public class Test {
	public static void main(String[] args) {
		@any String a = null;
		a += "add";
		a += "add";
		if ([[a]].size() != 2) {
			System.err.println("Error: expected two objects in any-relation, found " + [[a]].size());
			System.exit(1);
		}
	}
}

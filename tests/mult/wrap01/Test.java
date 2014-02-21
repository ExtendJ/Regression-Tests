public class Test {
	public static void main(String[] args) {
		@any(java.util.Collection) String a = null;
		a += "A";
		[[a]].add("B");
		if ([[a]].contains("B")) {
			System.err.println("Error: could mutate relation via wrap expression");
			System.exit(1);
		}
	}
}

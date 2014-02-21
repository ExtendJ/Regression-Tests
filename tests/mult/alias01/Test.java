// Tests aliasing of multiplicities
public class Test {
	public static void main(String[] args) {
		@any String a;
		@any String b;
		a += "hello";
		b = a;
		a += "oops";
		if ([[b]].size() != 1) {
			System.err.println("Error: multiplicity aliasing detected!");
		}
	}
}

public class Test {
	public static void main(String[] args) {
		@any String a = null;
		if (a instanceof java.lang.Iterable) {
			System.err.println("Error: any-type should not be subtype of java.util.Iterable");
			System.exit(1);
		}
	}
}

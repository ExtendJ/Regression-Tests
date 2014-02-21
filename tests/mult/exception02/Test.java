public class Test {
	public static void main(String[] args) {
		@option String a = "OK";
		@any(java.util.Collection) String b = [[new java.util.ArrayList<String>()]];
		b += a;
		b += "TOO MANY!";
		b -= "TOO MANY!";
		try {
			a = (@option) b;
		} catch (ClassCastException e) {
			System.exit(1);
		}
	}
}

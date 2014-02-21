public class Test {
	public static void main(String[] args) {
		@option String a = "OK";
		@any(java.util.Collection) String b = [[new java.util.ArrayList<String>()]];
		b += a;
		b += b;
		b += "NOT OK";
		b += b;
		b -= a;
		for (String status: [[b]]) {
			System.out.println("status: " + status);
		}
	}
}

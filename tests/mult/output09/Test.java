public class Test {
	public static void main(String[] args) {
		@any(java.util.Collection) String a = [[new java.util.ArrayList<String>()]];
		a = "[1]";
		a = "[2]";
		a = "[3]";
		a -= "[2]";
		for (String str: [[a]]) {
			System.out.println(str);
		}
	}
}

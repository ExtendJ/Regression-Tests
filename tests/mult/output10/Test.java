public class Test {
	public static void main(String[] args) {
		@any(java.util.Collection) String a = [[new java.util.ArrayList<String>()]];
		for (String str: [[(a = "[1]")]]) {
			System.out.println(str);
		}
	}
}

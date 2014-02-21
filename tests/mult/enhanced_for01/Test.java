public class Test {
	public static void main(String[] args) {
		@any String a;
		a += "one";
		a += "two";
		a += "three";
		String str = "";
		for (String item: a) {
			str += item;
		}
		if (!str.equals("onetwothree")) {
			System.err.println("Error: expected onetwothree, was " + str);
			System.exit(1);
		}
	}
}

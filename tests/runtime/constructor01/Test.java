public class Test {
	public Test(Object... args) {
		boolean first = true;
		for (Object arg : args) {
			if (!first)
				System.out.print(' ');
			first = false;
			System.out.print("" + arg);
		}
	}
	public static void main(String[] argv) {
		new Test(new Integer(3), "xyz");
	}
}

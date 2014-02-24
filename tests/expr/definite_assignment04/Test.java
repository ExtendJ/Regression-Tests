public class Test {
	public static void foo(int a) {
	}

	public static void main(String[] args) {
		final int f;
		boolean b = args[0].length() == 1;
		foo(b ? 3 : (f = 5));
		f = 4;// expect error because f not definitely unassigned
		System.out.println(""+f);
	}
}

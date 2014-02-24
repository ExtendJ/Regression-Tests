public class Test {
	public void m() {
		final int f;
		f = 3;
		f = 4;// expect error because f not definitely unassigned
		System.out.println(""+f);
	}
}

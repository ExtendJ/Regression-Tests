// Test for definite unassignedness bug
// https://bitbucket.org/jastadd/jastaddj/issue/51/definite-un-assignedness-for-assignment
// .result=COMPILE_FAIL
public class Test {
	public void m() {
		final int f;
		f = 3;
		f = 4;// expect error because f not definitely unassigned
		System.out.println(""+f);
	}
}

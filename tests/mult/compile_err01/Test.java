public class Test {
	public int a;
	public void m(@any(java.util.Collection) Test t) {
		int x = t.a;
	}
}

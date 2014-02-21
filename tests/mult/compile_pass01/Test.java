public class Test {
	@option Test a = null;
	public void m(@any(java.util.Collection) Test t) {
		@any(java.util.Collection) Test x = t.a;
	}
}

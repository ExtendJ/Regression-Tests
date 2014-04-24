public class Test {
	interface A {
		default void m() {
			String s = super.toString();
		}
	}
}
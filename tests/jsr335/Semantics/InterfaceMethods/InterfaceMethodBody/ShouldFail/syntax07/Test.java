public class Test {
	interface A {
		static void m() {
			String s = super.toString();
		}
	}
}
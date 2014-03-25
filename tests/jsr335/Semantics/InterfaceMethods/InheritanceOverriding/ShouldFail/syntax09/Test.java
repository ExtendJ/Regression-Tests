public class Test {
	
	interface A {
		static void m(int i) { }
		default void m(int i) { }
	}
}
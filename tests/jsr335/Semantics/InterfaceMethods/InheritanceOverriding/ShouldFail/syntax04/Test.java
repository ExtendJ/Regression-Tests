// .result=COMPILE_FAIL
public class Test {
	interface A {
		default void m(int i) { }
	}
	
	interface B extends A {
		static void m(int i) { }
	}
}
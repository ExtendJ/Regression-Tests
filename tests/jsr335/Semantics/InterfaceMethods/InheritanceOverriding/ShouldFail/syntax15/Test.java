public class Test {
	interface A  {
		default void m(int i) { System.out.println("" + i + 1); }
	}
	interface B extends A {
		default void m(int i) {  }
	}
	
	interface C extends B {
		default void m(int i) { }
	}
	
	interface D extends A, C {
		default void m(int i) {
			A.super.m(4);
		}
	}
}
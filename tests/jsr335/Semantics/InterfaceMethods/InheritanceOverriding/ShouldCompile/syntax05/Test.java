// .result=COMPILE_PASS

public class Test {
	interface A {
		default void m(int i) { System.out.println("" + i + 1); }
	}
	interface B extends A {
		default void m(int i) { A.super.m(i); }
	}
	
	public class C implements B {
		public void m() {
			m(5);
		}
	}
}
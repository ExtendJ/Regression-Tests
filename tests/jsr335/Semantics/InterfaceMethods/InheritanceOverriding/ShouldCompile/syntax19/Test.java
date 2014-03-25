public class Test {
	interface A  {
		default void m(int i) { System.out.println("" + i + 1); }
	}
	interface B  {
		default void m(int i) {  }
	}
	
	public class C implements A, B {
		public void m(int i) {
			A.super.m(4);
			B.super.m(3);
		}
	}
}
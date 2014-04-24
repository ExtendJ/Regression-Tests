
public class Test {
	
	interface A {
		default void m() { }
		int m2();
	}
	
	Integer integ = new Integer(5);
	A a = integ::intValue;
}
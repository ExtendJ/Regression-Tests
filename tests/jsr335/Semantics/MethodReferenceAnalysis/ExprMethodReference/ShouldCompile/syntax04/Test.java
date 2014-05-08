
public class Test {
	
	interface A {
		default void m() { }
		double m2();
	}
	
	Integer integ = new Integer(5);
	Double doub = new Double(4.9);
	
	public void method(int i) {
		A a = i==4 ? integ::intValue : doub::doubleValue;
	}
}

public class Test {
	
	interface A {
		int m(double d);
	}
	
	public int method(int i) {
		return i;
	}
	
	A a = this::method;
}
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class Test {

	public interface A {
		<T extends Integer> B m(T t);
	}
	
	public class B {
		public B(Integer i) {
		}
	}
	
	public void testMethod() {
		A a = B::new;
	}
	
	public static void main(String[] arg) {
		new Test().testMethod();
	}
}
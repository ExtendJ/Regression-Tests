// .result=COMPILE_PASS
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class Test {
	
	interface A {
		B m(Integer s);
	}
	
	class B<T> {
		public B(T t)  {
			
		}
	}
	
	public void testMethod() {
		A a = B<Number>::new;
	}

}
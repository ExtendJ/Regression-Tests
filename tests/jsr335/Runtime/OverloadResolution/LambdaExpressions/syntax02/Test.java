import static runtime.Test.*;

import java.util.*;

public class Test {
	public static int out = 0;
	
	interface A {
		void m(int a, int b); 
	}
	interface B {
		int m(int a, int b);
	}
	
	public static void method(A a) {
		out = 1;
	}
	
	public static void method(B b) {
		out = 2;
	}
	
	// Javac fails this test, fairly sure it's a bug
	
	public static void main(String[] args) {
		// Tests that only statement expressions (not other expressions) are applicable with void return
		method((a, b) -> a + b);
		testTrue("Method overload", out == 2);
		
		// Tests that non-value void-compatible blocks are only applicable with void-return
		method((a, b) -> {System.out.println("String"); });
		testTrue("Method overload", out == 1);
		
		// Tests that non-void value-compatible blocks are only applicable with value-return
		method((a, b) -> {
			if(a < b) 
				return a + b;
			else 
				return a;
		});
		testTrue("Method overload", out == 2);
    }
}
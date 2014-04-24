import static runtime.Test.*;

import java.util.*;

public class Test {
	public static int out = 0;
	
	interface A {
		int m(int a, int b); 
	}
	
	interface B {
		void m(int a, int b);
	}
	
	interface C extends B {
		
	}
	
	public static int m() {
		return 5;
	}
	
	public static void method(A a1, C... a2) {
		out = 1;
	}
	public static void method(B... i) {
		out = 2;
	}
	
	public static void main(String[] args) {
		method((int a, int b) -> m(), (int a, int b) -> m());
		testTrue("Method overload", out == 1);
    }
}
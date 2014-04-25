import static runtime.Test.*;

import java.util.*;

public class Test {
	public static int out = 0;
	
	interface A {
		int[] m(int a); 
	}
	interface B {
		int[][] m(int a);
	}
	
	
	public static void method(A a) {
		out = 1;
	}
	
	public static void method(B b) {
		out = 2;
	}
	
	public static void main(String[] args) {
		// Tests that the method with compatible return type is chosen
		method(int[]::new);
		testTrue("Method overload", out == 1);
    }
}
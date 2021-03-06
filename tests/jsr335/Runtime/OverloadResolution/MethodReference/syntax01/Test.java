// .classpath: @RUNTIME_CLASSES@
import static runtime.Test.*;

import java.util.*;

public class Test {
	public static int out = 0;
	
	interface A {
		int m(int a, int b); 
	}
	interface B {
		String m(int a, int b);
	}
	
	public static int m(int a, int b) {
		return 4;
	}
	
	public static void method(A a) {
		out = 1;
	}
	
	public static void method(B b) {
		out = 2;
	}
	
	public static void main(String[] args) {
		// Tests that the method with compatible return type is chosen
		method(Test::m);
		testTrue("Method overload", out == 1);
    }
}

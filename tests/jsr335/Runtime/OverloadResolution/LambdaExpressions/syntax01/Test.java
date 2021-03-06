// .classpath: @RUNTIME_CLASSES@
import static runtime.Test.*;

import java.util.*;

public class Test {
	public static int out = 0;
	
	interface A {
		void m(int a, int b); 
	}
	interface B {
		void m(int a);
	}
	
	public static void method(A a) {
		out = 1;
	}
	
	public static void method(B b) {
		out = 2;
	}
	
	public static void main(String[] args) {
		// Tests that only methods with types with correct arity are applicable
		method((a, b) -> { });
		testTrue("Method overload", out == 1);
    }
}

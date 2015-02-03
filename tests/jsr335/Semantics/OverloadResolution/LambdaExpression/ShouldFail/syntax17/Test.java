// .result=COMPILE_FAIL
import static runtime.Test.testTrue;


public class Test {
	public static int out = 0;
	
	interface A {
		C m(int a, int b); 
	}
	interface B {
		D m(int a, int b);
	}
	interface C {
		void m(int a);
	}
	interface D {
		int m(int a);
	}
	
	public static int m() {
		return 4;
	}
	
	public static void method(A a) {
		out = 1;
	}
	
	public static void method(B b) {
		out = 2;
	}
	
	public static void main(String[] args) {
		// Tests that for most specific method for lambdas, bullet #3,
		// every single return must be more specific 
		method((int a, int b) -> {
			if(a < b) 
				return (int c) -> m();
			else
				return c -> c + a;
		});
    }
}

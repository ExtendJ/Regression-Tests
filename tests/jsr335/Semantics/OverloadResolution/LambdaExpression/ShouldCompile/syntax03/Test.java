// .result=COMPILE_PASS

public class Test {
	interface A {
		int m(int a); 
	}
	
	public static void method(int i, A a) {
		
	}
	
	public static void main(String[] args) {
		method(45, i -> i + 6);
    }
}
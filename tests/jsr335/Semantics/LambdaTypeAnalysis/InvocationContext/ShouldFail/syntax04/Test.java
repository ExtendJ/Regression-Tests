
public class Test {
	interface A {
		int m(int a, int b); 
	}
	
	public static void method(A a) {
		
	}
	
	public static void main(String[] args) {
		method((int a, int b) -> {
			if(a < b)
				return;
		});
    }
}
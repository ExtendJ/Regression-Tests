
public class Test {
	public interface TestInterface {
		public void functMethod(int a, int b); 
	}
	
	public static int method(int a, int b) {
		return a + b;
	}
	
	public static void main(String[] args) {
		TestInterface t = (int a, int b) ->  method(a, b);
    }
}

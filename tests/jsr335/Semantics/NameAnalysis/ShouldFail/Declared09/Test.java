
public class Test {
	public interface TestInterface {
		public void functMethod(int a, int b, int c); 
	}
	
	public static void main(String[] args) {
		TestInterface t = (int a, int b, int b) -> {
			System.out.println("" + a + b);
		};
    }
}


public class Test {
	public interface TestInterface {
		public void functMethod(int a, int b, int c); 
	}
	
	public static void main(String[] args) {
		TestInterface t = (a, b, c) -> {
			TestInterface t2 = (c, d, e) -> {};
		};
    }
}


public class Test {
	public interface TestInterface {
		public void functMethod(int a, int b, int c); 
	}
	
	public static void main(String[] args) {
		TestInterface t = (int a, int b, int c) -> {
			TestInterface t2 = (int d, int e, int f) -> { int args = 4;  };
		};
    }
}

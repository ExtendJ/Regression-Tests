// .result=COMPILE_PASS


public class Test {
	public interface TestInterface {
		public int functMethod(int a); 
	}
	
	public static void main(String[] args) {
		TestInterface t = (int a) -> {
			int b = a + 4;
			return b;
		};
    }
}
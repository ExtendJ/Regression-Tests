

public class Test {
	public interface TestInterface {
		public int functMethod(); 
	}
	
	public static void main(String[] args) {
		int a;
		TestInterface t = () -> {
			a = 5;
			return a + b;
		};
    }
	
}
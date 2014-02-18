
public class Test {
	public interface TestInterface {
		public int functMethod(); 
	}
	
	public interface NestedTestInterface {
		public TestInterface functMethod(int a, int b);
	}
	
	public static void main(String[] args) {		
		NestedTestInterface t = (a, b) -> c -> a + b + c;
    }
}
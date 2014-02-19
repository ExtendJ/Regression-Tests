

public class Test {
	public interface TestInterface {
		public int functMethod(); 
	}
	
	public static void main(String[] args) {
		int a = 4;
		TestInterface t = () -> a;
		a++;
    }
	
}

public class Test {
	public interface TestInterface {
		public int functMethod(int a); 
	}
	
	public static void main(String[] args) {		
		TestInterface t = args -> args + 3;
    }
}


public class Test {
	public interface TestInterface {
		public int functMethod(); 
	}
	
	public interface NestedTestInterface {
		public TestInterface functMethod();
	}

	//Taken from JSR335 section B, 15.27.2
	public static void main(String[] args) {
		int a;
		if(args[0].length() == 1)
			a = 4;
		NestedTestInterface t = () -> () -> a + 4; 
    }
}
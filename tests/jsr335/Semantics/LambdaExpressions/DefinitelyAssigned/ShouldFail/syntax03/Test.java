
public class Test {
	public interface TestInterface {
		public String functMethod(); 
	}
	
	public interface NestedTestInterface {
		public TestInterface functMethod();
	}
	
	//Taken from JSR335 section B, 15.27.2
	public static void main(String[] args) {
		int a = 4;
		int b;
		NestedTestInterface t = () -> { 
			if (a == 3) 
				return () -> {
					if(b == 2)
						return "Done";
					return "Not Done";
				};
			return () -> "Not done"; 
		};
    }
}
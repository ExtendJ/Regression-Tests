// .result=COMPILE_PASS

public class Test {
	public interface TestInterface {
		public int functMethod(); 
	}
	
	//Taken from JSR335 section B, 15.27.2
	public static void main(String[] args) {
		boolean cond = true;
		TestInterface t = () -> { if (cond) return 1; else return 0; };
    }
}

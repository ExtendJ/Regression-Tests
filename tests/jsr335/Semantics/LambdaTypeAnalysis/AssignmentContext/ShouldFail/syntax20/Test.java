// .result=COMPILE_FAIL

public class Test {
	public interface TestInterface {
		public InnerTestInterface functMethod(TestInterface a); 
	}
	
	public interface InnerTestInterface {
		public TestInterface functMethod();
	}
	
	public static void main(String[] args) {
		TestInterface t = args.length==4 ? a -> () -> a : a -> () -> b -> a;
	}
}

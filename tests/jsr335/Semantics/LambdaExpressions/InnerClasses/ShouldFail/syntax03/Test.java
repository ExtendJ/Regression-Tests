

public class Test {	
	public interface TestInterface {
		public void functMethod(int a);
	}
	
	public static void main(String[] args) {
		TestInterface t = (int a) -> {
			Runnable r = new Runnable() {
				public void run() { int b = a; }
			};
			a = 5;
		};
    }

}
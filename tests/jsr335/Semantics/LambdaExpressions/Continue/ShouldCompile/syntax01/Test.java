

public class Test {
	public interface TestInterface {
		public int functMethod(int a); 
	}
	
	public static void main(String[] args) {
		TestInterface t = (int a) -> {
			for(int i = 0; i < a; i++) {
				if(i == 10)
					continue;
				System.out.println("Not 10");
			}
		};
    }
}
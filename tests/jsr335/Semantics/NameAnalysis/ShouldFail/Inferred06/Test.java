
public class Test {
	public interface TestInterface {
		public void functMethod(int a); 
	}
	
	public static void main(String[] args) {		
		TestInterface t = a -> {
			int a = 4;
			if(a == 4)
				System.out.println("out");
		};
    }
}

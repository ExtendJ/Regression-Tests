

public class Test {
	public interface TestInterface {
		public int functMethod(); 
	}
	
	public static void main(String[] args) {
		int[] a = new int[10];
		TestInterface t = () -> a[0] + a[1];
		a = new int[10];
    }
	
}

public class Test {
	public interface TestInterface {
		public void functMethod(); 
	}
	
	public static void main(String[] args) {
		TestInterface t = () -> {};
    }
}

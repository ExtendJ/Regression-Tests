
public class Test {
	public interface TestInterface {
		 public void m();
		 public void g();
	}
	
	public static void main(String[] args) {
		TestInterface t = () -> {};
    }
}

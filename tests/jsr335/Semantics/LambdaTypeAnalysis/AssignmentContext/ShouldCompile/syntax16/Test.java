import java.util.*;

public class Test {
	public interface TestInterface<T> {
		public void functMethod(T t); 
	}
	
	public static void main(String[] args) {
		TestInterface<Integer> t = a ->  {
			int b = a.intValue();
		};
    }
}

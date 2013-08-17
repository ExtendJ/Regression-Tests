
public class T7_2 {
    T7_2 (){}
    public static void main(String[] args) {
        
	try (AutoCloseable foo = System.out) {
	} catch (Exception e) {
		// handle possible exception from close
	}

    }
}

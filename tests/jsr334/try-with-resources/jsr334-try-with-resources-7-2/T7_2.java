
public class T7_2 {
  void pass() {

	  try (AutoCloseable foo = System.out) {
	  } catch (Exception e) {
		  // Handle possible exception from close.
	  }

  }
}

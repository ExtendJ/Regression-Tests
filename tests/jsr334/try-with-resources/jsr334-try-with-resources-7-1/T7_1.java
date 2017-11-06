
public class T7_1 {
  void fail() {

	  try (AutoCloseable foo = System.out) {
	  }

  }
}

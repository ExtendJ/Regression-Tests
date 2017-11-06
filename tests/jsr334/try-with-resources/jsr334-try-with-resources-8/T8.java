
public class T8 {
  void fail() {

	  try (java.io.PrintStream r = System.out) {
		  r = System.err;
	  }

  }
}

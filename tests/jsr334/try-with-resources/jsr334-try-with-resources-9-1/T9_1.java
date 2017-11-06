
public class T9_1 {
  void fail() {

	  try (java.io.PrintStream r = System.out;
		    java.io.PrintStream r = System.err) {
	  }

  }
}

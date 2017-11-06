
public class T9_5 {
  void fail() {

		try (java.io.PrintStream r = System.out) {
			try {
				throw new Exception();
			} catch (Exception r) {
			}
		}

  }
}

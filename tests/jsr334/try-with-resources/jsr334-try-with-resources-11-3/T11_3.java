
public class T11_3 {
  void fail() {

	  try (java.io.PrintStream r = System.out) {
		  throw new Exception();
	  } catch (Exception e) {
		  return;
	  }
    // Unreachable:
	  new Integer(1).toString();

  }
}

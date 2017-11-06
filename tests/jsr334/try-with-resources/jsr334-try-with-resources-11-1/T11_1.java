// Unreachable statement test.

public class T11_1 {
  void fail() {
    try (java.io.PrintStream r = System.out) {
      return;
    }
    new Integer(1).toString(); // Unreachable.
  }
}

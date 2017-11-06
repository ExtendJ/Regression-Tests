// The resource declaration type must implement java.lang.AutoCloseable.
public class T6 {
  void fail() {
    try (Object foo = System.out) {
    }
  }
}

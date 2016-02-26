// The resource declaration type must implement java.lang.AutoCloseable.
public class T6 {
  T6 () {
  }

  public static void main(String[] args) {
    try (Object foo = System.out) {
    }
  }
}

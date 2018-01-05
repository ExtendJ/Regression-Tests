// Test if-statement with generic condition.
// .result: EXEC_PASS
public class Test {
  public static void main(String[] args) {
    run(204 + 1);
  }

  static void run(int value) {
    Container<Boolean> con = new Container<Boolean>(value % 100 == 4);
    if (con.get()) {
      System.out.println("should be false");
    }
    if (!con.get()) {
      return;
    }
    System.out.println("should be false");
  }

}

class Container<T> {
  private final T value;

  public Container(T value) {
    this.value = value;
  }

  T get() {
    return value;
  }
}

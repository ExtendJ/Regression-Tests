// Test that executable bytecode is generated when discarding a method
// result from a method that returns some wildcard-super type.
// .result: EXEC_PASS
public class Test {
  public static void main(String[] args) {
    Container<? super Integer> con = new Container<? super Integer>(123);
    for (int i = 0; i < 1; ++i) {
      con.get();
    }
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

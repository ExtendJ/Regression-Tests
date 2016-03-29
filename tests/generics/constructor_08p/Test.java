// Test that code generation works for parameterized constructor in parameterized type.
// .result=EXEC_PASS
public class Test<U extends Number, V> {
  final U u;
  final V v;

  public <S, T extends S> Test(S s, U u, T t, V v) {
    this.u = u;
    this.v = v;
    s = t;
  }

  public static void main(String[] args) {
    new <Number, Float> Test<Long, String>(123, 0xFFL, 0.222f, "123");
  }
}

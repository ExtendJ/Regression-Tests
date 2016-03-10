// Assignment context type inference.
// .result=COMPILE_PASS
public class Test {
  void test(C<String> c, Impl i) {
    C<Integer> r = c.x(i);
  }
}

interface C<T> {
  <R> C<R> x(I<T, R> function);
}

interface I<O, N> {
}

class Impl implements I<String, Integer> {
}

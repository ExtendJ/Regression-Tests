// Test that array types work with type inference.
// .result=COMPILE_PASS
interface Set<E> {
  <T> T[] toArray(T[] a);
}

public class Test {
  void test(Set<Long> set) {
    Long[] a = set.toArray(new Long[0]);
  }
}

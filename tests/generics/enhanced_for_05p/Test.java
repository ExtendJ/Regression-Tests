// Test that enhanced-for can iterate over an inferred-type expression.
// https://bitbucket.org/extendj/extendj/issues/285/type-inference-error-for-enhanced-for-loop
// .result: COMPILE_PASS
import java.util.*;
public class Test {
  void test() {
    for (Test t : myIterable()) {
    }
  }

  <T> Iterable<T> myIterable() {
    return null;
  }
}

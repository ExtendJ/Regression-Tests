// Resource declarations must have initializer expression.
// .result: COMPILE_FAIL
public class Test {
  void fail() {
	  try (AutoCloseable r) {
      r = System.out;
	  } catch (Exception r) {
	  }
  }
}

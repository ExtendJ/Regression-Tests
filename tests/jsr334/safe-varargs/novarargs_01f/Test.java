// @SafeVarargs may not be used on non-variable arity methods
// .result: COMPILE_FAIL
class Test {

  @SafeVarargs
  void fail() {
  }

}

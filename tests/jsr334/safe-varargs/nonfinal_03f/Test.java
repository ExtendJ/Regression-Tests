// @SafeVarargs may not be used on methods that are not declared final or static.
// .result: COMPILE_FAIL
class Test {

  @SafeVarargs
  <T> void foo(T... a) {
  }

}

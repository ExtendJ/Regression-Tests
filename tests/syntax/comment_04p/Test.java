// Test that documentation comments are parsed in odd places.
// .result=COMPILE_PASS
class Test {
  void f(/** Misplaced doc comment. */) {
  }
}

// A misplaced documentation comment does not cause a syntax error.
// .result=COMPILE_PASS
class Test {
  void /** Misplaced doc comment. */ f() {
  }
}

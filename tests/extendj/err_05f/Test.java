// .result: COMPILE_FAIL
public class Test {
  void fail() {
    go(missing());
  }

  void go(String s) { }
}

// Binary literals: large and within bounds.
// .result=COMPILE_PASS
public class Test {
  void pass() {
    long foo;
    foo = 0b1000000000000000000000000000000000000000000000000000000000000000L;
    foo = 0b01000000000000000000000000000000000000000000000000000000000000000L;
  }
}
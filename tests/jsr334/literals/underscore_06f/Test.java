// Sequences of underscores must be between digits.
// .result=COMPILE_FAIL
public class Test {
  void fail() {
    double bar = 0x.F_P1D;
  }
}

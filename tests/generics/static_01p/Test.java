// Test that accessing a static field in a raw type works as it should.
// .result=COMPILE_PASS
class Test {
  int m() {
    int m = G.y;
    return (m * m) / 2;
  }
}

class G<T> {
  static int y = 4;
  T x;
}

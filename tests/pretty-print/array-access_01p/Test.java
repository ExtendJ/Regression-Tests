// Test that synchronized statements are pretty-printed correctly.
// .result=COMPILE_OUT
// .options=XprettyPrint
public class Test {
  int f;

  void m() {
    Test[] t = new Test[3];
    t[0] = new Test();
    t[0].f = 3;
  }
}

// Type inference case which is not supported in Java 7 or lower but works in Java 8.
// .result=COMPILE_FAIL

interface Muffin {}

interface Oven {
  <T> T bake();
}

public class Test {
  void eat(Muffin m) { }

  void go(Oven oven) {
    // ExtendJ should infer the target type Muffin for oven.bake(),
    // however infers java.lang.Object.
    eat(oven.bake());
  }
}

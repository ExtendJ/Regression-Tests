// ExtendJ infers the wrong target type for a generic method
// when used as an argument to an ordinary method call.
// See https://bitbucket.org/extendj/extendj/issues/182/wrong-target-type-for-inferred-method
// .result=COMPILE_PASS

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

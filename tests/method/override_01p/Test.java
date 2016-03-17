// Test an overriding case that produced errors.
// .result=COMPILE_PASS
interface Interface {
  public Interface clone();
}

abstract class Base implements Interface {
  @Override
  public Interface clone() {
    return null;
  }
}

class Test extends Base implements Interface {
}

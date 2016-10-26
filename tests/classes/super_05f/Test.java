// A default constructor can not be generated for a non-inner class
// extending an inner class.
// .result=COMPILE_FAIL
public class Test {
  public class Inner {
  }
}

class A {
  class B extends Test.Inner {
  }
}

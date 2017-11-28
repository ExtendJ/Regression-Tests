// Interface methods can be static.
// This exposes a bytecode generation issue - wrong invoke instruction is emitted.
// https://bitbucket.org/extendj/extendj/issues/220/intstream-and-doublestream-does-not-work
// .result: EXEC_PASS
public class Test {
  public static void main(String[] args) {
    I.foo();
  }
}

interface I {
  static void foo() {}
}

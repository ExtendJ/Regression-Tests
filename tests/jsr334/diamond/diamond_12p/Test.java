// Type argument inference affects exception checking.
// .result=COMPILE_PASS
class Test {

  class T6_2_A<T> {
    public T a;
    <U extends Throwable> T6_2_A(T t, U u) throws U {
      a = t;
    }
  }

  {
    // In this invocation of T6_2_A, U is Error which is an unchecked exception type
    // so we do not need to catch the exception which the constructor declares thrown.
    new T6_2_A<>("bar", new Error()).a.getBytes();
  }
}

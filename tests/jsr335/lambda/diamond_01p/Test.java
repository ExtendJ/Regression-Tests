// Test for a Stack Overflow error caused by circular dependency between lambda
// type analysis and diamond type inference.
// https://bitbucket.org/extendj/extendj/issues/176/lambda-expression-stackoverflowerror
// .result=COMPILE_PASS
public class LambdaExpr01<T> {
  public LambdaExpr01(LambdaExprDataProvider<T> t) {}

  public static void main(String[] args) {
    LambdaExpr01<Object> a = new LambdaExpr01<>(
        () -> LambdaExprObjectProducer.produce(Object.class)
        );
  }
}

class LambdaExprObjectProducer {
  static <T> T produce(Class<T> clazz) {
    T t = null;
    return t;
  }
}

interface LambdaExprDataProvider<T> {
  T getData();
}

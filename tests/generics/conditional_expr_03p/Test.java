// Test conditional expression type analysis.
// .result=COMPILE_PASS
import java.util.Collection;
import java.util.List;

public class Test {
  S<?> test(boolean a, C1 b, C2 c) {
    return a ? b : c;
  }
}

class S<T> {
}

class C1 extends S<Integer> {
}

class C2 extends S<Integer> {
}

// This test uses the Java 8 standard library and exposes a bug in ExtendJ type inference.
// .result=COMPILE_PASS
import java.util.List;
import java.util.function.Function;

public class Test {
  public static List<Integer> map(List<String> list, Function<String, Integer> f) {
    return list.stream().map(f).collect(java.util.stream.Collectors.toList());
  }
}

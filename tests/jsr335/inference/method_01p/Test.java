// Method invocation type inference.
// This test uses the Java 8 standard library and exposes a bug in ExtendJ type inference.
// The bug is essentially that ExtendJ does not infer the correct target type.
// Simplified versions of this test are in the tests method_02p, method_03p, and method_04p.
// .result=COMPILE_PASS
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
  List<Integer> toList(Stream<Integer> stream) {
    // ExtendJ computes the wrong type for 'Collectors.toList()',
    // leading to no matching method being found.
    //
    // Collectors.toList():
    //     <T> Collector<T, ?, List<T>> toList()
    //
    // Stream.collect():
    //     <R, A> R collect(Collector<? super T, A, R>)
    return stream.collect(Collectors.toList());
  }
}

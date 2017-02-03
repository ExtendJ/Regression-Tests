// This test uses the Java 8 standard library and exposes a bug in ExtendJ type inference.
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

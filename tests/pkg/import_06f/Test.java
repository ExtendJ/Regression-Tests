// Conflicting single-static imports.
// This test is not valid for Java 8.
// .result=COMPILE_FAIL
import static alfa.Alfa.Gamma;
import static beta.Beta.Gamma;
public class Test {
}

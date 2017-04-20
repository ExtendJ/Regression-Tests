// Test for generic bounds checking.
// .result=COMPILE_FAIL
public class Test {
  Fun<String, ? extends String> fun;  // Error: String is not within the bounds of I.
}

interface Fun<I extends R, R> {
  R fun(I i);
}

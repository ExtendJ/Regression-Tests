// Annotation member types can not be nested array types.
// .result=COMPILE_FAIL

@interface Annot {
  String[][] names(); // Invalid annotation member type.
}

public class Test {
  public void f() {
  }
}

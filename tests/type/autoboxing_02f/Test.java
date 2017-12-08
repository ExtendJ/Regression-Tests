// Illegal autoboxing conversion.
// .result: COMPILE_FAIL

class Test {
  public static void main(String[] args) {
    int i = f(3); // Autoboxing conversion int->Integer.
  }

  static int f(Object i) {
    return (int) i; // Can't convert Object->int.
  }
}

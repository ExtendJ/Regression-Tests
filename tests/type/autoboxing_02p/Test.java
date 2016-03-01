// Test autoboxing.
// .result=EXEC_PASS

class Test {
  public static void main(String[] args) {
    int i = f(3); // Autoboxing conversion int->Integer.
  }

  static int f(Object i) {
    return (int) i; // Autoboxing conversion Integer->int.
  }
}

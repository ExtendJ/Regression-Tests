// Test autoboxing.
// .result=EXEC_PASS
class Test {
  public static void main(String[] args) {
    if (f(100L) != 100L) {
      throw new Error();
    }
  }
  static long f(Object o) {
    return (long) o; // Autoboxing conversion Long->long.
  }
}

public class Test {
  public static void main(String[] args) {
    new Test().run();
  }

  int f_i;
  short f_s;
  char f_c;
  byte f_b;
  long f_l;

  void run() {
    f_i = 230;
    f_s = (short) f_i;
    f_c = (char) f_i;
    f_b = (byte) f_i;
    f_l = f_i;

    System.out.println(f_i);
    System.out.println(f_s);
    System.out.println(f_c);
    System.out.println(f_b);
    System.out.println(f_l);
  }
}

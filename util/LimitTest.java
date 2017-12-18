import java.io.*;
import java.util.*;

public class LimitTest {
  public static void main(String[] args) throws IOException {
    int sum = 0;
    try (FileOutputStream f = new FileOutputStream("../tests/codegen/conv_01p/Test.java");
        PrintStream out = new PrintStream(f)) {
      Random rand = new Random(System.currentTimeMillis());
      out.println("// Test that an unnecessary I2C instruction is not generated.");
      out.println("// The data array contains so many elements that if each is");
      out.println("// converted with I2C, the init method will run out of space.");
      out.println("// https://bitbucket.org/extendj/extendj/issues/246/redundant-integer-conversion");
      out.println("public class Test {");
      out.println("  char[] data = {");
      for (int i = 0; i < 600; ++i) {
        out.print("      ");
        for (int j = 0; j < 15; ++j) {
          int val = rand.nextInt(100);
          sum += val;
          out.print(val);
          out.print(", ");
        }
        out.println();
      }
      out.println("  };");
      out.println("  public static void main(String[] args) {");
      out.println("    new Test().run();");
      out.println("  }");
      out.println("  void run() {");
      out.println("    int sum = 0;");
      out.println("    for (int i = 0; i < data.length; ++i) {");
      out.println("      sum += data[i];");
      out.println("    }");
      out.println("    // Sum should be " + sum);
      out.println("    System.out.println(sum);");
      out.println("  }");
      out.println("}");
    }
    try (FileOutputStream f = new FileOutputStream("../tests/codegen/conv_01p/out.expected");
        PrintStream out = new PrintStream(f)) {
      out.println(sum);
    }
  }
}

// Regression test for Bitbucket issue #183:
// https://bitbucket.org/extendj/extendj/issues/183/prettyprint-of-try-with-resources
// .result=COMPILE_OUT
// .options=XprettyPrint
import java.util.*;
import java.io.*;
public class Test {
  void f(File f1, File f2) throws IOException {
    try (FileInputStream in1 = new FileInputStream(f1)) {
    }
    try (FileInputStream in1 = new FileInputStream(f1); FileInputStream in2 = new FileInputStream(f2)) {
    }
    try (FileInputStream in1 = new FileInputStream(f1); FileInputStream in2 = new FileInputStream(f2)) {
    } finally {
    }
    try (FileInputStream in1 = new FileInputStream(f1); FileInputStream in2 = new FileInputStream(f2)) {
    } finally {
      try (FileInputStream in1 = new FileInputStream(f1); FileInputStream in2 = new FileInputStream(f2)) {
        in1.read();
      } catch (IOException e) {
        System.out.println("oops");
        throw e;
      } catch (Exception e) {
      } catch (Throwable t) {
        throw t;
      } finally {
        System.out.println("done");
      }
    }
  }
}

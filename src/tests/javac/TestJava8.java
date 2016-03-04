package tests.javac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import core.TestProperties;
import core.TestRunner;
import core.Util;

/**
 * A parameterized Junit test to test Javac.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
@RunWith(Parameterized.class)
public class TestJava8 {

  private static final TestProperties properties = new TestProperties();
  static {
    properties.setProperty("compiler", "javac");
    properties.setProperty("extraOptions", "-Xlint:none");
    properties.exclude("pretty-print");

    properties.exclude("jsr335/Parsing");
    //properties.exclude("jsr335/Semantics/LambdaExpressions/ReservedKeyword");
    properties.exclude(tests.Tests.FAILING);
    properties.exclude(tests.Tests.EXCLUDE_JAVA8);
    properties.exclude(tests.Tests.EXTENDJ_ONLY);
  }

  private final String testDir;

  /**
   * Construct a new JastAdd test
   * @param testDir The base directory for the test
   */
  public TestJava8(String testDir) {
    this.testDir = testDir;
  }

  /**
   * Run the JastAdd test
   */
  @Test
  public void runTest() {
    TestRunner.runTest(testDir, properties);
  }

  @SuppressWarnings("javadoc")
  @Parameters(name = "{0}")
  public static Iterable<Object[]> getTests() {
    return Util.getTests(properties);
  }
}

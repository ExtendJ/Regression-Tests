package tests.extendj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import core.TestProperties;
import core.TestRunner;
import core.Util;

/**
 * A parameterized Junit test to test ExtendJ.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
@RunWith(Parameterized.class)
public class TestJava8 {

  private static final TestProperties properties = new TestProperties();
  static {
    properties.setProperty("compiler", "extendj");
    properties.setProperty("extendj.jar", "extendj.jar"); // Default to local compiler Jar.
    properties.exclude(tests.Tests.FAILING);
    properties.exclude(tests.Tests.EXCLUDE_JAVA8);

    // TODO(joqvist): These are bugs in ExtendJ that need to be fixed, but it is not helpful
    // to see the test failures for each run. That could potentially hide new regressions.
    properties.exclude("enum/final_07f");
    properties.exclude("enum/final_09f");
    properties.exclude("generics/override_02f");
    properties.exclude("generics/override_10f");
    properties.exclude("generics/override_12f");
    properties.exclude("jsr335/Semantics/ConstructorReferenceAnalysis/ClassReferences/ShouldFail/syntax03");
    properties.exclude("jsr335/Semantics/ConstructorReferenceAnalysis/ClassReferences/ShouldFail/syntax04");
    properties.exclude("jsr335/Semantics/FunctionalInterfaces/ReturnTypeSubstitutable/ShouldCompile/syntax22");
    properties.exclude("jsr335/Semantics/FunctionalInterfaces/ReturnTypeSubstitutable/ShouldFail/syntax11");
    properties.exclude("jsr335/Semantics/FunctionalInterfaces/ReturnTypeSubstitutable/ShouldFail/syntax12");
    properties.exclude("jsr335/Semantics/FunctionalInterfaces/Signature/ShouldCompile/syntax08");
    properties.exclude("jsr335/Semantics/FunctionalInterfaces/Signature/ShouldCompile/syntax12");
    properties.exclude("jsr335/Semantics/FunctionalInterfaces/Signature/ShouldFail/syntax08");
    properties.exclude("jsr335/Semantics/FunctionalInterfaces/TypeParameters/ShouldFail/syntax02");
    properties.exclude("jsr335/Semantics/FunctionalInterfaces/TypeParameters/ShouldFail/syntax03");
    properties.exclude("jsr335/Semantics/LambdaTypeAnalysis/AssignmentContext/ShouldCompile/syntax28");
    properties.exclude("jsr335/Semantics/MethodReferenceAnalysis/TypeMethodReference/ShouldCompile/syntax10");
    properties.exclude("jsr335/Semantics/MethodReferenceAnalysis/TypeMethodReference/ShouldCompile/syntax11");
    properties.exclude("jsr335/Semantics/MethodReferenceAnalysis/TypeMethodReference/ShouldCompile/syntax12");
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

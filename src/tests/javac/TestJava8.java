package tests.javac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import core.TestProperties;
import core.TestRunner;
import core.Util;

/**
 * A parameterized Junit test to test JastAdd 2 & 3
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
@RunWith(Parameterized.class)
public class TestJava8 {

	private static final TestProperties properties = new TestProperties();
	static {
		properties.setProperty("compiler", "javac");
		properties.exclude("mult");
		properties.exclude("jastaddj");
		properties.exclude("generics/constructor01", "type/conditional_expr02");
		properties.exclude("jsr335/Parsing");
		
		
		//TEMP EXCLUDES
		properties.exclude("annotation");
		properties.exclude("codegen");
		properties.exclude("exception");
		properties.exclude("expr");
		properties.exclude("generics");
		properties.exclude("name");
		properties.exclude("os");
		properties.exclude("stmt");
		properties.exclude("type");
		properties.exclude("classes");
		properties.exclude("jastaddj");
		properties.exclude("jsr335/Parsing");	
		properties.exclude("runtime");
		properties.exclude("jsr334");
		properties.exclude("jsr335/Semantics/LambdaExpressions/ReservedKeyword");
		properties.exclude("jsr335/Semantics/LambdaExpressions");
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

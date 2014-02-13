package tests.jastaddj;

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
		properties.setProperty("compiler", "jastaddj");
		properties.setProperty("jastaddj.jar", "jastaddj.jar");// local jastaddj.jar
		properties.exclude("generics/constructor01", "type/conditional_expr02");
		
		//Temp excludes:
		properties.exclude("jsr335/Parsing");
		properties.exclude("annotation");
		properties.exclude("codegen");
		properties.exclude("expr");
		properties.exclude("generics");
		properties.exclude("jsr334");
		properties.exclude("name");
		properties.exclude("os");
		properties.exclude("runtime");
		properties.exclude("stmt");
		properties.exclude("type");
		properties.exclude("exception");
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

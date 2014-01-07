package tests.jastaddj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import core.TestProperties;
import core.TestRunner;
import core.Util;

/**
 * A parameterized Junit test to test JastAddJ
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
@RunWith(Parameterized.class)
public class TestJava6 {

	private static final TestProperties properties = new TestProperties();
	static {
		properties.setProperty("compiler", "jastaddj");
		properties.setProperty("jastaddj.jar", "jastaddj.jar");// local jastaddj.jar
		properties.exclude("runtime/js334");
		properties.exclude("js334");
		properties.exclude("lambda");
		properties.exclude("generics/constructor01", "type/conditional_expr02");
	}

	private final String testDir;

	/**
	 * Construct a new JastAdd test
	 * @param testDir The base directory for the test
	 */
	public TestJava6(String testDir) {
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

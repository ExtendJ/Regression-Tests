package tests;

import java.io.File;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import core.JJTestRunner;
import core.Util;

/**
 * A parameterized Junit test to test JastAdd 2 & 3
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
@RunWith(Parameterized.class)
public class TestJavac {
	
	private static final String PROPERTIES_FILE = "javac.test";
	private static final Properties properties =
			Util.getProperties(new File(PROPERTIES_FILE));
	private final String testDir;

	/**
	 * Construct a new JastAdd test
	 * @param testDir The base directory for the test
	 */
	public TestJavac(String testDir) {
		this.testDir = testDir;
	}
	
	/**
	 * Run the JastAdd test
	 */
	@Test
	public void runTest() {
		JJTestRunner.runTest(testDir, properties);
	}
	
	@SuppressWarnings("javadoc")
	@Parameters(name = "{0}")
	public static Iterable<Object[]> getTests() {
		return Util.getTests(properties);
	}
}

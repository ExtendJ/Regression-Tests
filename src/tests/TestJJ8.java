package tests;

import java.io.File;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import core.TestRunner;
import core.Util;

/**
 * A parameterized Junit test to test JastAdd 2 & 3
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
@RunWith(Parameterized.class)
public class TestJJ8 {
	
	private static final String PROPERTIES_FILE = "jj8.test";
	private static final Properties properties =
			Util.getProperties(new File(PROPERTIES_FILE));
	private final String testDir;

	/**
	 * Construct a new JastAdd test
	 * @param testDir The base directory for the test
	 */
	public TestJJ8(String testDir) {
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

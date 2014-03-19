package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility methods for JastAdd testing
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public class Util {

	/**
	 * Root directory for all tests
	 */
	public static final String TEST_ROOT = "tests";

	/**
	 * Root directory for test output
	 */
	public static final String TEMP_ROOT = "tmp";

	/**
	 * Find all test directories
	 * @param testRoot
	 * @param testDirs
	 * @param excludes
	 */
	private static void addChildTestDirs(File testRoot, Collection<Object[]> testDirs,
		Collection<String> excludes) {

		for (File child: testRoot.listFiles()) {
			addTestDir(child, testDirs, excludes);
		}
	}

	private static void addTestDir(File dir,
			Collection<Object[]> testDirs, Collection<String> excludes) {

		if (dir.isDirectory()) {
			String path = dir.getPath().replace(File.separatorChar, '/');
			if (path.startsWith(TEST_ROOT + "/"))
				path = path.substring(TEST_ROOT.length()+1);
			for (String exclude: excludes) {
				if (path.startsWith(exclude)) {
					return;
				}
			}
			File resultFile = new File(dir, "Test.java");
			File propertiesFile = new File(dir, "Test.properties");
			if (resultFile.isFile() || propertiesFile.isFile()) {
				if (!skipTest(dir)) {
					testDirs.add(new Object[] { path });
				}
			} else {
				addChildTestDirs(dir, testDirs, excludes);
			}
		}
	}

	private static void addByPattern(File root, String pattern,
			Collection<Object[]> testDirs, Collection<String> excludes) {
		if (pattern.isEmpty()) {
			addTestDir(root, testDirs, excludes);
		} else {
			int index = pattern.indexOf('/');
			String part, rest;
			if (index == -1) {
				part = pattern;
				rest = "";
			} else {
				part = pattern.substring(0, index);
				rest = pattern.substring(index+1, pattern.length());
			}
			if (part.indexOf('*') == -1) {
				addByPattern(new File(root, part), rest, testDirs, excludes);
			} else if (part.equals("**")) {
				addByPattern(root, rest, testDirs, excludes);
				addByPattern(root, "*/**/" + rest, testDirs, excludes);
			} else if (root.isDirectory()) {
				for (File file: root.listFiles()) {
					if (patternMatch(file.getName().toCharArray(), 0, part.toCharArray(), 0)) {
						addByPattern(file, rest, testDirs, excludes);
					}
				}
			}
		}
	}

	private static boolean patternMatch(char[] name, int ni, char[] pattern, int pi) {

		if (ni >= name.length && pi >= pattern.length) {
			return true;
		}

		char p = pattern[pi];

		if (p == '*') {
			if (ni >= name.length) {
				return patternMatch(name, ni, pattern, pi+1);
			} else {
				char n = name[ni];
				return patternMatch(name, ni+1, pattern, pi) || patternMatch(name, ni+1, pattern, pi+1);
			}
		} else {
			if (ni >= name.length) {
				return false;
			} else {
				char n = name[ni];
				return n == p && patternMatch(name, ni+1, pattern, pi+1);
			}
		}
	}

	/**
	 * @param testDir
	 * @return <code>true</code> if the test should be skipped
	 */
	private static boolean skipTest(File testDir) {
		return false;
	}

	/**
	 * @param properties
	 * @return A collection of String arrays containing the test directories
	 */
	public static Iterable<Object[]> getTests(TestProperties properties) {
		List<Object[]> testDirs = new LinkedList<Object[]>();

		Collection<String> includes = properties.includes();
		Collection<String> excludes = properties.excludes();

		if (includes.isEmpty()) {
			addTestDir(new File(TEST_ROOT), testDirs, excludes);
		} else {
			for (String include: includes) {
				addByPattern(new File(TEST_ROOT), include.replace('\\', '/'),
						testDirs, excludes);
			}
		}

		// sort the tests lexicographically
		Collections.sort(testDirs, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] a, Object[] b) {
				return ((String)a[0]).compareTo((String)b[0]);
			}
		});
		return testDirs;
	}

	/**
	 * @param propertiesFile
	 * @return The properties loaded from the given file
	 */
	public static TestProperties getProperties(File propertiesFile) {
		TestProperties properties = new TestProperties();
		try {
			FileInputStream in = new FileInputStream(propertiesFile);
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}

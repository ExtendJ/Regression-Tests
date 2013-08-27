package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Utility methods for running JastAdd unit tests.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public class TestRunner {

	private static String TEST_FRAMEWORK = "framework";

	/**
	 * Run the unit test in testDir with the given JastAdd configuration.
	 * @param testName
	 * @param testSuiteProperties
	 */
	public static void runTest(String testName, Properties testSuiteProperties) {
		String testDir = Util.TEST_ROOT + "/" + testName;
		Properties testProperties = Util.getProperties(new File(testDir, "Test.properties"));
		testProperties.setProperty("compiler", testSuiteProperties.getProperty("compiler", "jastaddj"));
		Result expected = getExpectedResult(testProperties);

		File tmpDir = setupTemporaryDirectory(testDir);

		Compiler compiler = getCompiler(testSuiteProperties);

		// Compile generated code with the selected compiler
		compileSources(compiler, testProperties, tmpDir, testDir, expected);

		if (expected == Result.COMPILE_PASSED ||
				expected == Result.COMPILE_FAILED ||
				expected == Result.COMPILE_WARNING) {

			return;
		}

		// Execute the compiled code
		String stdErr = executeCode(testProperties, tmpDir, testDir, expected);
		if (!stdErr.isEmpty()) {
			fail("Standard error not empty:\n" + stdErr);
		}

		if (expected == Result.EXEC_PASSED ||
				expected == Result.EXEC_FAILED) {

			return;
		}

		// Compare the output with the expected output
		compareOutput(tmpDir, testDir);
	}

	private static Compiler getCompiler(Properties props) {
		if (props.getProperty("compiler", "jastaddj").equals("jastaddj")) {
			// compile with jastaddj
			return new JastAddJCompiler(props.getProperty("jastaddj.jar", "lib/JavaCompiler.jar"), true);
		} else {
			// compile with javac
			return new JavacCompiler(false);
		}
	}

	/**
	 * Set up the temporary directory - create it if it does not exist
	 * and clean it if it does already exist.
	 * @param testDir The temporary directory
	 */
	private static File setupTemporaryDirectory(String testDir) {
		String tmpDirName = testDir;
		if (tmpDirName.startsWith("tests")) {
			tmpDirName = tmpDirName.substring(6);
		}

		File tmpDir = new File("tmp" + File.separator + tmpDirName);

		if (!tmpDir.exists()) {
			// create directory with intermediate parent directories
			tmpDir.mkdirs();
		} else {
			// clean temporary directory
			cleanDirectory(tmpDir);
		}
		return tmpDir;
	}

	/**
	 * Recursively remove all files and folders in a directory
	 * @param dir The directory to nuke
	 */
	private static void cleanDirectory(File dir) {
		for (File file: dir.listFiles()) {
			if (!file.isDirectory()) {
				file.delete();
			} else {
				cleanDirectory(file);
				file.delete();
			}
		}
	}

	private static Result getExpectedResult(Properties props) {

		if (!props.containsKey("result"))
			return Result.OUTPUT_PASSED;

		String result = props.getProperty("result");
		if (result.equals("COMPILE_PASSED") || result.equals("COMPILE_PASS"))
			return Result.COMPILE_PASSED;
		else if (result.equals("COMPILE_FAILED") || result.equals("COMPILE_FAIL"))
			return Result.COMPILE_FAILED;
		else if (result.equals("COMPILE_WARNING") || result.equals("COMPILE_WARN"))
			return Result.COMPILE_WARNING;
		else if (result.equals("EXEC_PASSED") || result.equals("EXEC_PASS"))
			return Result.EXEC_PASSED;
		else if (result.equals("EXEC_FAILED") || result.equals("EXEC_FAIL"))
			return Result.EXEC_FAILED;
		else if (result.equals("OUTPUT_PASSED") || result.equals("OUTPUT_PASS"))
			return Result.OUTPUT_PASSED;
		else {
			fail("Unknown result option: " + result);
			return Result.OUTPUT_PASSED;
		}
	}

	/**
	 * Compare the generated output to the expected output
	 */
	private static void compareOutput(File tmpDir, String testDir) {
		try {
			Scanner output;
			Scanner expected;

			File outputFile = new File(tmpDir, "out");
			if (outputFile.isFile()) {
				output = new Scanner(outputFile);
			} else {
				output = new Scanner(new ByteArrayInputStream(new byte[0]));
			}
			File expectedFile = new File(testDir, "out.expected");
			if (expectedFile.isFile()) {
				expected = new Scanner(expectedFile);
			} else {
				expected = new Scanner(new ByteArrayInputStream(new byte[0]));
			}
			int line = 0;
			while (expected.hasNextLine()) {
				line += 1;
				if (!output.hasNextLine()) {
					fail("Too few lines of output");
					output.close();
					expected.close();
					return;
				}
				String outLine = output.nextLine();
				String expectedLine = expected.nextLine();
				if (!outLine.equals(expectedLine)) {
					output.close();
					expected.close();
					assertEquals("Output differs from expected output at line " +
							line, expectedLine, outLine);
					return;
				}
			}
			if (output.hasNextLine()) {
				fail("Too many lines of output");
			}
			output.close();
			expected.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Run the compiled test program
	 * @param props
	 * @param tmpDir
	 * @param testDir
	 * @param expected
	 * @return The standard error content
	 */
	private static String executeCode(Properties props, File tmpDir,
			String testDir, Result expected) {

		StringBuffer errors = new StringBuffer();

		String classpath = tmpDir.getPath();
		if (props.containsKey("classpath")) {
			classpath += File.pathSeparator + props.getProperty("classpath");
		}

		try {
			Process p = Runtime.getRuntime().exec("java -classpath " +
					classpath + " Test");
			// write output to file
			InputStream in = p.getInputStream();
			OutputStream out = new FileOutputStream(new File(tmpDir, "out"));
			InputStream errIn = p.getErrorStream();
			OutputStream errOut = new FileOutputStream(new File(tmpDir, "err"));
			int data;
			while ((data = in.read()) != -1) {
				out.write(data);
			}
			out.close();
			while ((data = errIn.read()) != -1) {
				errOut.write(data);
				errors.append((char) data);
			}
			errOut.close();
			int exitValue = p.waitFor();
			if (exitValue == 0) {
				if (expected == Result.EXEC_FAILED) {
					fail("Code execution passed when expected to fail");
				}
				return errors.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (expected != Result.EXEC_FAILED) {
			fail("Code execution failed when expected to pass:\n" +
					errors.toString());
		}

		return errors.toString();
	}

	/**
	 * Compile generated source files.
	 * @param props
	 * @param tmpDir
	 * @param testDir
	 * @param expected
	 */
	private static void compileSources(Compiler compiler, Properties props, File tmpDir,
			String testDir, Result expected) {

		String compileOrder = props.getProperty("compile_order", "");
		if (!compileOrder.isEmpty()) {
			// TODO!!
			// Compile files in custom order
			for (String sourceObj : compileOrder.split(",")) {
				File sourceFile = new File(testDir, sourceObj.trim());
				Collection<String> sourceFiles = new LinkedList<String>();
				sourceFiles.add(sourceFile.getPath());
				compileSources(compiler, props, tmpDir, testDir, expected, sourceFiles);
			}
		} else {
			Collection<String> sourceFiles = collectSourceFiles(tmpDir);
			sourceFiles.addAll(collectSourceFiles(new File(testDir)));
			compileSources(compiler, props, tmpDir, testDir, expected, sourceFiles);
		}
	}

	private static void compileSources(Compiler compiler, Properties props, File tmpDir,
			String testDir, Result expected, Collection<String> sourceFiles) {

		List<String> args = new ArrayList<String>();

		args.add("-d");
		args.add(tmpDir.getPath());

		args.add("-classpath");
		String classpath = TEST_FRAMEWORK;
		if (props.containsKey("classpath")) {
			classpath += File.pathSeparator + props.getProperty("classpath");
			classpath = classpath.replaceAll("@TEST_DIR@", testDir);
			classpath = classpath.replaceAll("@TMP_DIR@", tmpDir.getPath());
		}
		args.add(classpath);

		for (String sourceFile: sourceFiles) {
			args.add(sourceFile);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayOutputStream err = new ByteArrayOutputStream();

		try {
			String[] arguments = args.toArray(new String[args.size()]);

			int exitValue = -1;
			exitValue = compiler.compile(arguments, out, err);

			String errors = err.toString();

			if (exitValue == 0) {
				Result result = errors.isEmpty() ? Result.COMPILE_PASSED : Result.COMPILE_WARNING;
				if (result != expected) {
					if (result == Result.COMPILE_WARNING)
						fail("Compilation produced unexpected warning:\n" + errors);
					else if (expected == Result.COMPILE_FAILED)
						fail("Compilation passed when expected to fail!");
				}
			} else {
				if (expected != Result.COMPILE_FAILED) {
					fail("Compilation failed when expected to pass:\n" + errors);
				}
			}
		} finally {
			// close streams
			try { out.close(); } catch (IOException e) { }
			try { err.close(); } catch (IOException e) { }
		}
	}

	/**
	 * Collect all source file names in the test directory
	 * @param dir
	 * @return
	 */
	private static Collection<String> collectSourceFiles(File dir) {
		Collection<String> sourceFiles = new LinkedList<String>();
		for (File file: dir.listFiles()) {
			if (!file.isDirectory() && file.getName().endsWith(".java")) {
				sourceFiles.add(file.getPath());
			} else if (file.isDirectory()) {
				sourceFiles.addAll(collectSourceFiles(file));
			}
		}
		return sourceFiles;
	}

}

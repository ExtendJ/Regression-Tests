package core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Utility methods for running JastAdd unit tests.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public class TestRunner {

	private static String SYS_LINE_SEP = System.getProperty("line.separator");
	private static String TEST_FRAMEWORK = "framework";

	/**
	 * Run the unit test in testDir with the given JastAdd configuration.
	 * @param testName
	 * @param testSuiteProperties
	 */
	public static void runTest(String testName, Properties testSuiteProperties) {
		File testDir = new File(Util.TEST_ROOT, testName);
		Properties testProperties = Util.getProperties(new File(testDir, "Test.properties"));
		testProperties.setProperty("compiler", testSuiteProperties.getProperty("compiler", "jastaddj"));
		Result expected = getExpectedResult(testProperties);

		File tmpDir = setupTemporaryDirectory(testName);

		Compiler compiler = getCompiler(testSuiteProperties);

		if (expected == Result.TREE_PASSED) {
			dumpStructurePrint(compiler, tmpDir, testDir);
			compareOutput(tmpDir, testDir);
			return;
		}

		if (expected == Result.FRONTEND_PASSED || expected == Result.FRONTEND_FAILED) {
			String errors = getFrontendErrors(compiler, tmpDir, testDir);
			if(expected == Result.FRONTEND_PASSED && errors.length() != 0) {
				fail(errors);
			}
			else if(expected == Result.FRONTEND_FAILED && errors.length() == 0) {
				fail("Expected semantic errors in front end, but was none");
			}

			return;
		}

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
	 * @param testName The temporary directory
	 */
	private static File setupTemporaryDirectory(String testName) {
		File tmpDir = new File(Util.TEMP_ROOT, testName);

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
		else if (result.equals("TREE_PASSED") || result.equals("TREE_PASS"))
			return Result.TREE_PASSED;
		else if (result.equals("FRONTEND_PASSED") || result.equals("FRONTEND_PASS"))
			return Result.FRONTEND_PASSED;
		else if (result.equals("FRONTEND_FAILED") || result.equals("FRONTEND_FAIL"))
			return Result.FRONTEND_FAILED;
		else {
			fail("Unknown result option: " + result);
			return Result.OUTPUT_PASSED;
		}
	}

	/**
	 * Compare the generated output to the expected output
	 */
	private static void compareOutput(File tmpDir, File testDir) {
		try {
			File expected = new File(testDir, "out.expected");
			File actual = new File(tmpDir, "out");
			assertEquals("Output files differ", readFileToString(expected),
					readFileToString(actual));

			expected = new File(testDir, "err.expected");
			actual = new File(tmpDir, "err");
			assertEquals("Error output files differ", readFileToString(expected),
					readFileToString(actual));
		} catch (IOException e) {
			fail("IOException occurred while comparing output: " + e.getMessage());
		}
	}

	/**
	 * <p>Reads an entire file to a string object.
	 *
	 * <p>If the file does not exist an empty string is returned.
	 *
	 * <p>The system dependent line separator char sequence is replaced by
	 * the newline character.
	 *
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	private static String readFileToString(File file) throws FileNotFoundException {
		if (!file.isFile()) {
			return "";
		}

		Scanner scanner = new Scanner(file);
		scanner.useDelimiter("\\Z");
		String theString = scanner.hasNext() ? scanner.next() : "";
		theString = theString.replace(SYS_LINE_SEP, "\n").trim();
		scanner.close();
		return theString;
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
			File testDir, Result expected) {

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
			File testDir, Result expected) {

		String compileOrder = props.getProperty("compile_order", "");
		String sourceOrder = props.getProperty("source_order", "");
		if (!compileOrder.isEmpty()) {
			// Compile files in custom order
			for (String sourceObj : compileOrder.split(",")) {
				File sourceFile = new File(testDir, sourceObj.trim());
				Collection<String> sourceFiles = new LinkedList<String>();
				sourceFiles.add(sourceFile.getPath());
				compileSources(compiler, props, tmpDir, testDir, expected, sourceFiles);
			}
		} else if (!sourceOrder.isEmpty()) {
			// use custom source order
			Collection<String> sourceFiles = new LinkedList<String>();
			for (String sourceObj : sourceOrder.split(",")) {
				File sourceFile = new File(testDir, sourceObj.trim());
				sourceFiles.add(sourceFile.getPath());
			}
			compileSources(compiler, props, tmpDir, testDir, expected, sourceFiles);
		} else {
			Collection<String> sourceFiles = collectSourceFiles(tmpDir);
			sourceFiles.addAll(collectSourceFiles(testDir));
			compileSources(compiler, props, tmpDir, testDir, expected, sourceFiles);
		}
	}

	private static void compileSources(Compiler compiler, Properties props, File tmpDir,
			File testDir, Result expected, Collection<String> sourceFiles) {

		List<String> args = new ArrayList<String>();

		args.add("-d");
		args.add(tmpDir.getPath());

		args.add("-classpath");
		String classpath = TEST_FRAMEWORK;
		if (props.containsKey("classpath")) {
			classpath += File.pathSeparator + props.getProperty("classpath");
			classpath = classpath.replaceAll("@TEST_DIR@", testDir.getPath());
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

	private static String getFrontendErrors(Compiler compiler, File tmpDir, File testDir) {
		Collection<String> sourceFiles = collectSourceFiles(tmpDir);
		sourceFiles.addAll(collectSourceFiles(testDir));
		if(sourceFiles.size() != 1) {
			fail("A single file per test required for parse tests");
		}
		String errors = null;
		for(String fileName : sourceFiles) {
			errors = ((JastAddJCompiler)compiler).dumpFrontendErrors(fileName);
		}

		return errors.trim();
	}

	/**
	 * Dump the structured print in tmp folder for output check.
	 * This assumes JastAddJ is being used, do not use parse tests
	 * with javac.
	 */
	private static void dumpStructurePrint(Compiler compiler, File tmpDir, File testDir) {
		if (compiler instanceof JavacCompiler) {
			fail("Can not test javac parse tree");
			return;
		}
		Collection<String> sourceFiles = collectSourceFiles(tmpDir);
		sourceFiles.addAll(collectSourceFiles(testDir));
		if(sourceFiles.size() != 1) {
			fail("A single file per test required for parse tests");
			return;
		}
		String program = null;
		for(String fileName : sourceFiles) {
			program = ((JastAddJCompiler)compiler).dumpStructurePrint(fileName);
		}

		try {
			PrintWriter out = new PrintWriter(tmpDir.getPath() + File.separator + "out");
			out.print(program.trim());
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
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

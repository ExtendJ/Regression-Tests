package core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
		TestConfiguration config = new TestConfiguration(testName, testSuiteProperties);

		Result expected = config.expected;

		// Compile generated code with the selected compiler
		compileSources(config);

		if (expected == Result.COMPILE_OUTPUT) {
			compareOutput("compile.out", config.tmpDir, config.testDir);
			return;
		}

		if (expected == Result.COMPILE_PASSED ||
				expected == Result.COMPILE_FAILED ||
				expected == Result.COMPILE_WARNING) {

			return;
		}

		if (config.expected == Result.COMPILE_ERR_OUTPUT) {
			// TODO implement compiler output logging!!
			compareCompileErrOutput(config.tmpDir, config.testDir);
			return;
		}

		// Execute the compiled code
		executeCode(config.testProperties, config.tmpDir, config.testDir, expected);

		// Compare the output with the expected output
		compareErrorOutput(config.tmpDir, config.testDir);
		compareOutput("out", config.tmpDir, config.testDir);
	}

	/**
	 * Compare the error output from JastAdd
	 */
	private static void compareCompileErrOutput(File tmpDir, File testDir) {
		try {
			File expected = expectedCompileErrorOutput(testDir);
			File actual = new File(tmpDir, "compile.err");
			assertEquals("Error output files differ",
					readFileToString(expected),
					readFileToString(actual).replace('\\', '/'));
		} catch (IOException e) {
			fail("IOException occurred while comparing JastAdd error output: " + e.getMessage());
		}
	}

	private static File expectedCompileErrorOutput(File testDir) {
		boolean windows = System.getProperty("os.name").startsWith("Windows");
		if (windows) {
			// First try opening .win file.
			File file = new File(testDir, "compile.err.expected.win");
			if (file.isFile()) {
				return file;
      }
		}
		// Open default file.
		return new File(testDir, "compile.err.expected");
	}

	/**
	 * Compare the generated error output to the expected error output
	 */
	private static void compareErrorOutput(File tmpDir, File testDir) {
		try {
			File expected = new File(testDir, "err.expected");
			File actual = new File(tmpDir, "err");
			assertEquals("Error output files differ", readFileToString(expected),
					readFileToString(actual));
		} catch (IOException e) {
			fail("IOException occurred while comparing error output: " + e.getMessage());
		}
	}

	/**
	 * Compare the generated output to the expected output
	 */
	private static void compareOutput(String file, File tmpDir, File testDir) {
		try {
			File expected = new File(testDir, file + ".expected");
			File actual = new File(tmpDir, file);
			assertEquals("Output files differ", readFileToString(expected),
					readFileToString(actual));
		} catch (IOException e) {
			fail("IOException occurred while comparing output: " + e.getMessage());
		}
	}

	/**
	 * Reads an entire file to a string object.
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
	private static String readFileToString(File file) throws IOException {
		if (!file.isFile()) {
			return "";
		}

		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		while (true) {
			int size = in.read(buffer);
			if (size == -1) {
				break;
			}
			out.write(buffer, 0, size);
		}
		in.close();
		out.close();
		return out.toString("UTF-8").replace(SYS_LINE_SEP, "\n").trim();
	}

	/**
	 * Run the compiled test program.
	 * @param props
	 * @param tmpDir
	 * @param testDir
	 * @param expected
	 */
	private static void executeCode(Properties props, File tmpDir,
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
			}
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (expected != Result.EXEC_FAILED) {
			fail("Code execution failed when expected to pass:\n" +
					errors.toString());
		}
	}

	/**
	 * Compile generated source files.
	 * @param props
	 * @param tmpDir
	 * @param testDir
	 * @param expected
	 */
	private static void compileSources(TestConfiguration config) {
		Properties props = config.testProperties;

		String compileOrder = props.getProperty("compile_order", "");
		String sourceOrder = props.getProperty("source_order", "");
		if (!compileOrder.isEmpty()) {
			// Compile files in custom order
			for (String sourceObj : compileOrder.split(",")) {
				File sourceFile = new File(config.testDir, sourceObj.trim());
				Collection<String> sourceFiles = new LinkedList<String>();
				sourceFiles.add(sourceFile.getPath());
				compileSources(config, sourceFiles);
			}
		} else if (!sourceOrder.isEmpty()) {
			// use custom source order
			Collection<String> sourceFiles = new LinkedList<String>();
			for (String sourceObj : sourceOrder.split(",")) {
				File sourceFile = new File(config.testDir, sourceObj.trim());
				sourceFiles.add(sourceFile.getPath());
			}
			compileSources(config, sourceFiles);
		} else {
			String sources = props.getProperty("sources", "");
			Collection<String> sourceFiles;
			if (sources.isEmpty()) {
				sourceFiles = collectSourceFiles(config.tmpDir);
				sourceFiles.addAll(collectSourceFiles(config.testDir));
			} else {
				sourceFiles = new LinkedList<String>();
				// use source list from test properties
				for (String sourceFile: sources.split(",")) {
					sourceFile = sourceFile.replace('/', File.separatorChar);
					sourceFiles.add(config.testDir.getPath() + File.separator + sourceFile);
				}
			}
			compileSources(config, sourceFiles);
		}
	}

	private static void compileSources(TestConfiguration config, Collection<String> sourceFiles) {
		List<String> args = new ArrayList<String>();

		args.add("-d");
		args.add(config.tmpDir.getPath());

		args.add("-classpath");
		String classpath = TEST_FRAMEWORK;
		String addClasspath = config.testProperties.getProperty("classpath", "").trim();
		if (!addClasspath.isEmpty()) {
			addClasspath = addClasspath.replaceAll("@TEST_DIR@", config.testDir.getPath());
			addClasspath = addClasspath.replaceAll("@TMP_DIR@", config.tmpDir.getPath());
			addClasspath = addClasspath.replaceAll("@TEMP_DIR@", config.tmpDir.getPath());
			classpath += File.pathSeparator + addClasspath;
		}
		args.add(classpath);

		String sourcepath = config.testProperties.getProperty("sourcepath", "").trim();
		if (!sourcepath.isEmpty()) {
			args.add("-sourcepath");
			sourcepath = sourcepath.replaceAll("@TEST_DIR@", config.testDir.getPath());
			sourcepath = sourcepath.replaceAll("@TMP_DIR@", config.tmpDir.getPath());
			sourcepath = sourcepath.replaceAll("@TEMP_DIR@", config.tmpDir.getPath());
			args.add(sourcepath);
		}

		String options = config.testProperties.getProperty("options", "").trim();
		if (!options.isEmpty()) {
			// add compiler options
			for (String option : options.split(",")) {
				args.add("-" + option);
			}
		}

		for (String sourceFile: sourceFiles) {
			args.add(sourceFile);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayOutputStream err = new ByteArrayOutputStream();

		try {
			String[] arguments = args.toArray(new String[args.size()]);

			int exitValue = -1;
			exitValue = config.compiler.compile(arguments, out, err);

			if (err.size() > 0) {
				String errors = err.toString();
				try {
					PrintWriter file = new PrintWriter(new File(config.tmpDir, "compile.err"));
					file.append(errors);
					file.close();
				} catch (IOException e) {
					fail("Failed to write compile error output file!");
				}
			}

			if (config.expected == Result.COMPILE_OUTPUT && out.size() > 0) {
				String output = out.toString();
				try {
					PrintWriter file = new PrintWriter(new File(config.tmpDir, "compile.out"));
					file.append(output);
					file.close();
				} catch (IOException e) {
					fail("Failed to write compile error output file!");
				}
			}

			if (exitValue == 0) {
				Result result = err.size()==0 ? Result.COMPILE_PASSED : Result.COMPILE_WARNING;
				if (result != config.expected) {
					if (result == Result.COMPILE_WARNING) {
						fail("Compilation produced unexpected warning:\n" + err.toString());
					} else if (config.expected == Result.COMPILE_FAILED) {
						fail("Compilation passed when expected to fail!");
					}
				}
			} else {
				if (config.expected != Result.COMPILE_FAILED &&
					config.expected != Result.COMPILE_ERR_OUTPUT) {
					fail("Compilation failed when expected to pass:\n" + err.toString());
				}
			}

			if (err.size()>0 && config.verbose) {
				System.err.println(err.toString());
			}
		} finally {
			// close streams
			try { out.close(); } catch (IOException e) { }
			try { err.close(); } catch (IOException e) { }
		}
	}

	/**
	 * Collect all source file names in the test directory
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

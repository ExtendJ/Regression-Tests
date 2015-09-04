package core;

/**
 * Expected test result for a test.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public enum Result {
	/**
	 * The compilation failed
	 */
	COMPILE_FAILED,

	/**
	 * The compilation passed, without warnings
	 */
	COMPILE_PASSED,

	/**
	 * The compilation passed, but with a warning
	 */
	COMPILE_WARNING,

	/**
	 * The compilation failed, with some error message(s)
	 */
	COMPILE_ERR_OUTPUT,

	/**
	 * The compilation passed, with some output
	 */
	COMPILE_OUTPUT,

	/**
	 * Execution of the compiled code failed (exit status != 0)
	 */
	EXEC_FAILED,

	/**
	 * Execution succeeded (exit status == 0)
	 */
	EXEC_PASSED
}

package core;

/**
 * Possible results for a JastAdd unit test.
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
	 * Execution of the compiled code failed (exit status != 0)
	 */
	EXEC_FAILED,
	
	/**
	 * Execution succeeded (exit status == 0)
	 */
	EXEC_PASSED,
	
	/**
	 * The constructed parse was identical to the expected tree
	 */
	TREE_PASSED,
	
	/**
	 * Execution succeeded and the output was identical to the expected output
	 */
	OUTPUT_PASSED
}

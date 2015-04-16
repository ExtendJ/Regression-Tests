package tests;

/**
 * Default test sets.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public interface Tests {
	/**
	 * Tests expected to fail.
	 */
	String[] FAILING = {
		"generics/constructor_01f",
		"type/conditional_expr02",
		"type/ambiguous_01f",
	};

	/**
 	 * Java 7 tests
 	 */
	String[] JAVA7 = {
		"jsr334"
	};

	/**
 	 * Java 8 tests
 	 */
	String[] JAVA8 = {
		"jsr335"
	};

	/**
 	 * Tests that should be excluded for Java 8
 	 */
	String[] EXCLUDE_JAVA8 = {
		"jsr334/diamond/jsr334-type-inference-8"
	};
}

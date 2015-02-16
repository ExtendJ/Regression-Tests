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
		"type/ambiguous_03f",
		"enum/final_07f",
		"enum/final_09f",
		"generics/override_02f",
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
}

package tests.jastaddj;

/**
 * Default test sets.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public interface Tests {
	/**
	 * Tests expected to fail.
	 */
	String[] FAILING = {
		"mult",
		"generics/constructor01",
		"type/conditional_expr02",
		"type/ambiguous_01f",
	};
}

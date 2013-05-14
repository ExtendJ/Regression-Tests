package core;

import java.io.OutputStream;

/**
 * Abstract compiler class
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public abstract class Compiler {
	
	/**
	 * The name of this compiler
	 */
	public final String name;
	
	/**
	 * The version of this compiler
	 */
	public final String version;
	
	/**
	 * Constructor
	 * @param name
	 * @param version
	 */
	public Compiler(String name, String version) {
		this.name = name;
		this.version = version;
	}
	
	@Override
	public String toString() {
		return name + " (" + version + ")";
	}

	/**
	 * Run the compiler with the given arguments
	 * @param arguments
	 * @param out Output stream
	 * @param err Error output stream
	 * @return Exit value
	 */
	public abstract int compile(String[] arguments, OutputStream out, OutputStream err);
}

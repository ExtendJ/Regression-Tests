package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * javac compiler wrapper
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public class JavacCompiler extends Compiler {

	private final boolean newVM;

	/**
	 * Constructor
	 * @param newVM
	 */
	public JavacCompiler(boolean newVM) {
		super("javac", getVersion());

		this.newVM = newVM;
	}

	@Override
	public int compile(String[] arguments, OutputStream out, OutputStream err) {
		InputStream in = new ByteArrayInputStream(new byte[0]);
		return invoke(arguments, in, out, err);
	}

	/**
	 * @param arguments
	 * @param in
	 * @param out
	 * @param err
	 * @return Exit value of the compile process
	 */
	public int invoke(String[] arguments, InputStream in,
			final OutputStream out, final OutputStream err) {

		if (newVM) {
			StringBuffer cmd = new StringBuffer();
			// TODO(jesper): build the jar file
			cmd.append("java -jar tools/javacjar.jar");
			for (String arg: arguments) {
				cmd.append(" " + arg);
			}
			try {
				final Process p = Runtime.getRuntime().exec(cmd.toString());
				Thread errThread = new Thread() {
					@Override
					public void run() {
						PrintStream ps = new PrintStream(err);
						Scanner scanner = new Scanner(p.getErrorStream());
						while (scanner.hasNextLine()) {
							ps.println(scanner.nextLine());
						}
						scanner.close();
					}
				};
				Thread outThread = new Thread() {
					@Override
					public void run() {
						PrintStream ps = new PrintStream(out);
						Scanner scanner = new Scanner(p.getInputStream());
						while (scanner.hasNextLine()) {
							ps.println(scanner.nextLine());
						}
						scanner.close();
					}
				};
				errThread.start();
				outThread.start();
				int exitValue = p.waitFor();
				outThread.join();
				errThread.join();
				return exitValue;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}

		PrintStream stdout = System.out;
		try {
			System.setOut(new PrintStream(out));
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			// TODO(jesper): setting out as the output stream seems to not work...
			return compiler.run(in, null, err, arguments);
		} finally {
			System.setOut(stdout);
		}

	}

	/**
	 * @return The version of this compiler
	 */
	public static String getVersion() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		InputStream in = new ByteArrayInputStream(new byte[0]);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayOutputStream err = new ByteArrayOutputStream();
		compiler.run(in, out, err, new String[] { "-version" });
		return err.toString().trim();
	}
}

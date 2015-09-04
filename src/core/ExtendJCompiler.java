package core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

// can't use this if we want to benchmark older JJ versions
//import org.jastadd.jastaddj.JavaCompiler;

/**
 * ExtendJ compiler runner.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public class ExtendJCompiler extends Compiler {

	private final boolean newVM;
	private final String jarPath;

	/**
	 * Constructor
	 * @param jarPath Path to the ExtendJ jar
	 * @param newVM
	 */
	public ExtendJCompiler(String jarPath, boolean newVM) {
		super("extendj", jarPath);

		this.newVM = newVM;
		this.jarPath = jarPath;
	}

	@Override
	public int compile(String[] arguments, OutputStream out, OutputStream err) {

		InputStream in = new ByteArrayInputStream(new byte[0]);
		return invoke(arguments, in, out, err);
	}

	/**
	 * Invoke ExtendJ using reflection (in order to access main class in
	 * default package)
	 *
	 * @param arguments
	 * @param in
	 * @param outStream
	 * @param errStream
	 * @return Exit value of the compile process
	 */
	public int invoke(String[] arguments, InputStream in,
			OutputStream outStream, OutputStream errStream) {

		final PrintStream out = new PrintStream(outStream);
		final PrintStream err = new PrintStream(errStream);

		if (newVM) {
			StringBuffer cmd = new StringBuffer();
			cmd.append("java -Xmx2g -jar " + jarPath);
			for (String arg: arguments) {
				cmd.append(" " + arg);
			}
			try {
				final Process p = Runtime.getRuntime().exec(cmd.toString());

				final Collection<String> stderrErrors = new LinkedList<String>();
				new Thread() {
					@Override
					public void run() {
						Scanner scanner = new Scanner(p.getErrorStream());
						while (scanner.hasNextLine()) {
							String line = scanner.nextLine();
							if (stderrErrors.isEmpty() &&
									line.equals("java.lang.NullPointerException"))
								stderrErrors.add(line);
							err.println(line);
						}
						scanner.close();
					}
				}.start();

				// Some versions of ExtendJ print error messages on stdout
				// and do not return a nozero exit code on error.
				final Collection<String> stdoutErrors = new LinkedList<String>();
				new Thread() {
					@Override
					public void run() {
						Scanner scanner = new Scanner(p.getInputStream());
						while (scanner.hasNextLine()) {
							String line = scanner.nextLine();
							if (stdoutErrors.isEmpty() && line.equals("Errors:"))
								stdoutErrors.add(line);
							out.println(line);
						}
						scanner.close();
					}
				}.start();
				int exitValue = p.waitFor();
				if (!stdoutErrors.isEmpty() || !stderrErrors.isEmpty()) {
          return 1;
				} else {
          return exitValue;
        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}

		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		PrintStream stderr = System.err;
		System.setIn(in);
		System.setOut(out);
		System.setErr(err);

		try {
			Class<?> jjMain = Class.forName("org.jastadd.extendj.JavaCompiler");
			Method compile = jjMain.getMethod("compile", new Class[] { String[].class } );

			boolean result = (Boolean) compile.invoke(null, new Object[] { arguments });
			return result ? 0 : 1;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.setIn(stdin);
		System.setOut(stdout);
		System.setErr(stderr);

		return 1;
	}
}

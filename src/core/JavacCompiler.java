package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * javac compiler wrapper
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public class JavacCompiler extends Compiler {
	
	private boolean newVM;

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
		
		String[] args = new String[arguments.length + 1];
		System.arraycopy(arguments, 0, args, 0, arguments.length);
		args[args.length-1] = "-Xlint:none";
		
		InputStream in = new ByteArrayInputStream(new byte[0]);
		return invoke(args, in, out, err);
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
			// TODO build the jar file
			cmd.append("java -jar tools/javacjar.jar");
			for (String arg: arguments) {
				cmd.append(" " + arg);
			}
			try {
				final Process p = Runtime.getRuntime().exec(cmd.toString());
				new Thread() {
					@Override
					public void run() {
						PrintStream ps = new PrintStream(err);
						Scanner scanner = new Scanner(p.getErrorStream());
						while (scanner.hasNextLine())
							ps.println(scanner.nextLine());
						scanner.close();
					}
				}.start();
				new Thread() {
					@Override
					public void run() {
						PrintStream ps = new PrintStream(out);
						Scanner scanner = new Scanner(p.getInputStream());
						while (scanner.hasNextLine())
							ps.println(scanner.nextLine());
						scanner.close();
					}
				}.start();
				return p.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		return compiler.run(in, out, err, arguments);
		
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

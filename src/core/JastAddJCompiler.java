package core;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
 * JastAddJ
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public class JastAddJCompiler extends Compiler {
	
	private boolean newVM;
	private final String jarPath;

	/**
	 * Constructor
	 * @param jarPath Path to the JastAddJ jar
	 * @param newVM 
	 */
	public JastAddJCompiler(String jarPath, boolean newVM) {
		super("jastaddj", jarPath);
		
		this.newVM = newVM;
		this.jarPath = jarPath;
	}
	
	@Override
	public int compile(String[] arguments, OutputStream out, OutputStream err) {
		
		InputStream in = new ByteArrayInputStream(new byte[0]);
		return invoke(arguments, in, out, err);
	}
	
	// TODO use JavaDumpTree instead! /Jesper 2014-02-20
	/**
	 * Returns the StructurePrettyPrinted program received by parsing the file
	 * referred to in the argument path. 
	 * @param path to the program whose tree should be returned
	 * @return A string representing the parse tree
	 */
	public String dumpStructurePrint(String path) {
		StringBuffer cmd = new StringBuffer();
		cmd.append("java -Xmx2g -cp " + jarPath + " org.jastadd.jastaddj.JavaDumpStructuredPrint ");
		cmd.append(path);
		StringBuilder program = new StringBuilder();
		try {
			Process p = Runtime.getRuntime().exec(cmd.toString());
			p.waitFor();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String s = null;
		    while ((s = stdInput.readLine()) != null) {
		        program.append(s);
		        program.append('\n');
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return program.toString();
	}
	
	// TODO remove this /Jesper 2014-02-20
	public String dumpFrontendErrors(String path) {
		StringBuffer cmd = new StringBuffer();
		cmd.append("java -Xmx2g -cp " + jarPath + " org.jastadd.jastaddj.JavaDumpFrontendErrors ");
		cmd.append(path);
		StringBuilder errors = new StringBuilder();
		try {
			Process p = Runtime.getRuntime().exec(cmd.toString());
			p.waitFor();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String s = null;
		    while ((s = stdInput.readLine()) != null) {
		        errors.append(s);
		        errors.append('\n');
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return errors.toString();
	}
	
	/**
	 * Invoke JastAddJ using reflection (in order to access main class in
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
				
				// some versions of JastAddJ print error messages on stdout
				// and do not return a nozero exit code on error
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
				if (!stdoutErrors.isEmpty() || !stderrErrors.isEmpty()) return 1;
				else return exitValue;
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
		
		int exitValue = 1;
		// Warning: Lots of bad reflection Voodoo!
		try {
			// The only way we can access the JastAddJ JavaCompiler class is
			// using reflection (since it lies in the default package)
			Class<?> jjMain = Class.forName("JavaCompiler");
			Method compile = jjMain.getMethod("compile", new Class[] { String[].class } );
			
			// The method is not accessible, so we trick the JVM to think it is!
			compile.setAccessible(true);
			
			boolean result = (Boolean) compile.invoke(null, new Object[] { arguments });
			exitValue = result ? 0 : 1;
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
		
		return exitValue;
	}
}

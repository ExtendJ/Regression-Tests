/* Copyright (c) 2013-2016, Jesper Öqvist <jesper.oqvist@cs.lth.se>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *
 *   3. Neither the name of the copyright holder nor the names of its contributors
 *      may be used to endorse or promote products derived from this software
 *      without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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

/**
 * ExtendJ compiler runner.
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public class ExtendJCompiler extends Compiler {

  private final boolean newVM;
  private final boolean debug;
  private final String jarPath;

  /**
   * @param jarPath Path to the ExtendJ jar
   * @param newVM set to {@code true} if a new JVM should be started
   * @param debug set to {@code true} if remote debuggin should be enabled
   */
  public ExtendJCompiler(String jarPath, boolean newVM, boolean debug) {
    super("extendj", jarPath);

    this.newVM = newVM;
    this.debug = debug;
    this.jarPath = jarPath;
  }

  @Override
  public int compile(String[] arguments, OutputStream out, OutputStream err) {
    InputStream in = new ByteArrayInputStream(new byte[0]);
    return invoke(arguments, in, out, err);
  }

  /**
   * Invoke ExtendJ using reflection (in order to access main class in
   * default package).
   *
   * @return Exit value of the compile process
   */
  public int invoke(String[] arguments, InputStream in,
      OutputStream outStream, OutputStream errStream) {

    final PrintStream out = new PrintStream(outStream);
    final PrintStream err = new PrintStream(errStream);

    if (newVM || debug) {
      StringBuffer cmd = new StringBuffer();
      cmd.append("java -Xmx2g");
      if (debug) {
        cmd.append(" -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005");
      }
      cmd.append(" -jar " + jarPath);
      for (String arg : arguments) {
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

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;

// Taken from JSR335, part A

public class Test {
	interface A {
		List<String> foo(List<String> arg) throws IOException, SQLTransientException;
	}
	interface B {
		List foo(List<String> arg) throws EOFException, SQLException, TimeoutException;
	}
	interface C {
		List foo(List arg) throws Exception;
	}
	interface D extends A, B {}
	interface E extends A, B, C {}

	// D has descriptor (List<String>)->List<String> throws EOFException, SQLTransientException
	// E has descriptor (List)->List throws EOFException, SQLTransientException
	
	public static void main(String[] args) {
		D d = a -> { throw new EOFException(); };
		d = (List<String> a) -> { throw new SQLTransientException(); };
	}
}

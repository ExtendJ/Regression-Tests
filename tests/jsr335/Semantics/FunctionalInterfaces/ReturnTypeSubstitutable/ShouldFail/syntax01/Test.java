import java.util.ArrayList;
import java.util.List;

class Test {
	interface X { <A, T, S extends List<A>> List<T> execute(ArrayList<S>[][] a); }
	interface Y { <A, S, T extends List<A>> T execute(ArrayList<T>[][] a); }
	
	
	@FunctionalInterface
	interface Exec extends Y, X { }
}
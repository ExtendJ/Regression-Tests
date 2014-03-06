import java.util.ArrayList;
import java.util.List;

class Test {
	interface X { <S, T> int execute(T t, S s); }
	interface Y { <T, S> int execute(T t, S s); }
	
	@FunctionalInterface
	interface Exec extends Y, X { }
}
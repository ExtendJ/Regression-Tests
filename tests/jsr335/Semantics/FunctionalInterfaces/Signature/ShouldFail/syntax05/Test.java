// .result=COMPILE_FAIL
import java.util.ArrayList;
import java.util.List;

class Test {
	interface X { <S> int execute(ArrayList<? super ArrayList<?>> a); }
	interface Y { <T> int execute(ArrayList<? super ArrayList<T>> a); }
	
	@FunctionalInterface
	interface Exec extends Y, X { }
}
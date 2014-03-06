import java.util.ArrayList;
import java.util.List;

class Test {
	interface X { int execute(int a); }
	interface Y { int execute(); }
	
	@FunctionalInterface
	interface Exec extends Y, X { }
}
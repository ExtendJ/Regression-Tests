import java.util.*;


class Test {
	
	interface X { void execute(int a); }
	interface Y { void execute(long a); }
		
	@FunctionalInterface
	interface Exec extends X, Y { }
}
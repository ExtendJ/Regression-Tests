import java.util.*;


class Test {
	
	interface X { void execute(Number a); }
	interface Y { void execute(Integer a); }
		
	@FunctionalInterface
	interface Exec extends X, Y { }
}
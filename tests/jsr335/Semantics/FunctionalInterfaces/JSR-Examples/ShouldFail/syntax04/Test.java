import java.util.ArrayList;
import java.util.List;

class Test {
	
	interface X { int m(Iterable<String> arg, Class c); }
	interface Y { int m(Iterable arg, Class<?> c); }
	
	@FunctionalInterface
	interface Z extends X, Y {}
	// Not functional: No method has a subsignature of all abstract methods
}
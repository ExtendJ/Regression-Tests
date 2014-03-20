import java.util.*;
import java.lang.StringBuilder;

class Test {
	interface X<A> { <T extends A> void execute(int a); }
	interface Y<B> { <S extends B> void execute(int a); }
	
	@FunctionalInterface
	interface Exec<A> extends Y<A>, X { }
	
	public static void main(String[] arg) {
		Exec<Integer> e = a -> { };
	}
}
import java.util.*;

class Test {
	interface A { <S>     void execute(ArrayList<S> d, int[] a); }
	interface B { <S,T>   void execute(ArrayList<T> b, int[] a); }
	interface C { <S>     void execute(ArrayList    d, int[] a); }
	interface D { <S,T,A> void execute(ArrayList<Map<S, Map<T,A>>> b, int[] a); }
	interface E {         void execute(ArrayList <Integer> n, int[] a); }
	interface F {         void execute(ArrayList b, int[] a); }
	
	@FunctionalInterface
	interface Exec extends A, B, C, D, E, F { }
}
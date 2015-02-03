// .result=COMPILE_FAIL
import java.util.*;

class Test {
	interface X<I> { <B extends I, A extends B, C extends A, D extends C> D execute(int a); }
	interface Y<I> { <A extends I, B extends A, D extends B, C extends D> C execute(int a); }
	
	/*
	 This test currently fails because there is a bug that substitution is not currently
	 performed in bound lists, which means both I will be unknown type and thus compare equal.
	 */
	@FunctionalInterface
	interface Exec extends Y<Integer>, X<Double> { }
}
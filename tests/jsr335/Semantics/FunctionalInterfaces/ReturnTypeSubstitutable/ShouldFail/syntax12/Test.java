import java.util.*;

class Test {
	interface X<A> { <B> A execute(int a); }
	interface Y<B> { <A> A execute(int a); }
	
	/*
	 This test currently fails because there is a bug for generic interfaces.
	 For Y<B> { <A> A execute(int a); }, since not type arguments are specified
	 for Y in the extends-clause, all the methods that Exec has access to will
	 be erased, which also erased A even though it has nothing to do with B.
	 */
	@FunctionalInterface
	interface Exec extends Y, X<Integer> { }
}
import java.util.*;

class Test {
	interface X<A> { <B> A execute(int a); }
	interface Y<B> { <A> A execute(int a); }
	
	/*
	 This test currently fails because there is a bug for generic interfaces.
	 For Y<B> { <A> A execute(int a); }, when trying to loop up the return type
	 from the execute-method, the type Object is returned, which is wrong (A is correct). 
	 Some type lookup is probably handled different for generic interfaces since the
	 problem disappears if the interface is changed to a normal interface. 
	 */
	@FunctionalInterface
	interface Exec extends Y, X<Integer> { }
}
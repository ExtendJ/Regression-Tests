// calling methods with non-multiplicity return type results in void result
public class Test {
	int m() {
		@option Test test = new Test();
		int result = test.m();// type of RHS is void so this expresson has a static type error
		return result;
	}
}

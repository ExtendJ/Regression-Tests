class A {
	void m() { }
}

class Test extends A {
	/* Test.m() does not override A.m() */
	int m(int x) {
		return -1;
	}
}
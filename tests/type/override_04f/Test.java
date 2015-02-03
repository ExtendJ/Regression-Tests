// .result=COMPILE_FAIL
abstract class A {
	abstract void m();
}

interface I {
	int m();
}

public class Test extends A implements I {
	int m() {
		return 0;
	}
}

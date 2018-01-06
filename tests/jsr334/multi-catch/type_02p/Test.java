// The effective type of a union-type catch parameter is the lub of
// the union types.
// .result: COMPILE_PASS
public class T6 {
	class MyException extends Exception {
		public int a;
	}
	class E1 extends MyException {}
	class E2 extends MyException {}
	public void foo() {
		try {
			if (System.currentTimeMillis() % 2 == 0)
				throw new E1();
			else
				throw new E2();
		} catch (E1 | E2 e) {
			// e has effective type MyException
			e.a = 0xDeadBeef;
		}
	}
}


class T7 {
    
	class E1 extends Exception {
		public int a;
	}
	class E2 extends Exception {
		public int a;
	}
	public void foo() {
		try {
			if (System.currentTimeMillis() % 2 == 0)
				throw new E1();
			else
				throw new E2();
		} catch (E1 | E2 e) {
			// e has effective type Exception
			e.a = 123;
		}
	}

}

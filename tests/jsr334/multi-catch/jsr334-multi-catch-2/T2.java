
class T2 {
    
	class E1 extends Exception {}
	class E2 extends E1 {}
	public void foo() {
		try {
			if (System.currentTimeMillis() % 2 == 0)
				throw new E1();
			else
				throw new E2();
		} catch (E1 | E2 e) {
		}
	}

}

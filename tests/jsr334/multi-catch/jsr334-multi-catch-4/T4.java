
class T4 {
    
	class E1 extends Exception {}
	class E2 extends Exception {}
	public void foo() {
		try {
			throw new E1();
		} catch (E1 | E2 e) {
		}
	}

}

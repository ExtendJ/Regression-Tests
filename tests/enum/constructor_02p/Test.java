// Test calling constructor within constructor
// .result=COMPILE_PASS
enum E {
	;
	int i;

	private E() {
		this(0xFFFF);
	}

	E(int i) {
		this.i = i;
	}
}

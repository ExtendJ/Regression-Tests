// .result=COMPILE_FAIL
class SS {
	public boolean b;
	public SS(Object o, boolean b) {
	}
}
class Test extends SS {
	public Test() {
		// illegal super access -- can not access super of Test
		// in constructor invocation:
		super(new Object(), super.b);
	}
}

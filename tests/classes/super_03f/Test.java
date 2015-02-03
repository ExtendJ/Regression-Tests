// .result=COMPILE_FAIL
class SS {
	public SS(Object o) {
	}
	public void m() {
	}
}
class Test extends SS {
	public Test() {
		super(new Runnable() {
			@Override
			public void run() {
				// illegal super access -- the super reference is
				// not available before the superconstructor has initialized
				// it, so we can not reference super of Test here
				Test.super.m();
			}
		});
	}
}

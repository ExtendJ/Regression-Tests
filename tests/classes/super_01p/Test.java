// .result=COMPILE_PASS
class SS {
	public SS(Object o) {
	}
}
class Test extends SS {
	public Test() {
		super(new Runnable() {
			@Override
			public void run() {
				// legal super access -- not accessing super of Test:
				super.hashCode();
			}
		});
	}
}

// Test overriding final method in enum superclass (java.lang.Enum)
// .result=COMPILE_FAIL
enum Test {
	;
	public Object clone() {
		return null;
	}
}

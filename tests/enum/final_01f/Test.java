// Test that overriding the name method is not allowed in enum declaration
// .result=COMPILE_FAIL
enum Test {
	;
	public String name() {// error - name is final in java.lang.Enum
		return "";
	}
}

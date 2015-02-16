// Test too large integer literal
// .result=COMPILE_PASS
class Test {
	int x = 020000000000;// octal literal within int bounds
}

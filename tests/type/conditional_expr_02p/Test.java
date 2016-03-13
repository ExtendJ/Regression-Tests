// Tests type analysis of a conditional expression
// http://svn.cs.lth.se/trac/jastadd-trac/ticket/56
// .result=COMPILE_PASS
class Test {
	byte test(byte a, Byte b, boolean c) {
		return c ? a : b;
	}
}

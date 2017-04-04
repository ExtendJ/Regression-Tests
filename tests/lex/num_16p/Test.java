// Test legal integer literal lexing.
// .result=COMPILE_PASS
public class Test {
	int    x = 0xCE-256;
	int    y = 0x8F-256;
}

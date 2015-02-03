// .result=COMPILE_FAIL
class Test {
	double _ = 0x.ep-; // missing exponent digits after 'p-'
	// this will not scan correctly because the scanner macro requires digits after the minus
}

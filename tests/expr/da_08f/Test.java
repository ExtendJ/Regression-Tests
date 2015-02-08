// Test definite assignment check for logical AND
// .result=COMPILE_FAIL
class Test {
	{
		boolean v;
		if (false && (v=true)) ;
		boolean b = v;// fail : v not DA after `a` when false in `a && b`
	}
}

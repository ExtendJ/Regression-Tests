// Test definite assignment check for logical AND
// .result=COMPILE_FAIL
class Test {
	{
		boolean v;
		if (true || (v=true)) ;
		boolean b = v;
		// v is not definitely assigned after `a` when true in `a || b`
	}
}

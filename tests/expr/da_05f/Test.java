// Test definite assignment check for logical AND
// .result=COMPILE_FAIL
class Test {
	{
		final int v;
		if ((false && ((v=2) == 3)) && ((v=3) == 2)) ;
		// even though `a` in `a && b` can obviously not be true
		// v is not definitely unassigned before `b` here
	}
}

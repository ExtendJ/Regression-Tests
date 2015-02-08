// Test definite assignment check for logical AND
// https://bitbucket.org/jastadd/jastaddj/issue/88/incorrect-definite-unassignment-check
// .result=COMPILE_FAIL
class Test {
	{
		final int v;
		if (((v=3) == 3) || ((v=2) == 2)) ;
		// v is not definitely unassigned before `b` in `a || b` here
	}
}

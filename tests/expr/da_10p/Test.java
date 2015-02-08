// Test definite assignment check for logical AND
// .result=COMPILE_PASS
class Test {
	{
		int u;
		int v;
		if ((true && ((u=3) == 2)) && ((v=u)==2)) ;
	}
}

// Test definite assignment check for logical OR
// .result=COMPILE_PASS
class Test {
	{
		int u;
		int v;
		if ((true && ((u=4) == 3)) || ((v=u) == 2)) ;
	}
}

// .result=COMPILE_FAIL

@interface annot {
	String[][] names();
}	

public class Test {
	@annot(names = { { "multi-dimensional", "array", "annotation" }, { "component" } })
	public void f() {
	}
}

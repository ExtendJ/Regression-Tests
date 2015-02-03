// .result=COMPILE_PASS

@interface annot {
	String[] words();
}	

public class Test {
	@annot(words = { "array", "annotation", "component" })
	public void f() {
	}
}

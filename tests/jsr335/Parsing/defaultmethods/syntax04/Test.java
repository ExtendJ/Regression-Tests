// .result=COMPILE_OUTPUT
// .options=XstructuredPrint

public interface Test {
	@SuppressWarnings
	public default static strictfp String addExtension(String s) {
		s = s + ".extension";
		return s;
	}
}
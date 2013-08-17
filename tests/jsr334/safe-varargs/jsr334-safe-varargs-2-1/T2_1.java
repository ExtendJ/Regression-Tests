
class T2_1 {
    
	@SafeVarargs
	static <T> void foo(T... a) { }

	@SafeVarargs
	final <T> void bar(T... a) { }

}

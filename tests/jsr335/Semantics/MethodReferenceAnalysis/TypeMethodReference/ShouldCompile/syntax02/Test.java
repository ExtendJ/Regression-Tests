// .result=COMPILE_PASS

public class Test {
	
	class Pair<T, S> {
		T t;
		S s;
		public Pair(T t, S s) {
			this.t = t;
			this.s = s;
		}
		public T first() {
			return t;
		}
	}
	
	interface A {
		<T extends Integer> Integer m(Pair<T, String> p); 
	}
	
	
	A a = Pair::first;
}
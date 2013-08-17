
class T6_1 {
    
		class T6_1_A<T> {
			public T a;
			T6_1_A(T t) {
				a = t;
			}
		}
		{
			new T6_1_A<>("bar").a.getBytes();
		}
	
}

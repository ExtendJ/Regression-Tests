
class T6_2 {
    
		class T6_2_A<T> {
			public T a;
			<U extends Throwable> T6_2_A(T t, U u) throws U {
				a = t;
			}
		}
		{
			new T6_2_A<>("bar", new Error()).a.getBytes();
		}
	
}

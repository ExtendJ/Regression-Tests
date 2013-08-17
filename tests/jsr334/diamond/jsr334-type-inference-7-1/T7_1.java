
class T7_1 {
    
		class T7_1_A<T> {
			public T a;
			T7_1_A() { }
			void set(T t) {
				a = t;
			}
		}
		{
			T7_1_A<Integer> obj = new T7_1_A<>();
			obj.set(4);
			obj.a.compareTo(new Integer(4));
		}
	
}

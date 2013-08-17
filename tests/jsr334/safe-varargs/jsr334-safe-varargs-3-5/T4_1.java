
class T4_1 {
    
	@SuppressWarnings("unchecked")
	static <T> void addToList(java.util.List<T> l, T... a) {
		for (T item : a) l.add(item);
	}

	void foo() {
		java.util.List<Integer> l1 =
			new java.util.LinkedList<Integer>();
		java.util.List<Integer> l2 =
			new java.util.LinkedList<Integer>();
		java.util.List<java.util.List<Integer>> l3 =
			new java.util.LinkedList<java.util.List<Integer>>();
		addToList(l1, new Integer(2), new Integer(3));
		addToList(l2, new Integer(5), new Integer(7));
		addToList(l3, l1, l2);
	}

}

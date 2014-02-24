class X<T> {
}

interface I {
	Float m(X<Integer> i, X<Float> f);
}

interface J {
	Float m(X<Float> i, X<Integer> f);
}

interface Test extends I, J {
}

public class T1 {
    void m() {
        C<F> c = new C<F>();
        c.new D(new F());
    }
}
class C<X> {
    class D {
        D(X x) { }
        D(F f) { }
    }
}

class F { }

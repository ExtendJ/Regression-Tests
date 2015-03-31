// The type Object is not compatible with T extends Object
// .result=COMPILE_FAIL
class Test<T> {
    T _() {
        return new Object();
    }
}


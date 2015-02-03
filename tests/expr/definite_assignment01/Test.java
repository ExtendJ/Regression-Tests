// .result=COMPILE_FAIL
class Test {
    public Object test(Object var) {
        Object o;
        o = test(o);
        return o;
    }
}

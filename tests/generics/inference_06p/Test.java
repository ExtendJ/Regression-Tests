// Test a NullPointerException bug in ExtendJ caused by an error in method type
// inference. See https://bitbucket.org/extendj/extendj/issues/172/nullpointerexception-in-method-type
// .result=COMPILE_PASS
public class Test {
    private static final int i = 0;

    public <T extends Test> T a() {
        return b();
    }

    <T extends Test> T b() {
        return null;
    }
}

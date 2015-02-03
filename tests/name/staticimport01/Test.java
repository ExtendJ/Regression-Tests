// .result=COMPILE_PASS
import static a.A.fail;
import static b.B.notfail;

public class Test {
	void m() {
		fail();
	}
}
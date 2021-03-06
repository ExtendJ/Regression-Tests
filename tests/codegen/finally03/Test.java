// .result=EXEC_PASS
public class Test {
	public static void main(String[] args) throws Throwable {
		new Test().runBare();
	}

    public void runBare() throws Throwable {
        Throwable exception = null;
        setUp();
        try {
            runTest();
        } catch (Throwable running) {
            exception = running;
        } finally {
            try {
                tearDown();
            } catch (Throwable tearingDown) {
                if (exception == null) exception = tearingDown;
            }
        }
        if (exception != null) throw exception;
    }

	public void setUp() {
	}

	public void tearDown() {
	}

	public void runTest() {
	}
}

import java.util.concurrent.Callable;

public class Test {
	public static void main(String[] args) {
        Object x = 0;
		Object f = (int x) -> { return x.toString(); };
    }
}

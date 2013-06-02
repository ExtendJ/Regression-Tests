import java.io.IOException;

public class Test {
	class Foo {
		Foo() throws IOException {
			throw new IOException("oops");
		}
	}
	public void foo() {
		try {
			new Foo() { };
		} catch (IOException e) {
		}
	}
}

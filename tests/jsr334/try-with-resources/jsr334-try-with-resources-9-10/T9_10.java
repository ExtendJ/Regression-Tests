
public class T9_10 {
    T9_10 (){}
    public static void main(String[] args) {
        
	try (java.io.PrintStream r1 = System.out) {
		try (AutoCloseable r2 = new AutoCloseable() {
				public void close() {
					int r1, r2;
				}
			}) {
		} catch (Exception e) {
		}
	}

    }
}

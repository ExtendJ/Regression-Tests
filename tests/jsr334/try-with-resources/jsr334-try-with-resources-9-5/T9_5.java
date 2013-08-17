
public class T9_5 {
    T9_5 (){}
    public static void main(String[] args) {
        
		try (java.io.PrintStream r = System.out) {
			try {
				throw new Exception();
			} catch (Exception r) {
			}
		}
	
    }
}

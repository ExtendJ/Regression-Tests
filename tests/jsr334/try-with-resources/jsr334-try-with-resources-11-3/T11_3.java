
public class T11_3 {
    T11_3 (){}
    public static void main(String[] args) {
        
	try (java.io.PrintStream r = System.out) {
		throw new Exception();
	} catch (Exception e) {
		return;
	}
	new Integer(1).toString();

    }
}

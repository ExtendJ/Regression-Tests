import static runtime.Test.*;

/**
 * @see testLookupSwitchNoDefault
 */
public class Test {
	public static void main(String[] args) {
		String	value = "NAMBLA";
		switch (value) {
			case "a":
				break;
			case "b":
				break;
			case "c":
				break;
			case "d":
				break;
			default:
				return;
		}
		fail("Missed the correct case label!");
	}
}

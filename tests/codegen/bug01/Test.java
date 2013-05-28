public class Test {
	public static void main(String[] args) {
		System.out.println(m());
	}

	public static boolean m() {
		try {
			String x = new String();
			String s = new String();
			try {
				return true;
			} catch (Throwable t) {
				s.toString();
				return false;
			}
		} finally {
		}
	}
}

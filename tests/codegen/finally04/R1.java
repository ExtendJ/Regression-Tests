public class R1 {
	public static void main(String args[]) {
		int a = 0;
		int b = 0;
		int c = 0;
		while (true) {
			try {
				try {
					a++;
				} finally {
					b++;
					break;
				}
			} finally {
				c++;
			}
		}
	}
}

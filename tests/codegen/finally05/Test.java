public class Test {

	public static void main(String args[]) {
		System.out.println(m(1));
	}

	static int m( int i ) {
		try {
			try {
				if (i == 1)
					return i;
			} finally {
				i++;
			}
		} finally {
			return i;
		}
	}
}

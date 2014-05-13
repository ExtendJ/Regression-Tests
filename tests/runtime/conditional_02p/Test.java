public class Test {
	public static void main(String[] args) {
		boolean[] in = { true, false };
		boolean[] out = { true, false };

		for (int i = 0; i < in.length; ++i) {
			boolean result = in[i] ? true : false;
			if (result != out[i]) {
				throw new Error("Incorrect conditional expression result");
			}
		}
	}
}

public class Test {
	static @any String strings() {
		return null;
	}

	public static void main(String[] args) {
		if ([[strings()]].size() != 0) {
			System.err.println("Expected empty relation");
			System.exit(1);
		}
	}
}

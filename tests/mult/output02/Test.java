import java.util.*;

public class Test {
	static class Account {
		String name;
		public Account(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}

	static @any(Set) Account accounts = [[new HashSet<Account>(Arrays.asList(new Account("Steve")))]];

	public static void main(String[] args) {
		int numAccount = [[accounts]].size();
		System.out.println("Num accounts: " + numAccount);
	}
}


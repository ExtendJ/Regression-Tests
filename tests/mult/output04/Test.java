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

	static @any(List) Account accounts = [[new ArrayList<Account>()]];

	public static void main(String[] args) {
		accounts += new Account("Steve");
		accounts += new Account("Sara");
		int numAccount = [[accounts]].size();
		System.out.println("Num accounts: " + numAccount);
		for (Account account: [[accounts]]) {
			System.out.println("" + account);
		}
	}
}


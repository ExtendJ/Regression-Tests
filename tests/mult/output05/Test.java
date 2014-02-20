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

	static @any(Set) Account accounts = [[new HashSet<Account>()]];

	public static void main(String[] args) {
		accounts += new Account("Steve");
		accounts += new Account("Sara");
		System.out.println("Num accounts: " + [[accounts]].size());
		accounts = null;
		System.out.println("Num accounts: " + [[accounts]].size());
	}
}

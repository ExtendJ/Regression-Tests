import java.util.*;

public class Test {
	static class Account {
		String name;
		public Account(String name) {
			this.name = name;
		}
		public void printName() {
			System.out.println(name);
		}
	}
	public static void main(String[] args) {
		@option Account b = new Account("TestAcc");
		b.printName();
	}
}

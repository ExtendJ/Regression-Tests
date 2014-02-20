public class Test {
	String status;
	public Test(String status) {
		this.status = status;
	}
	public static void main(String[] args) {
		@option Test a = null;
		@any(java.util.Collection) Test b = [[new java.util.ArrayList<Test>()]];
		a = new Test("OK");
		b = a;
		b.printStatus();
	}
	public void printStatus() {
		System.out.println("Status is: " + status);
	}
}

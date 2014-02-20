public class Test {
	String status;
	public Test(String status) {
		this.status = status;
	}
	public static void main(String[] args) {
		@any(java.util.Collection) Test a = [[new java.util.ArrayList<Test>()]];
		a += new Test("OK");
		a += new Test("NOT OK");
		a.printStatus();
	}
	public void printStatus() {
		System.out.println("Status is: " + status);
	}
}

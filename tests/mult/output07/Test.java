public class Test {
	String status;
	public Test(String status) {
		this.status = status;
	}
	public static void main(String[] args) {
		@any(java.util.Collection) Test a = [[new java.util.ArrayList<Test>()]];
		a += new Test("OK");
		a += new Test("NOT OK");
		@any(java.util.Collection) Test b = [[new java.util.ArrayList<Test>()]];
		b = a;
		b.printStatus();
	}
	public void printStatus() {
		System.out.println("Status is: " + status);
	}
}

// referencing unknown class "List" in any-modifier (see AA.java)
public class Test {
	public static void main(String[] args) {
		AA a = new AA();
		System.out.println([[a.getLeaves()]].size());
	}
}

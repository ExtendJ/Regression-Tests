public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Component root = new Component();
		Component node = new Component();
		root.children += new Leaf();
		root.children += node;
		node.children += new Leaf();
		node.children += new Leaf();
		System.out.println([[root.getLeaves()]].size());


	}

}

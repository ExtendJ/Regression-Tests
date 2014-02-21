import java.util.LinkedList;

public class Leaf extends Composite {
	@any(LinkedList) Leaf getLeaves() {
		return this;
	}
}

import java.util.LinkedList;

public class Component extends Composite {
	@any(LinkedList) Composite children = [[new LinkedList<Leaf>()]];

	@Override
	@any(LinkedList) Leaf getLeaves() {
		return children.getLeaves();
	}
}

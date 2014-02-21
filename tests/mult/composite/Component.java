import java.util.LinkedList;

public class Component extends Composite {
	@any(LinkedList) Composite children = [[new LinkedList<Composite>()]];

	@Override
	@any(LinkedList) Leaf getLeaves() {
		return children.getLeaves();
	}
}

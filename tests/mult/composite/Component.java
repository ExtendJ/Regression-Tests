import java.util.LinkedList;
import java.util.List;

public class Component extends Composite {
	@any(LinkedList) Composite children = [[new LinkedList<Composite>()]];

	@Override
	@any(List) Leaf getLeaves() {
		return children.getLeaves();
	}
}

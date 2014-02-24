import java.util.LinkedList;

public class AA {
	@any(LinkedList) String children;

	// referencing unknown class "List" in any-modifier
	@any(List) String getLeaves() {
		return children;
	}
}

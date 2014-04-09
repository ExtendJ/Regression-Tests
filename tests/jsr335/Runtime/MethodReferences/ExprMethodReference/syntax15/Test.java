import static runtime.Test.*;

import java.util.*;

public class Test {
	public interface A {
		B m(int i, String s);
	}
	public interface B {
		int m(String s);
	}
	
	public int getVal(int i) {
		return i + 4;
	}
	
	public static void main(String[] arg) {
		int local = 5;
		A a = (i, s1) -> {
			if(local > 4)
				return s2 -> {
					s2.length() + s1.length() + i;
				};
			else
				return s2 -> {
					s1.length() + local;
				};
		};
		testTrue(a.m("yes!") == 4);
	}
}
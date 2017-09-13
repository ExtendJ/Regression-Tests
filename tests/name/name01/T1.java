// Tests accessing a protected member of an enclosing class inherited from
// common superclass declared in another package
// http://svn.cs.lth.se/trac/jastadd-trac/ticket/52
import p1.C;
class T1 extends C {
	class Inner extends C {
		int i = T1.this.m;
	}
}

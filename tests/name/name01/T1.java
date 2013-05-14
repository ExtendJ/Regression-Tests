import p1.C;
class T1 extends C {
	class Inner extends C {
		int i = T1.this.m;
	}
}

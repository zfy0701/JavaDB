
class B {
	int f = 1;
}

class A [int B.fname] {
	//Object t2;
	// A a;
	// A b;

	void test() {
		B b = new B();
		int x = b.fname;
		//this.t2 = this.t1;
		//this.b.b = this.a;
	}
}


//package examples;

public class B {
	public int x = 1;
	public int foo() { return 0; };
	public String foo2() { return "sadf"; };
}

public class C with [int B.f, int B.g()] {
	void test() {
		B b = new B();
		System.out.println("test value = " + b.f);

		System.out.println("test value = " + b.g());
	}
}

public class A with [int B.f, int B.g()] {

	void test() {
		C c = new C with [B.f, B.g] ();
		c.test();
	}

	public static void main(String []args) {
		A a = new A with [B.x, B.foo] ();
	//  A a = new A with [B.x, B.foo2] ();  //error
		a.test();
	}
}

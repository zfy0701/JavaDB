//package examples;

public class B {
	public int f = 1;
	public int foo() { return 0; };
	public int bar(int a, int b) { return a+b; };
}

public class A<T> with [int B.fname, int B.gname()] {
	B b = new B();

	void test() {
		//Object o = 3;
		//int x = (Integer)o;
		
		//Integer i = 3;
		//int x = i;
		System.out.println("test value = " + b.fname);

		System.out.println("test value = " + b.gname());
	}

	public static void main(String []args) {
		A a = new A<B> with [B.f, B.foo] ();
		a.test();
		
		// A a2 = new A<B> with [B.foo] ();
		// a2.test();
	}
}

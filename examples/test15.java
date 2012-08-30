//package examples;

public class B {
	public int f = 1;
	public int foo() { return 0; };
}

public class A<T> with [int B.fname] {
	B b = new B();

	void test() {
		//Object o = 3;
		//int x = (Integer)o;
		
		//Integer i = 3;
		//int x = i;
		System.out.println("test value = " + b.fname);
		b.fname = b.fname * 2;
		System.out.println("test value = " + b.fname);

//		System.out.println("test value = " + b.funname());
	}

	public static void main(String []args) {
		A a = new A<B> with [B.f] ();
		a.test();
		
		// A a2 = new A<B> with [B.foo] ();
		// a2.test();
	}
}

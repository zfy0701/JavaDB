//package examples;

public class B {
	public int f = 1;
	public int foo() { return 0; };
	public int bar(int a) { return a; };
	public int add(int a, Integer b) { return a+b; };
}

public class A<T> with [int B.f, int B.g(), int B.h(int), int B.x(int, Integer)] {
	B b = new B();

	 void test() {
		System.out.println("test value = " + b.f);

		System.out.println("test value = " + b.g());

		System.out.println("test value = " + b.h(7));

		System.out.println("test value = " + b.add(5, 7));

		System.out.println("test value = " + b.x(5, 7));
	}

	public static void main(String []args) {
		A a = new A<B> with [B.f, B.foo, B.bar, B.add] ();
		a.test();
		
		// A a2 = new A<B> with [B.foo] ();
		// a2.test();
	}
}

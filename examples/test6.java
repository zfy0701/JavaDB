//package examples;

public class B {
	public Integer f = 1;
	public Integer g = 10;
}

public class A<T> with [Integer B.fname] {
	B b = new B();

	void test() {
		int x = b.fname.intValue();
		System.out.println("test value = " + x);
	}

	public static void main(String []args) {
		A a = new A<B> with [B.f] ();
		a.test();
		
		A a2 = new A<B> with [B.g] ();
		a2.test();
	}
}

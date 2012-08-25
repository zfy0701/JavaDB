//package examples;

//sub type instantiantion

public class B {
	public Integer f = 1;
	public Integer g = 10;
}

public class C extends B {

}

public class A<T> with [Integer B.fname] {
	B b = new B();

	void test() {
		System.out.println("test value = " + b.fname);
	}

	public static void main(String []args) {
		A a = new A<B> with [B.f] ();
		a.test();
		
		A a2 = new A<B> with [B.g] ();
		a2.test();

		A a3 = new A<B> with [C.f] ();
		a3.test();
	}
}

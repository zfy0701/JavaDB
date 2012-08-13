public class B {
	public Integer f = 1;
	public Integer g = 10;
}

public class C {
	public Integer f = 1;
	public Integer g = 10;
}

public class A<T> with [Integer T.fname] {
	T b = (T)new B();

	void test() {
		System.out.println("test value = " + b.fname);
		b.fname = b.fname * 2;
		System.out.println("test value = " + b.fname);
	}

	public static void main(String []args) {
		//A a = new A<B> with [C.f] ();	//this is wrong [done]
		A a = new A<B> with [B.f] ();
		a.test();
	}
}

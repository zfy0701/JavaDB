//package examples;

class C {
	public int g = 10;
}

class B extends C {
	private int f = 1;
}

class A<T> with [int B.fname] {
	B b = new B();

	void test() {
		//Object o = 3;
		//int x = (Integer)o;
		
		//Integer i = 3;
		//int x = i;
		System.out.println("test value = " + b.fname);
		b.fname = b.fname * 2;
		System.out.println("test value = " + b.fname);
	}

	public static void main(String []args) {
		A a = new A<B> with [B.f] ();
		a.test();
		
		A a2 = new A<B> with [B.g] ();
		a2.test();
	}
}

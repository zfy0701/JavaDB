
import java.util.*;

class B {
  	A a = new A();

  	B () {

  	}

  	B (int a) {
  	}

  	public int myid = 1;

	public static void main(String []args) {
		B b = new B();
		b.myid = 2;
		B bb = b.a.p;

		B bs = ownerof b.a;

		if (bs == null) {
			System.out.println("owner: null");
		} else {
			System.out.println("not null:" + bs.myid);
		}
//		A a = new A(); //TODO this should be forbidden
	//	new A();
	}

	void test() {
		B b1;
		a = new A();
		
		B b3;
		B b4;
		//L1Runtime.getInstance().putParent(a, this);
	}
}

class A ownedby B extends Object {
	//A a; //this is wrong
	//B b1 = new B();
	Object a = this;
	B p = ownerof this;
	//B p = ownerof ((A)a);
}



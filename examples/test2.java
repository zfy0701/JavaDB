
import java.util.*;

class B {
}

class A<T extends B> ownedby T {
	T t2;

	void test() {
		T b = ownerof this;
		B b1 = ownerof this;
	}
}

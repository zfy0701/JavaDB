//package examples;

import java.util.*;

public class VCollection<A, B> with [VCollection A.myfield] ownedby B {
	public Collection<A> col;
	VCollection(Collection<A> col) {
		this.col = col;
	}

	public void add(A a) {
		this.col.add(a);

		B b = ownerof this;
		a.myfield.col.add(b);  //this is not infinite loop
		//VCollection c = a.myfield;
	}
}

public class People {
	public String name;
	public final VCollection<Class, People> bc = new VCollection<Class, People> with [Class.ac] (new ArrayList<Class>());  //~ inverse ac;
}

public class Class {
	public String classname;
	public final VCollection<People, Class> ac = new VCollection<People, Class> with [People.bc] (new ArrayList<People>());	//~ inverse bc
}

public class Test {
	//public static 
}
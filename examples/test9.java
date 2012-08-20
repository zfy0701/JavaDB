//package examples;

import java.util.*;

public class VCollection<A, B, C> with [VCollection A.myfield] ownedby B {
	public Map<A, C> col;
	VCollection() {
		this.col = new HashMap<A, C>();
	}

	public void add(A a, C c) {
		this.col.put(a, c);
		B b = ownerof this;
		a.myfield.col.put(b, c);  //this is not infinite loop
	}
}

public class People {
	public String name;
	public People(String name) {
		this.name = name;
	}
	public String toString() {return name;}
	public final VCollection<Class, People, Integer> classes = new VCollection<Class, People, Integer> with [Class.students] ();  //~ inverse ac;
}

public class Class {
	public String classname;
	public Class(String classname) {
		this.classname = classname;
	}
	public String toString() {return classname;}

	public final VCollection<People, Class, Integer> students = new VCollection<People, Class, Integer> with [People.classes] ();	//~ inverse bc
}

public class Test {
	public static void main(String args[]) {
		People jim = new People("jim");
		People tom = new People("tom");
		People sam = new People("sam");
		List<People> allstudents = new ArrayList<People>();
		allstudents.add(jim);
		allstudents.add(tom);
		allstudents.add(sam);

		Class english = new Class("ENG");
		Class chinese = new Class("CHN");
		Class japanese = new Class("JPN");
		List<Class> allclasses = new ArrayList<Class>();
		allclasses.add(english);
		allclasses.add(chinese);
		allclasses.add(japanese);

		//adding relations
		jim.classes.add(english, 87);
		jim.classes.add(japanese, 92);

		tom.classes.add(japanese, 86);

		sam.classes.add(japanese, 79);

		chinese.students.add(jim, 83);
		chinese.students.add(tom, 88);

		System.out.println("test 1: traversal through students, then classes");
		for (People s : allstudents) {
			System.out.println(s + ":");
			for (Map.Entry<Class, Integer> c: s.classes.col.entrySet()) {
				System.out.println("\t" + c.getKey() + " " + c.getValue());
			}
		}

		System.out.println("\ntest 2: traversal through classes, then students");
		for (Class c : allclasses) {
			System.out.println(c + ":");
			for (Map.Entry<People, Integer> s: c.students.col.entrySet()) {
				System.out.println("\t" + s.getKey() + " " + s.getValue());
			}
		}
	}
}

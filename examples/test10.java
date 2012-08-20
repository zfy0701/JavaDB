//package examples;

import java.util.*;

public class VCollection<A, B> with [B A.myfield] ownedby B {
	public List<A> col;
	VCollection(List<A> col) {
		this.col = col;
	}

	public void add(A a) {
		this.col.add(a);

		B b = ownerof this;
		a.myfield = b;  //this is not infinite loop
	}
}

public class Inverse<A, B> with [VCollection A.myfield] ownedby B {
	public A value;

	public Inverse(A a) {
		this.value = a;
		B b = ownerof this;
		value.myfield.col.add(b);
	}
}

public class People {
	public String name;
	public People(String name) {
		this.name = name;
	}
	public String toString() {return name;}
	public final VCollection<Book, People> classes = new VCollection<Book, People> with [Book.student2] (new ArrayList<Book>());  //~ inverse ac;
}

public class Book {
	public String bookname;

	public Book(String bookname, People student) {
		this.bookname = bookname;
		this.student = student;
		student.col.add(this);
	}

	public String toString() {return bookname;}

	public People student;

	public final Inverse<People, Book> student2;
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
		jim.classes.add(english);
		jim.classes.add(japanese);

		tom.classes.add(japanese);

		sam.classes.add(japanese);

//		chinese.students.add(jim);
//		chinese.students.add(tom);

		// System.out.println("test 1: traversal through students, then classes");
		// for (People s : allstudents) {
		// 	System.out.println(s + ":");
		// 	for (Class c : s.classes.col) {
		// 		System.out.println("\t" + c);
		// 	}
		// }
	}
}
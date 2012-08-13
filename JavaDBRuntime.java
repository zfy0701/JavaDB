package examples;

import java.lang.reflect.Field;
import java.util.*;
class Pair<A, B> {
	private A first;
	private B second;

	public Pair(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}

	public int hashCode() {
		int hashFirst = first != null ? first.hashCode() : 0;
		int hashSecond = second != null ? second.hashCode() : 0;

		return (hashFirst + hashSecond) * hashSecond + hashFirst;
	}

	public boolean equals(Object other) {
		if (other instanceof Pair) {
			@SuppressWarnings("unchecked")
			Pair<A, B> otherPair = (Pair<A, B>) other;
			return ((this.first == otherPair.first || (this.first != null
					&& otherPair.first != null && this.first
						.equals(otherPair.first))) && (this.second == otherPair.second || (this.second != null
					&& otherPair.second != null && this.second
						.equals(otherPair.second))));
		}

		return false;
	}

	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	public A getFirst() {
		return first;
	}

	public void setFirst(A first) {
		this.first = first;
	}

	public B getSecond() {
		return second;
	}

	public void setSecond(B second) {
		this.second = second;
	}
}


public class JavaDBRuntime {
	private static JavaDBRuntime l1Runtime = new JavaDBRuntime();

	private final HashMap<Object, Object> table = new HashMap<Object, Object>();
	private final static HashMap<Pair<Object, String>, Field> fieldTable = new HashMap<Pair<Object, String>, Field>();

	private JavaDBRuntime() {
	}

	public static JavaDBRuntime getInstance() {
		return l1Runtime;
	}

	public void putParent(Object k, Object p) {
		table.put(k, p);
	}

	public Object getParent(Object k) {
		return table.get(k);
	}

	public static void registerField(Object o, String classname, String parName,
			String fieldName) {
		Field f;
		try {
			f = Class.forName(classname).getField(fieldName);
			JavaDBRuntime.fieldTable.put(new Pair<Object, String>(o, parName), f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getFieldValue(Object o, String parName) {
		Field f = JavaDBRuntime.fieldTable.get(new Pair<Object, String>(o, parName));
		try {
			return f.get(o);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void Main(String args[]) {
		JavaDBRuntime.getFieldValue(null, "asdf");
		JavaDBRuntime.registerField(null, null, null, null);
	}
}

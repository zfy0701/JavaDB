package examples;

import java.lang.reflect.Field;
import java.util.*;

public class JavaDBRuntime {
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

	private static JavaDBRuntime l1Runtime = new JavaDBRuntime();

	private HashMap<Object, Object> table = new HashMap<Object, Object>();
	private HashMap<Pair<Object, String>, Field> fieldTable = new HashMap<Pair<Object, String>, Field>();

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

	public void registerField(Object o, String classname, String parName,
			String fieldName) throws Exception {
		Field f = Class.forName(classname).getField(fieldName);
		fieldTable.put(new Pair<Object, String>(o, parName), f);
	}

	public Object getFieldValue(Object o, String parName) throws Exception {
		Field f = fieldTable.get(new Pair<Object, String>(o, parName));
		return f.get(o);
	}
}

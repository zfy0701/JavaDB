
package javadb;

import java.lang.reflect.*;
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
	//private static JavaDBRuntime l1Runtime = new JavaDBRuntime();

	private final static HashMap<Object, Object> table = new HashMap<Object, Object>();
	private final static HashMap<Pair<Object, Integer>, Field> fieldTable = new HashMap<Pair<Object, Integer>, Field>();
	private final static HashMap<Pair<Object, Integer>, Method> methodTable = new HashMap<Pair<Object, Integer>, Method>();

	private JavaDBRuntime() {
	}

	public static void putParent(Object k, Object p) {
		table.put(k, p);
	}

	public static Object getParent(Object k) {
		return table.get(k);
	}

	public static void registerField(Object o, String classname, int parName,
			String fieldName) {
		Field f = null;
		try {
			for (Class c = Class.forName(classname); f == null && c != null; c = c.getSuperclass()) {
				try {
					f = c.getDeclaredField(fieldName);
				} catch (Exception e) {
				}
			}
			f.setAccessible(true);

			JavaDBRuntime.fieldTable.put(new Pair<Object, Integer>(o, parName), f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getFieldValue(Object o, Object reciver, int parName) {
		Field f = JavaDBRuntime.fieldTable.get(new Pair<Object, Integer>(o, parName));
		try {
			return f.get(reciver);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void setFieldValue(Object reciver, Object value, Object o, int parName) {
		Field f = JavaDBRuntime.fieldTable.get(new Pair<Object, Integer>(o, parName));
		try {
			f.set(reciver, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void registerMethod(Object o, String classname, int parName,
			String methodName, Class[] paramTypes) {
		Method m = null;
		try {
//			System.out.println("test");

			for (Class c = Class.forName(classname); m == null && c != null; c = c.getSuperclass()) {
				try {
					m = c.getDeclaredMethod(methodName, paramTypes);
				} catch (Exception e) {
				}
			}
			m.setAccessible(true);

			JavaDBRuntime.methodTable.put(new Pair<Object, Integer>(o, parName), m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object callMethod(Object o, Object reciver, int parName, Object[] args) {
		
		Method m = JavaDBRuntime.methodTable.get(new Pair<Object, Integer>(o, parName));
		try {
			return m.invoke(reciver, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Class forNameNoException(String name) {
		try {
//			System.out.println("hello" + name);
			
			if (name.equals("@primitive.byte")) return byte.class;
			if (name.equals("@primitive.short")) return short.class;
			if (name.equals("@primitive.int")) return int.class;
			if (name.equals("@primitive.long")) return long.class;
			if (name.equals("@primitive.char")) return char.class;
			if (name.equals("@primitive.float")) return float.class;
			if (name.equals("@primitive.double")) return double.class;
			if (name.equals("@primitive.boolean")) return boolean.class;
			if (name.equals("@primitive.void")) return void.class;

			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	 public static void main(String args[]) throws Exception {

		 Test t = new Test();
		 
//		 int.class
		 Method m = Test.class.getDeclaredMethod("foo", Class.forName("java/lang/int"));
		 
		 m.invoke(t, 1);
		 
//		JavaDBRuntime.getFieldValue(null, null, 0);
//	 	JavaDBRuntime.setFieldValue(null, null, null, 0);
//	 	
//	 	JavaDBRuntime.registerField(null, null, 0, null);
//
//	 	JavaDBRuntime.registerMethod(null, null, 0, null, null);
//	 	JavaDBRuntime.callMethod(null, null, 0, null);
//	 	
//	 	Class [] cls = new Class[2]; 
//	 	
//	 	//for (int i = 0; i < 2; i++) {
//		cls[0] = JavaDBRuntime.forNameNoException("test");
//		
//		cls[1] = JavaDBRuntime.forNameNoException("test");

	}
}

class Test {
	int foo(int a) {
		return a;
	}
}


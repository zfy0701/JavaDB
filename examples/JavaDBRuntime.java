package examples;

import java.lang.reflect.Field;
import java.util.*;

public class JavaDBRuntime {
	class ParField {
		Object o;
		String parName;
		ParField(Object o, String parName) {
			this.o = o;
			this.parName = parName;
		}
	}
	
	private static JavaDBRuntime l1Runtime = new JavaDBRuntime();

	private HashMap<Object, Object> table = new HashMap<Object, Object>();
	private HashMap< ParField, Field> fieldTable = new HashMap<ParField, Field>();

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
	
	public void registerField(Object o, String classname, String parName, String fieldName) throws Exception {
		Field f  = Class.forName(classname).getField(fieldName);
		fieldTable.put(new ParField(o, parName), f);
	}
	
	public Object getFieldValue(Object o, String parName) throws Exception {
		Field f = fieldTable.get(new ParField(o, parName));
		return f.get(o);
	}
}

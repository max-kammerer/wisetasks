package ru.spb.ipo.generator.base;

public class FuncUtil {

	public static String projection(String axis) {
		return "<function type=\"Projection\" axis=\"" + axis + "\">\n" +
				"		<current-set-element/>\n" +
				"</function>\n";
	}
	
	public static String projection(String axis, String body) {
		return "<function type=\"Projection\" axis=\"" + axis + "\">\n" +
				"		" + body + "\n" +
				"</function>\n";
	}	
	
	public static String equals(String first, String second) {
		return "<function type=\"Equals\">\n" +
				"		" + first + "\n" +
				"		" + second + "\n" +
				"</function>\n";
	}

	public static String func(String name, String first) {
		return "<function type=\"" + name + "\">\n" +
				"		" + first + "\n" +
				"</function>\n";
	}
	
	public static String func(String name, String first, String second) {
		return "<function type=\"" + name + "\">\n" +
				"		" + first + "\n" +
				"		" + second + "\n" +
				"</function>\n";
	}
	
	public static String projection(int axis) {		
		return projection("" + axis);
	}
	
	public static String constElement(String value) {
		return "<constElement>" + value + "</constElement>\n";
	}
	
	public static String constElement(int value) {
		return constElement("" + value);
	}
	
	public static String for1(String name, String first, String last, String inc, String body) {
		return "<for name=\"" + name + "\" first=\"" + first + "\" last=\"" + last + "\" inc=\"" + inc + "\">\n" +
				"		" + body + "\n" +
				"</for>";
	}
	
	public static String cse() {
		return "<current-set-element/>";
	}
}

package ru.spb.ipo.generator.base;

public class FuncUtil {

	public static String projection(String axis) {
		StringBuffer sb = new StringBuffer();
		sb.append("<function type=\"Projection\" axis=\"" + axis + "\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		return sb.toString();
	}
	
	public static String projection(String axis, String body) {
		StringBuffer sb = new StringBuffer();
		sb.append("<function type=\"Projection\" axis=\"" + axis + "\">\n");			
		sb.append("		" + body + "\n");
		sb.append("</function>\n");
		return sb.toString();
	}	
	
	public static String equals(String first, String second) {
		StringBuffer sb = new StringBuffer();
		sb.append("<function type=\"Equals\">\n");			
		sb.append("		" + first + "\n");
		sb.append("		" + second + "\n");
		sb.append("</function>\n");
		return sb.toString();
	}

	public static String func(String name, String first) {
		StringBuffer sb = new StringBuffer();
		sb.append("<function type=\"" + name + "\">\n");			
		sb.append("		" + first + "\n");		
		sb.append("</function>\n");
		return sb.toString();
	}
	
	public static String func(String name, String first, String second) {
		StringBuffer sb = new StringBuffer();
		sb.append("<function type=\"" + name + "\">\n");			
		sb.append("		" + first + "\n");
		sb.append("		" + second + "\n");
		sb.append("</function>\n");
		return sb.toString();
	}
	
	public static String projection(int axis) {		
		return projection("" + axis);
	}
	
	public static String constElement(String value) {
		StringBuffer sb = new StringBuffer();
		sb.append("<constElement>" + value + "</constElement>\n");
		return sb.toString();
	}
	
	public static String constElement(int value) {
		return constElement("" + value);
	}
	
	public static String for1(String name, String first, String last, String inc, String body) {
		StringBuffer sb = new StringBuffer();		
		sb.append("<for name=\"" + name + "\" first=\"" + first + "\" last=\"" + last + "\" inc=\"" + inc + "\">\n");				
		sb.append("		" + body + "\n");		                	
		sb.append("</for>");
		
		return sb.toString();
	}
	
	public static String cse() {
		return "<current-set-element/>";
	}
}

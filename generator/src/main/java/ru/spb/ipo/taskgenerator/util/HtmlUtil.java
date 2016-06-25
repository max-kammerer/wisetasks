package ru.spb.ipo.taskgenerator.util;

import ru.spb.ipo.taskgenerator.model.Attribute;

public class HtmlUtil {

	public static String getHtmlString(String source) {
		return "<html><body>"+ ElementUtil.getSafe(source) +"</body></html>"; 
	}
	
	public static String getHtmlString(String source, String color) {
		return getHtmlString("<font color='" + color + "'>" + ElementUtil.getSafe(source) +"</font>"); 
	}
	
	public static String colorize(String code, String color) {
		return "<font color='" + color + "'>" + ElementUtil.getSafe(code) +"</font>";
	}
	
	public static String colorize(Attribute attribute) {
		String name = attribute.getName();
		String value = attribute.getValue();
		name = colorize(name, "blue");
		value = colorize(value, "green");
		return name + " = " + value;
	}
}

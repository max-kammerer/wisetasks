package ru.spb.ipo.taskgenerator.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.spb.ipo.taskgenerator.config.Config;
import ru.spb.ipo.taskgenerator.util.ElementUtil;

public class Parameter extends Element{

	private List parameters;
	
	public Parameter() {
		super(Config.TYPE_PARAM);
		parameters = new ArrayList();
	}
	
	public String getText() {
		return super.getText();
//		StringBuffer text = new StringBuffer();
//		HashMap map = getValues();
//		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
//			String value = (String) iter.next();
//			String description = (String) map.get(value);
//			text.append(value);
//			if (!"".equals(ElementUtil.getSafe(description))) {
//				text.append("  - [" + description + "]");
//			}
//			text.append("\n");
//		}
//		String result = "";
//		if (text.length() > 0) {
//			result = text.substring(0, text.length() - "\n".length());
//		} else {
//			result = text.toString();
//		}
//		return result;
	}

	public void setText(String text) {
		if ("".equals(ElementUtil.getSafe(text))){
			return;
		} 
 		throw new UnsupportedOperationException();
	}
	
	public List getValues() {
		return parameters;
	}
	
	public void addValue(String value, String description) {
		parameters.add(new KeyValue(value, description));
	}
	
	public void clear() {
		parameters.clear();
	}

	public void addChild(KernelElement child) {
		if (child == null || child instanceof Element == false) return;			
			
		Element el = (Element)child;
		Attribute attribute = el.getAttribute("text");
		if (attribute != null) {
			addValue(el.getText(), attribute.getValue());
		} else {
			addValue(el.getText(), null);
		}
	}

}

package ru.spb.ipo.taskgenerator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ru.spb.ipo.taskgenerator.util.ElementUtil;
import ru.spb.ipo.taskgenerator.util.HtmlUtil;

public class Element extends KernelElementImpl {

	private String myName;
	private String myText;
	//remove
	private HashMap<String, Attribute> attributes;
	private List<Attribute> attributesList;
	
	public Element(String name) {
		myName = name;
		attributes = new HashMap<String, Attribute>();
		attributesList = new ArrayList<Attribute>();
	}
	
	private void addAttribute(Attribute attr) {
		Object v = attributes.put(attr.getName(), attr);
		if (v != null) {
			int i = attributesList.indexOf(v);
			attributesList.remove(i);
			attributesList.add(i, attr);
		} else {
			attributesList.add(attr);
		}
	}
	
	public void addAttribute(String name, String value) {
		addAttribute(new Attribute(name, value));
	}
	
	public Attribute getAttribute(String name) {
		return attributes.get(name);
	}
	
	public void removeAttributes(){
		attributesList.clear();
		attributes.clear();
	}
	
	public String getPresentableString() {
//		String text = ElementUtil.getSafe(myText);
//		if (!"".equals(text)) {
//			return myName + " = " + text;
//		}
		boolean isType = false;
		if (ElementUtil.getElementType(this) == ElementUtil.E_FUNCTION || 
				ElementUtil.getElementType(this) == ElementUtil.E_SET) {
			isType = !ElementUtil.isConst(this);
		}
		
		Iterator<Attribute> it = attributesList.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {		
			Attribute attr = it.next();
			if (isType && isType(attr)) {
				sb.insert(0, attr.getValue() + " ");			
			} else {
				sb.append("&nbsp;&nbsp;&nbsp;").append(attr.getPresentableString());
			}
		}
		if (!isType) {		
			return HtmlUtil.getHtmlString(myName + sb.toString(), "black");
		} else {
			return HtmlUtil.getHtmlString(sb.toString(), "blue");
		}
	}

	public String getText() {
		return myText;
	}

	public void setText(String text) {
		myText = text;
	}

	public Collection<Attribute> getAttributes() {
		return attributesList;
	}

	public String getName() {
		return myName;
	}
	
	private boolean isType(Attribute attribute) {
		return "type".equals(attribute.getName());
	}

}


package ru.spb.ipo.generator.cards;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.ListElement;

public class OneCard implements ComplexElement {

	private ListElement value, type;
	
	public OneCard(ListElement value, ListElement type) {
		this.value = value;
		this.type = type;
	}
	
	public String generateXml() {
		String str =  "<function type=\"Or\">\n";
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		sb.append("<for name=\"i\" first=\"1\" last=\"${length}\" inc=\"1\">\n");
		sb.append("<function type=\"And\">\n");
		
		sb.append(value.generateXml() + "\n");
		sb.append(type.generateXml() + "\n");
        		
		sb.append("</function>\n");
		sb.append("</for>");			
		sb.append("</function>\n");			
		
		return sb.toString();
	}

	
	public String toDescription() {		
		return toString();
	}
	
	public String toString() {		
		return "имеется " + value + " " + type;
	}
}


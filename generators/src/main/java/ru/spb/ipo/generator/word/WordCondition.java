package ru.spb.ipo.generator.word;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.cards.TypeModell;

public class WordCondition extends TypeModell.Generator implements ComplexElement {

	private String desc;
	
	public WordCondition(String desc) {
		super(null);
		this.desc = desc;
	}

	public String toDescription() {		
		return desc;
	}
	
	public String toString() {
		return toDescription();
	}

	public String generateXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<function type=\"And\">\n");
		sb.append("<constElement>1</constElement>\n");
		sb.append("<constElement>1</constElement>\n");
		
		sb.append("<for name=\"i\" first=\"1\" last=\"${lengthMod2}\" inc=\"1\">\n");
		sb.append("<function type=\"Equals\">\n");
		
		sb.append("<function type=\"Projection\" axis=\"${i}\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		
		sb.append("<function type=\"Projection\" axis=\"${length} + 1 -${i}\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		
		sb.append("</function>\n");
                	
		sb.append("</for>");
		sb.append("</function>\n");
		
		return sb.toString();
		
	}	
}
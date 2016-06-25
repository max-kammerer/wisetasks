package ru.spb.ipo.generator.numbers;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.cards.TypeModell.Generator;

public class DiifNumbersGenerator extends Generator implements ComplexElement {
	private String desc;
	private int type;
	
	public DiifNumbersGenerator(String desc) {
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
		sb.append("<function type=\"Equals\">\n");
		sb.append("<function type=\"Projection\" axis=\"1\">\n");
		sb.append("<function type=\"Count\">\n");
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		sb.append("</function>\n");
		sb.append("<constElement>" + "1" + "</constElement>");
		sb.append("</function>\n");
				return sb.toString();		
	}	
}

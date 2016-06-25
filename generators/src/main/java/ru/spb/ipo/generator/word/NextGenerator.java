package ru.spb.ipo.generator.word;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.cards.TypeModell;

public class NextGenerator extends TypeModell.Generator implements ComplexElement {

	private String desc;
	private int type;
	
	public NextGenerator(String desc, int type) {
		super(null);
		this.desc = desc;
		this.type = type;
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
		
		sb.append("<for name=\"i\" first=\"1\" last=\"${length}-1\" inc=\"1\">\n");
		sb.append("<function type=\"Or\">\n");
		sb.append("<function type=\"And\">\n");
		
		sb.append("<function type=\"Equals\">\n");
		sb.append("<function type=\"Projection\" axis=\"2\">\n");
		sb.append("<function type=\"Projection\" axis=\"${i}\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		sb.append("</function>\n");
		sb.append("<constElement>" + getType() + "</constElement>");
		sb.append("</function>\n");

		sb.append("<function type=\"Equals\">\n");
		sb.append("<function type=\"Projection\" axis=\"2\">\n");
		sb.append("<function type=\"Projection\" axis=\"${i}+1\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		sb.append("</function>\n");
		sb.append("<constElement>" + getUnType() + "</constElement>");
		sb.append("</function>\n");
		
		sb.append("</function>\n");
		
		sb.append("<function type=\"Equals\">\n");
		sb.append("<function type=\"Projection\" axis=\"2\">\n");
		sb.append("<function type=\"Projection\" axis=\"${i}\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		sb.append("</function>\n");
		sb.append("<constElement>" + getUnType() + "</constElement>");
		sb.append("</function>\n");
		
		sb.append("</function>\n");		                	
		sb.append("</for>");
		
		sb.append("<function type=\"Equals\">\n");
		sb.append("<function type=\"Projection\" axis=\"2\">\n");
		sb.append("<function type=\"Projection\" axis=\"${length}\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		sb.append("</function>\n");
		sb.append("<constElement>" + getUnType() + "</constElement>");
		sb.append("</function>\n");
		
		sb.append("</function>\n");
		return sb.toString();		
	}
	
	private String getType() {
		return "" + type;
	}
	
	private String getUnType() {
		return "" + (type == 0 ? 1 : 0);
	}

}

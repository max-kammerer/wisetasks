package ru.spb.ipo.generator.numbers;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.cards.TypeModell.Generator;

public class OrderNumberGenerator extends Generator implements ComplexElement {

	private int order;
	
	public OrderNumberGenerator(int order) {
		super(null);
		this.order = order;
	}

	public String generateXml() {
		StringBuffer sb = new StringBuffer();
		String type = "";
		switch (order) {
		case -1: case -2:
			type = "Smaller";			
			break;

		case 0:
			type = "Equal";
			break;
			
		case 1: case 2:
			type = "Greater";
			break;
		}		
		
		sb.append("<function type=\"And\">\n");
		sb.append("<constElement>1</constElement>\n");
		
		sb.append("<for name=\"i\" first=\"1\" last=\"${length}-1\" inc=\"1\">\n");		

		if (order == 2 || order == -2) {
			sb.append("<function type=\"Not\">\n");
		}
		
		sb.append("<function type=\"" + type + "\">\n");		
		sb.append("<function type=\"Projection\" axis=\"${i}\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");		
		sb.append("<function type=\"Projection\" axis=\"${i}+1\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");				
		sb.append("</function>\n");		
		
		if (order == 2 || order == -2) {
			sb.append("</function>\n");
		}
		
		sb.append("</for>");		
		sb.append("</function>\n");
		
		return sb.toString();
	}

	public String toDescription() {
		String s = "";
		switch (order) {
		case -2:
			s = "в невозрастающем";
			break;
			
		case -1:
			s = "в убывающем";			
			break;
			
		case 1:
			s = "в возрастающем";
			break;
		case 2:
			s = "в неубывающем";
			break;
		}
		return "Цифры набора идут " + s + " порядке";		
	}
	
	public String toString() {		
		return toDescription();
	}	
}

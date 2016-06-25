package ru.spb.ipo.generator.cards;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.ListElement;

public class ParseElement implements ComplexElement {

	private ListElement e;
	private String cond;
	private String value;
	
	public ParseElement(ListElement e, String cond, String value) {
		this.cond = cond;
		this.e = e;
		this.value = value;
	}

	
	public String toString() {
		String s = "";
		switch (cond.charAt(0)) {
			case '<': s = "������"; break;
			case '=': s = "�����"; break;
			case '>': s = "������"; break;		
		}
		return "���������� ���� " + e + " " + cond + " " + value;		
	}
	
	public String toDescription() {
		String s = "";
		switch (cond.charAt(0)) {
			case '<': s = "������"; break;
			case '=': s = "�����"; break;
			case '>': s = "������"; break;		
		}
		return "���������� ���� " + e + " " + s + " " + value;		
	}
	
	
	
	public String generateXml() {
		StringBuffer sb = new StringBuffer("<function type=");
		String func = "";
		switch (cond.charAt(0)) {
			case '<': func = "Smaller"; break;
			case '=': func = "Equals"; break;
			case  '>': func = "Greater"; break;
		}
		sb.append("\"" + func + "\">\n");
		
		sb.append(e.generateXml()+"\n");
		
		sb.append("<constElement>" + value + "</constElement>\n");
		sb.append("</function>");
		return sb.toString();		
	}
}

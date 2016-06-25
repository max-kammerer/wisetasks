package ru.spb.ipo.generator.word;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.cards.TypeModel;

public class NextGenerator extends TypeModel.Generator implements ComplexElement {

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
		return "<function type=\"And\">\n" +
				"<constElement>1</constElement>\n" +
				"<for name=\"i\" first=\"1\" last=\"${length}-1\" inc=\"1\">\n" +
				"<function type=\"Or\">\n" +
				"<function type=\"And\">\n" +
				"<function type=\"Equals\">\n" +
				"<function type=\"Projection\" axis=\"2\">\n" +
				"<function type=\"Projection\" axis=\"${i}\">\n" +
				"		<current-set-element/>\n" +
				"</function>\n" +
				"</function>\n" +
				"<constElement>" + getType() + "</constElement>" +
				"</function>\n" +
				"<function type=\"Equals\">\n" +
				"<function type=\"Projection\" axis=\"2\">\n" +
				"<function type=\"Projection\" axis=\"${i}+1\">\n" +
				"		<current-set-element/>\n" +
				"</function>\n" +
				"</function>\n" +
				"<constElement>" + getUnType() + "</constElement>" +
				"</function>\n" +
				"</function>\n" +
				"<function type=\"Equals\">\n" +
				"<function type=\"Projection\" axis=\"2\">\n" +
				"<function type=\"Projection\" axis=\"${i}\">\n" +
				"		<current-set-element/>\n" +
				"</function>\n" +
				"</function>\n" +
				"<constElement>" + getUnType() + "</constElement>" +
				"</function>\n" +
				"</function>\n" +
				"</for>" +
				"<function type=\"Equals\">\n" +
				"<function type=\"Projection\" axis=\"2\">\n" +
				"<function type=\"Projection\" axis=\"${length}\">\n" +
				"		<current-set-element/>\n" +
				"</function>\n" +
				"</function>\n" +
				"<constElement>" + getUnType() + "</constElement>" +
				"</function>\n" +
				"</function>\n";
	}
	
	private String getType() {
		return "" + type;
	}
	
	private String getUnType() {
		return "" + (type == 0 ? 1 : 0);
	}

}

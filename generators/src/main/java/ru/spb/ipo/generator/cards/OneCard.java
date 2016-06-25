package ru.spb.ipo.generator.cards;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.ListElement;

class OneCard implements ComplexElement {

	private ListElement value, type;
	
	OneCard(ListElement value, ListElement type) {
		this.value = value;
		this.type = type;
	}
	
	public String generateXml() {
		return "<function type=\"Or\">\n" +
				"	<for name=\"i\" first=\"1\" last=\"${length}\" inc=\"1\">\n" +
				"		<function type=\"And\">\n" +
						value.generateXml() + "\n" +
						type.generateXml() + "\n" +
				"		</function>\n" +
				"	</for>" +
				"</function>\n";
	}

	public String toDescription() {		
		return toString();
	}
	
	public String toString() {		
		return "имеется " + value + " " + type;
	}
}


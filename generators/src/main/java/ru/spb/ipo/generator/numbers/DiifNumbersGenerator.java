package ru.spb.ipo.generator.numbers;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.cards.TypeModel.Generator;

class DiifNumbersGenerator extends Generator implements ComplexElement {
	private String desc;

	DiifNumbersGenerator(String desc) {
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
		return "<function type=\"Equals\">\n" +
				"	<function type=\"Projection\" axis=\"1\">\n" +
				"		<function type=\"Count\">\n" +
				"			<current-set-element/>\n" +
				"		</function>\n" +
				"	</function>\n" +
				"	<constElement>" + "1" + "</constElement>" +
				"</function>\n";
	}	
}

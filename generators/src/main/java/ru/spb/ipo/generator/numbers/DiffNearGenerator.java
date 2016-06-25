package ru.spb.ipo.generator.numbers;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.cards.TypeModel.Generator;

class DiffNearGenerator extends Generator implements ComplexElement  {
	private String desc;

	DiffNearGenerator(String desc) {
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
		return "<function type=\"And\">\n" +
				"	<constElement>1</constElement>\n" +
				"	<for name=\"i\" first=\"1\" last=\"${length}-1\" inc=\"1\">\n" +
				"		<function type=\"Not\">\n" +
				"			<function type=\"Equals\">\n" +
				"				<function type=\"Projection\" axis=\"${i}\">\n" +
				"				<current-set-element/>\n" +
				"			</function>\n" +
				"			<function type=\"Projection\" axis=\"${i}+1\">\n" +
				"				<current-set-element/>\n" +
				"			</function>\n" +
				"			</function>\n" +
				"		</function>\n" +
				"	</for>" +
				"</function>\n";
	}
}

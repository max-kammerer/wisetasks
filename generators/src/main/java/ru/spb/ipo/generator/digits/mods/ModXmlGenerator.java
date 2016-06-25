package ru.spb.ipo.generator.digits.mods;

import java.util.Map;

import ru.spb.ipo.generator.base.BaseGenerator;

class ModXmlGenerator extends BaseGenerator {


	ModXmlGenerator(Map<String, Object> sourceParams, Map<String, Object> funcParams, Map<String, Object> taskParams) {
		super(sourceParams, funcParams, taskParams);
	}

	public String getVerifier(Map funcParams) {
		return "<verifier type=\"AnswerVerifier\">\n" +
				"	<function type=\"Parser\" exp=\"" + funcParams.get("expression") + "\" mod=\"" + funcParams.get("mod") + "\">\n" +
				"	</function>\n" +
				"</verifier>";
	}

	public String generateDescription() {
		return "Какой остаток дает число " + funcParams.get("expression") + " при делении на " + funcParams.get("mod") + "?";
	}

	
	public String getParams() {		
		return "";
	}

	public String getSource(Map sourceParams) {		
		return "";
	}
}

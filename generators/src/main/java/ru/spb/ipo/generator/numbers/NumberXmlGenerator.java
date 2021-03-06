package ru.spb.ipo.generator.numbers;

import java.util.Collections;
import java.util.Map;

import ru.spb.ipo.generator.base.BaseGenerator;

public class NumberXmlGenerator extends BaseGenerator {

	public NumberXmlGenerator(Map<String, Object> sourceParams, Map<String, Object> funcParams, Map<String, Object> taskParams) {
		super(sourceParams, funcParams, taskParams);
	}
	
	public String getSourceTemplate() {
		int nabor = Integer.valueOf((String) sourceParams.get("nabor"));
		int maxDigit = Integer.valueOf((String) sourceParams.get("maxDigit"));
		boolean isNumber = (Boolean)sourceParams.get("isNumber");
		
		String source = " <sourceSet> \n <set type=\"DecartSet\"> \n";
		for (int i = 0; i < nabor; i ++) {
			if (isNumber && i == 0) {
				source +=  "<set type=\"NumericSet\" first=\"1\" last=\"" + maxDigit + "\"/>";			        
			} else {
				source +=  "<set type=\"NumericSet\" first=\"0\" last=\"" + maxDigit + "\"/>";
			}
		}
        source += "	</set>\n" +
        "</sourceSet>";
		
    	source = replace(source, getBaseSourceParameters());
    	return source;
	}

	protected Map<String, Object> getBaseSourceParameters() {
    	return Collections.emptyMap();
	}
	
	public String generateDescription() {
		int nabor = Integer.valueOf((String) sourceParams.get("nabor"));
		int maxDigit = Integer.valueOf((String) sourceParams.get("maxDigit"));
		boolean isNumber = (Boolean)sourceParams.get("isNumber");
		if (isNumber) {
			return "Найдите количество всех " + nabor + "-значных  чисел, состоящих из цифр от 0 до " + maxDigit + 
			 (isEmptyInline() ? "." : ", для которых верно следующее: " + ((String)taskParams.get("inlineDesc")).toLowerCase() + ".");
		} else {
			return "Найдите количество всех наборов чисел, состоящих из " + nabor + " цифр от 0 до " + maxDigit + 
			(isEmptyInline() ? "." : ", для которых верно следующее: " + ((String)taskParams.get("inlineDesc")).toLowerCase() + ".");
		}
    }
	
}

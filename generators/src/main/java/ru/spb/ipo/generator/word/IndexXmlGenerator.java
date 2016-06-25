package ru.spb.ipo.generator.word;

import java.util.Map;

class IndexXmlGenerator extends WordXmlGenerator {

	IndexXmlGenerator(Map<String, Object> sourceParams, Map<String, Object> funcParams, Map<String, Object> taskParams) {
		super(sourceParams, funcParams, taskParams);
	}
	
    public String getVerifier(Map funcParams) {
		return "<verifier type=\"IndexVerifier\">\n" +
				"<function type=\"And\">\n" +
				"<constElement>" + 1 + "</constElement>\n" +
				funcParams.get("function") + "\n" +
				"</function>\n" +
				funcParams.get("indexingElement") + "\n" +
				"</verifier>";
    }
    
    public String generateDescription() {
		return "Рассмотрим множество всех " + sourceParams.get("nabor") + "-буквенных слов в алфавите А = " + taskParams.get("alphabit") + " (буквы в словах" + (isMulti() ? " " : " не ") +  "могут повторяться)" +
				(isEmptyInline() ? "" : ", удовлетворяющих следующим условиям : " + ((String)taskParams.get("inlineDesc")).toLowerCase()) + ". " +
				"Найдите в этом множестве порядковый номер слова \""  + funcParams.get("indexingElementText") + "\".";
    }
}

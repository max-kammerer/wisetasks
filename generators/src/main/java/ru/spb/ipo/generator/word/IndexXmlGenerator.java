package ru.spb.ipo.generator.word;

import java.util.Map;

public class IndexXmlGenerator extends WordXmlGenerator {

	public IndexXmlGenerator(Map sourceParams, Map funcParams, Map taskParams) {
		super(sourceParams, funcParams, taskParams);
	}
	
    public String getVerifier(Map funcParams) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("<verifier type=\"IndexVerifier\">\n");
    	sb.append("<function type=\"And\">\n");
    	sb.append("<constElement>" + 1 + "</constElement>\n");    	
        
    	sb.append(funcParams.get("function") + "\n");        
		
        sb.append("</function>\n");
        

        sb.append(funcParams.get("indexingElement") + "\n");

        sb.append("</verifier>");          
       return sb.toString();
    }
    
    public String generateDescription() {
		return "Рассмотрим множество всех " + sourceParams.get("nabor") + "-буквенных слов в алфавите А = " + taskParams.get("alphabit") + " (буквы в словах" + (isMulti() ? " " : " не ") +  "могут повторяться)" +
				(isEmptyInline() ? "" : ", удовлетворяющих следующим условиям : " + ((String)taskParams.get("inlineDesc")).toLowerCase()) + ". " +
				"Найдите в этом множестве порядковый номер слова \""  + funcParams.get("indexingElementText") + "\".";
    }
}

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
		return "–ассмотрим множество всех " + sourceParams.get("nabor") + "-буквенных слов в алфавите ј = " + taskParams.get("alphabit") + " (буквы в словах" + (isMulti() ? " " : " не ") +  "могут повтор€тьс€)" +
				(isEmptyInline() ? "" : ", удовлетвор€ющих следующим услови€м : " + ((String)taskParams.get("inlineDesc")).toLowerCase()) + ". " +
				"Ќайдите в этом множестве пор€дковый номер слова \""  + funcParams.get("indexingElementText") + "\".";
    }
}

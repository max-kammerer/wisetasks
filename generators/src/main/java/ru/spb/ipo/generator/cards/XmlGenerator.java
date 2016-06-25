//package ru.spb.ipo.generator.cards;
//
///*
// * XmlGenerator.java
// *
// * Created on May 30, 2007
// * @author michael.bogdanov
// 
// */
//
//
///**
// *
// * @author michael.bogdanov
// */
//public class XmlGenerator {
//    
//    /** Creates a new instance of XmlGenerator */
//    public XmlGenerator() {
//        
//    }
//    
//    public String getDescription(String functionList, String numbers) {
//    	String genParam =    "<description-params>\n" +
//        "	<param name=\"length\">\n" +
//        " 		<value>${numbers}</value>\n" +
//        " 	</param>\n" + 
//        "</description-params>";
//    	genParam = genParam.replace("${numbers}", "" + numbers);
//        return "<description>\n<![CDATA[" + functionList + "\n]]></description>\n" + genParam;
//    }
//    
//    public String getSource(int coloda, int numbers) {
//        coloda = coloda / 4;
//        
//        String source = " <sourceSet> \n <set type=\"CombinationSet\" length=\"${length}\"> \n" +
//                "	<set type=\"DecartSet\">\n" +
//                "		<set type=\"NumericSet\" first=\"1\" last=\"${coloda}\"/>\n" +
//                "		<set type=\"NumericSet\" first=\"1\" last=\"4\"/>\n" +
//                " 		</set>\n" +
//                "	</set>\n" +
//                "</sourceSet>";
//         
//        source = source.replace("${numbers}", "" + numbers).replace("${coloda}", "" + coloda);        
//        return source;
//    }
//    
//    public String getVerifiesr(String func) {
//    	StringBuffer sb = new StringBuffer();
//    	sb.append("<verifier type=\"CountVerifier\">\n");
//    	sb.append("<function type=\"And\">\n");
//    	sb.append("<constElement>" + 1 + "</constElement>\n");
//    	
//        sb.append(func + "\n");
//        
//		sb.append("</function>\n");
//        
//        sb.append("</verifier>");          
//       return sb.toString();
//    }
//    
//    public static String generateDescription(String coloda, String number, String functionList) {
//        return "»з колоды в " + coloda + " карт вытаскивают случайным образом " + number + " карт. ѕодсчитайте количество наборов в которых: " + functionList + ".";
//                
//    }    
//    
//    public String generateXml(String coloda, String number, String functionList, String func, String title) {
//        StringBuffer str = new StringBuffer("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n");
//        str.append("<task title=\"" + title + "\">\n");
//        str.append(getDescription(functionList, number)+ "\n");
//        str.append(" <mathDescription>\n");
//        str.append(getSource(Integer.valueOf(coloda), Integer.valueOf(number)) + "\n");
//        str.append(getVerifiesr(func));
//        
//        str.append(" </mathDescription>\n");
//        str.append(" </task>");
//        return str.toString();                
//    }
//    
//}

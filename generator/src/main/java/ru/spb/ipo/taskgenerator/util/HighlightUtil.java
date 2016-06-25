package ru.spb.ipo.taskgenerator.util;

import org.w3c.dom.Document;
import ru.spb.ipo.taskgenerator.xml.Writer;

import java.io.StringWriter;

public class HighlightUtil {

	public static String highlight(Document doc) {
		StringWriter writer = new StringWriter();		
		try {
			Writer.getString(doc);
			//doc.write(writer);		
//			StringReader reader = new StringReader(writer.getBuffer().toString());
//			InputSource source = new InputSource(reader);
//			DOMResult res = new DOMResult();
//			writer = new StringWriter();		
//			HighlightEngine engine = new HighlightEngine();
//			engine.highlight(source, res);
//			((XmlDocument)res.getNode()).write(writer);
			//System.out.println(s writer.getBuffer().toString());
			
			return ""; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
        
        // Build the XMLHighlighter object and register an HighlightListener
//        XMLHighlighter highlighter = new XMLHighlighter();
//        HighlightListenerImpl listener = new HighlightListenerImpl();
//        highlighter.addHighlightListener(listener);
//        Pattern patterns [] = new Pattern[] {Pattern.compile()} 
//        // Find highlights and print the results
//        highlighter.findMatches(doc, patterns);
//        XMLHighlighterTools.printDocument(document, System.out);
	}
	
}


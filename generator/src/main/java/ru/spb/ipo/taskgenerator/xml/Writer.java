package ru.spb.ipo.taskgenerator.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import ru.spb.ipo.taskgenerator.model.*;
import ru.spb.ipo.taskgenerator.util.ElementUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

public class Writer {

    private static DocumentBuilder parser;

    private static Document doc = null;
	
	public static Document generateXmlTree(KernelElement root) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			doc = builder.newDocument();
//			doc = new XmlDocument();
			buildXmlTree(root, doc);
			return doc;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getString(Document doc) {
		ByteArrayOutputStream writer = new ByteArrayOutputStream();
		//StringWriter writer = new StringWriter(); 
		try {
			transform(doc, new OutputStreamWriter(writer, "windows-1251"));
			//doc.write(writer/*,"windows-1251"*/);
			return writer.toString("windows-1251");
			//return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
		
		//return writer.getBuffer().toString();
	}
	
	public static void save(FileWriter writer, Document doc) {			
		try {
			transform(doc, writer);
//			doc.write(writer/*,"windows-1251"*/);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void transform(Document doc, java.io.Writer out) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "windows-1251");
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, "text");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(out));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
    private static Node buildXmlTree(KernelElement element, Node parentNode) {
        Node newNode = null;        
        if (element instanceof Element) {        	
        	Element el = (Element)element;
        	newNode = doc.createElement(el.getName());
        	Iterator it = el.getAttributes().iterator();
        	while(it.hasNext()) {
        		Attribute attr = (Attribute)it.next();
        		((org.w3c.dom.Element)newNode).setAttribute(attr.getName(), attr.getValue());
        	}
        	String text = ElementUtil.getSafe(el.getText());
        	if (!"".equals(text)){        		
        		newNode.appendChild(doc.createTextNode(text));
        	}
        	if (element instanceof Parameter) {        		
				Parameter parameter = (Parameter)element;
				List list = parameter.getValues();
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					KeyValue vd = (KeyValue)iter.next(); 
					String name = vd.getKey();
					String description = vd.getValue();
					org.w3c.dom.Element value = doc.createElement("value");
					value.appendChild(doc.createTextNode(name));					
					newNode.appendChild(value);
					description = ElementUtil.getSafe(description);
					if (!"".equals(description)) {
						value.setAttribute("text", description);
					}
				}
        	}
        } else if (element instanceof Comment) {
        	newNode = doc.createComment(((ru.spb.ipo.taskgenerator.model.Comment)element).getText());
        }
        
        
        Iterator ch = element.getChildren().iterator();
        while(ch.hasNext()){
        	KernelElement child = (KernelElement)ch.next();
        	buildXmlTree(child, newNode);
        }
        parentNode.appendChild(newNode);
        return newNode;
    }
    
    

}

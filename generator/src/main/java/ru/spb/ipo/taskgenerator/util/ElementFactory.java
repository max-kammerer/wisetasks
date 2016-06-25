package ru.spb.ipo.taskgenerator.util;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import ru.spb.ipo.taskgenerator.config.Config;
import ru.spb.ipo.taskgenerator.model.Comment;
import ru.spb.ipo.taskgenerator.model.Element;
import ru.spb.ipo.taskgenerator.model.KernelElement;
import ru.spb.ipo.taskgenerator.model.Parameter;

public class ElementFactory {

	public static KernelElement buildElement(Node node) {
		short type = node.getNodeType();
        if ((type == Node.TEXT_NODE || type==Node.CDATA_SECTION_NODE)) {
        	String text = node.getNodeValue();
        	text = ElementUtil.getSafe(text);
        	if (!"".equals(text)) {
        		return  new Comment(text);
        	}   
        } else if(type == Node.COMMENT_NODE) {
        	String text = node.getNodeValue();
        	text = ElementUtil.getSafe(text);
        	if (!"".equals(text)) {
        		return  new Comment(text);
        	}
		} else {
			KernelElement element = null;
			if (isParameter(node)){
				element = new Parameter();
			} else {
				element = new Element(node.getNodeName()); 
			}			 
			NamedNodeMap nnm = node.getAttributes();
	        if (nnm != null)
	            for (int i = 0; i < nnm.getLength(); i++){
	                Node n= nnm.item(i);
	                String nname = n.getNodeName();
	                String nvalue = n.getNodeValue();                
	                ((Element)element).addAttribute(nname, nvalue);                
	            }
			return element;
        }
        return null;
	}
	
	public static KernelElement buildElement(int type, String kind) {
		String name = "";		
		if (type == ElementUtil.E_FUNCTION) {
			name = Config.TYPE_FUNCTION;			
		} else if (type == ElementUtil.E_SET) {
			name = Config.TYPE_SET;
		} else if (type == ElementUtil.E_ADDITION) {
			name = kind;
		} else if (type == ElementUtil.E_PARAMETER) {
			name = Config.TYPE_PARAM;
		} else {
			name = ElementUtil.getNameByType(type);
		}
		
		if (type == ElementUtil.E_PARAMETER) {
			return new Parameter();
		}
		if (ElementUtil.isConst(kind)) {
			return new Element(kind);
		}
		Element element = new Element(name);
		if (type == ElementUtil.E_FUNCTION || type == ElementUtil.E_SET) {
			element.addAttribute("type", kind);
		}
		return element;
	}
	
	private static boolean isParameter(Node node){
		return isParameter(node.getNodeName());
	}
	
	private static boolean isParameter(String name){
		return Config.TYPE_PARAM.equals(name);
	}

}

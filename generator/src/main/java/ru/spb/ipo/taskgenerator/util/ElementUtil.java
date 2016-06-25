package ru.spb.ipo.taskgenerator.util;

import ru.spb.ipo.taskgenerator.config.Config;
import ru.spb.ipo.taskgenerator.model.Attribute;
import ru.spb.ipo.taskgenerator.model.Element;
import ru.spb.ipo.taskgenerator.model.KernelElement;

public class ElementUtil {
	
	public static final int ELEMENT = 1;
	public static final int ATTRIBUTE = 2;
	public static final int COMMENT = 3;
	
	public static final int E_SET = 1;
	public static final int E_FUNCTION = 2;	
	public static final int E_TASK = 3;
	public static final int E_DESCRIPTION = 4;
	public static final int E_PARAMETER = 6;
	public static final int E_DESC_PARAMETERS = 7;
	public static final int E_VER_PARAMETERS = 8;
	public static final int E_SOURCE_SET = 9;	
	public static final int E_UNKNOWN = 10;
	public static final int E_ADDITION = 11;
	
	
	public static String getSafe(String str) {
		if (str == null) return "";
		return str.trim();
	}

	public static String getPresentableText(String elementText){
		return getSafe(elementText);
	}
	
//	public static int getType(KernelElement element) {
//		
//	}
	
	public static String getElementName(KernelElement element) {		
		if (element == null) {
			return null;
		}
		if (element instanceof Element) {
			return ((Element)element).getName();
		} 
		return null;
	}
	
	
	public static int getElementType(Element element) {
		//System.out.println(element.getName());
		String type = "";
		ru.spb.ipo.taskgenerator.config.Element configElement = Config.getInstance().getElementByName(element.getName()); 
		if (configElement != null) {
			type = configElement.getType();
		} 
		if (isConst(element) || type.equals(Config.TYPE_FUNCTION)) {
			return E_FUNCTION;
		} 		
		if (type.equals(Config.TYPE_SET)) {
			return E_SET;
		}
		if (type.equals(Config.TYPE_PARAM)) {
			return E_PARAMETER;
		}
		if (type.equals(Config.TYPE_ROOTELEMENT)) {
			return E_TASK;
		}
		if (type.equals(Config.TYPE_DESCRIPTION)) {
			return E_DESCRIPTION;
		}
		return E_UNKNOWN;		
	}
	
	public static boolean isConst(Element element) {
		return isConst(element.getName());
	}
	
	public static boolean isConst(String name) {
		if ("constElement".equals(name) || 
				"current-set-element".equals(name)) {
			return true;
		}			
		return false;
	}

	public boolean isElement(KernelElement element) {
		return element instanceof Element;
	}
	
	public static String getElementKind(Element element) {
		if (isConst(element)) {
			return element.getName();
		}
		Attribute attr = element.getAttribute("type");
		if (attr != null) {
			return attr.getValue();
		}
		return "";		
	}
	
	public static String getNameByType(int type) {
		if (E_VER_PARAMETERS == type) {
			return Config.TYPE_VERPARAMSET;
		}
		if (E_DESC_PARAMETERS == type) {
			return Config.TYPE_DESCPARAMSET;
		}
		return null; 
	}

}

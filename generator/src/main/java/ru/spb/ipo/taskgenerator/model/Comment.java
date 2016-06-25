package ru.spb.ipo.taskgenerator.model;

import java.util.Collections;
import java.util.List;

import ru.spb.ipo.taskgenerator.util.HtmlUtil;

public class Comment extends KernelElementImpl {

	private String myText; 
	
	public Comment(String text) {
		myText = text;
	}
	
	public void addChild(KernelElement child) {
		throw new UnsupportedOperationException();
	}

	public List getChildren() {		
		return Collections.EMPTY_LIST;
	}

	public String getPresentableString() {		
		//return "<!--" + myText + "-->";
		return HtmlUtil.getHtmlString(myText, "gray");
	}

	public String getText() {
		return myText;
	}	
	
}

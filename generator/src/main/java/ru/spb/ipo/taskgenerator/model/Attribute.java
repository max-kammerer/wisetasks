package ru.spb.ipo.taskgenerator.model;

import java.util.Collections;
import java.util.List;

import ru.spb.ipo.taskgenerator.util.HtmlUtil;

public class Attribute extends KernelElementImpl{

	private String myName;
	private String myValue;
	private static final String EQUAL = "="; 
	
	public Attribute() {
		
	}
	
	public Attribute(String name, String value) {
		myName = name;
		myValue = value;
	}
	
	public void addChild(KernelElement child) {
		throw new UnsupportedOperationException();
	}

	public List getChildren() {		
		return Collections.EMPTY_LIST;
	}

	public String getPresentableString() {		
		return HtmlUtil.colorize(this);// myName + " " + EQUAL +" " + myValue;
	}

	public String getName() {
		return myName;
	}

	public void setName(String myName) {
		this.myName = myName;
	}

	public String getValue() {
		return myValue;
	}

	public void setValue(String myValue) {
		this.myValue = myValue;
	}

}

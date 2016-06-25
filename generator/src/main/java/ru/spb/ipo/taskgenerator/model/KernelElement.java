package ru.spb.ipo.taskgenerator.model;

import java.util.List;

public interface KernelElement {

	String getPresentableString();
	
	void addChild(KernelElement child);
	
	List getChildren();
	
	KernelElement getParent();
	
	void removeChild(KernelElement child);
}

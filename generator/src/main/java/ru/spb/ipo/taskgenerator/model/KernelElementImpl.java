package ru.spb.ipo.taskgenerator.model;

import java.util.ArrayList;
import java.util.List;

public abstract class KernelElementImpl implements KernelElement {
	
	public ArrayList myChildren;
	public KernelElement myParent;
	
	public KernelElementImpl() {
		myChildren = new ArrayList();
	}

	public void addChild(KernelElement child) {
		if (child == null) return;
		((KernelElementImpl)child).setParent(this);
		myChildren.add(child);
	}

	public List getChildren() {
		return myChildren;
	}

	public KernelElement getParent() {
		return myParent;
	}

	public void setParent(KernelElement parent) {
		this.myParent = parent;
	}

	public void removeChild(KernelElement child) {
		getChildren().remove(child);		
	}
}

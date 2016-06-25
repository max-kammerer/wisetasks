package ru.spb.ipo.taskgenerator.model;

import java.util.ArrayList;
import java.util.List;

abstract class KernelElementImpl implements KernelElement {
	
	private ArrayList<KernelElement> myChildren;
	private KernelElement myParent;
	
	KernelElementImpl() {
		myChildren = new ArrayList<KernelElement>();
	}

	public void addChild(KernelElement child) {
		if (child == null) return;
		((KernelElementImpl)child).setParent(this);
		myChildren.add(child);
	}

	public List<KernelElement> getChildren() {
		return myChildren;
	}

	public KernelElement getParent() {
		return myParent;
	}

	private void setParent(KernelElement parent) {
		this.myParent = parent;
	}

	public void removeChild(KernelElement child) {
		getChildren().remove(child);		
	}
}

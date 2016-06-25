package ru.spb.ipo.taskgenerator.util;

import java.util.Iterator;
import java.util.List;

import ru.spb.ipo.taskgenerator.model.KernelElement;

public abstract class Visitor {
	
	public void visit(KernelElement parent) {
		processElement(parent);
		List children = parent.getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			KernelElement child = (KernelElement) iter.next();
			visit(child);
		}
	}
	
	public abstract void processElement(KernelElement element);
}

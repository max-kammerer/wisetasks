package ru.spb.ipo.taskgenerator.util;

import java.util.Iterator;
import java.util.List;

import ru.spb.ipo.taskgenerator.model.KernelElement;

public abstract class Visitor {
	
	public void visit(KernelElement parent) {
		processElement(parent);
		List children = parent.getChildren();
		for (Object aChildren : children) {
			KernelElement child = (KernelElement) aChildren;
			visit(child);
		}
	}
	
	public abstract void processElement(KernelElement element);
}

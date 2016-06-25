package ru.spb.ipo.taskgenerator.ui;

import javax.swing.tree.DefaultTreeModel;

import ru.spb.ipo.taskgenerator.model.Element;

public class TaskTreeModel extends DefaultTreeModel {
	
	public TaskTreeModel() {
		super(new TaskTreeNode(new Element("root")));
	}

}

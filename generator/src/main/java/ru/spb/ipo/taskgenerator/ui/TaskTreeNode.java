package ru.spb.ipo.taskgenerator.ui;

import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

import ru.spb.ipo.taskgenerator.model.KernelElement;

public class TaskTreeNode extends DefaultMutableTreeNode {
	
	private KernelElement myElement;
	private JTextArea myTextArea;
		
	public TaskTreeNode(KernelElement element) {
		super(element.getPresentableString());
		myElement = element;		
	}
	
	public TaskTreeNode(String text) {	
		setUserObject(getTextArea(text));
	}
	

	public JTextArea getTextArea() {
		return myTextArea;
	}

	private JTextArea getTextArea(String text) {
		if (myTextArea == null) {
			myTextArea = new JTextArea();
			myTextArea.setEditable(true);
		}
		myTextArea.setText(text);		
		return myTextArea;		
	}
	
	public void update(){
		setUserObject(getUserObject());
	}
	
	public KernelElement getElement() {
		return myElement;
	}

	public Object getUserObject() {		
		if (myElement != null) {
			return myElement.getPresentableString();
		} 
		return getTextArea();
	}
	
	public TaskTreeNode getTextChild() {
		int childNumber = getChildCount();
		for (int i = 0; i < childNumber; i++) {
			TaskTreeNode child = (TaskTreeNode)getChildAt(i);
			if (child.isText()) {
				return child;
			}
		}
		TaskTreeNode newText = new TaskTreeNode("temporary");
		//insert(newText, 0);
		insert(newText, getChildCount());
		return newText;
	}
	
	public boolean isText() {
		return myElement == null;
	}

	public void setTextArea(JTextArea textArea) {
		this.myTextArea = textArea;
	}
	
//	public boolean isParameter() {
//		return !isText() && myElement instanceof Parameter;
//	}
//	
//	public TaskTreeNode getParameterChild() {
//		
//	}

}

package ru.spb.ipo.taskgenerator.util;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import ru.spb.ipo.taskgenerator.model.Element;
import ru.spb.ipo.taskgenerator.ui.TaskGenerator;
import ru.spb.ipo.taskgenerator.ui.TaskTreeNode;

public class TaskTreeRenderer extends DefaultTreeCellRenderer{
	
	public TaskTreeRenderer(){
		setClosedIcon(TaskGenerator.ELEMENT_ICON);
		setLeafIcon(TaskGenerator.ELEMENT_ICON);
		setOpenIcon(TaskGenerator.ELEMENT_ICON);
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		TaskTreeNode node = (TaskTreeNode) value;
		if (node.getElement() == null) {
			if (sel) {								
				node.getTextArea().setBackground(super.getBackgroundSelectionColor());
				node.getTextArea().setForeground(super.getTextSelectionColor());
				node.getTextArea().selectAll();
			} else {				
				
				node.getTextArea().setBackground(super.getBackgroundNonSelectionColor());
				node.getTextArea().setForeground(super.getTextNonSelectionColor());
				node.getTextArea().select(0,0);
			}
			return node.getTextArea();			
		}
		if (ElementUtil.getElementType((Element)node.getElement()) == ElementUtil.E_FUNCTION) {
			setClosedIcon(TaskGenerator.FUNCTION_ICON);
			setLeafIcon(TaskGenerator.FUNCTION_ICON);
			setOpenIcon(TaskGenerator.FUNCTION_ICON);
		} else if (ElementUtil.getElementType((Element)node.getElement()) == ElementUtil.E_SET) {
			setClosedIcon(TaskGenerator.SET_ICON);
			setLeafIcon(TaskGenerator.SET_ICON);
			setOpenIcon(TaskGenerator.SET_ICON);
		} else {
			setClosedIcon(TaskGenerator.ELEMENT_ICON);
			setLeafIcon(TaskGenerator.ELEMENT_ICON);
			setOpenIcon(TaskGenerator.ELEMENT_ICON);
		}
		return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
//				return getTreeCellRendererComponent2(tree, value, sel, expanded, leaf,
//						row, hasFocus);
	}
	
    public Component getTreeCellRendererComponent2(JTree tree, Object value,
			  boolean sel,
			  boolean expanded,
			  boolean leaf, int row,
			  boolean hasFocus) {
    	String         stringValue = tree.convertValueToText(value, sel,
			  expanded, leaf, row, hasFocus);
	
		
		this.hasFocus = hasFocus;
		setText(stringValue);
		if(sel)
		setForeground(getTextSelectionColor());
		else
		setForeground(getTextNonSelectionColor());
		// There needs to be a way to specify disabled icons.
		if (!tree.isEnabled()) {
			setEnabled(false);			
			if (leaf) {
			setDisabledIcon(getLeafIcon());
			} else if (expanded) {
			setDisabledIcon(getOpenIcon());
			} else {
			setDisabledIcon(getClosedIcon());
			}
		} else {
			setEnabled(true);
			if (leaf) {
			setIcon( TaskGenerator.ELEMENT_ICON);
			} else if (expanded) {
			setIcon(getOpenIcon());
			} else {
			setIcon(getClosedIcon());
			}
		}
		setComponentOrientation(tree.getComponentOrientation());		
		selected = sel;	
		return this;
    }	
	
}

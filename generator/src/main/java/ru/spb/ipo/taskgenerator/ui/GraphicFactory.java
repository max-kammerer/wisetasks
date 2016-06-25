package ru.spb.ipo.taskgenerator.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import ru.spb.ipo.taskgenerator.model.Element;
import ru.spb.ipo.taskgenerator.model.KernelElement;
import ru.spb.ipo.taskgenerator.model.Parameter;
import ru.spb.ipo.taskgenerator.model.KeyValue;
import ru.spb.ipo.taskgenerator.util.ElementUtil;

public class GraphicFactory {
	
	public static HashMap elements = new HashMap();
	
	public static void addChild(KernelElement parent, KernelElement element){
		insertChild(parent, element);		
		parent.addChild(element);		
	}
	
	public static void insertChild(KernelElement parent, KernelElement element){
		TaskTreeNode parentNode = (TaskTreeNode)elements.get(parent);
		TaskTreeNode childNode = getView(element);
		elements.put(element, childNode);		
		//parentNode.add(childNode);
		((DefaultTreeModel)TaskGenerator.getFrame().getModelTree().getModel()).insertNodeInto(childNode, parentNode, parentNode.getChildCount());
		//TODO check
		JTree tree = TaskGenerator.getFrame().getModelTree();
		tree.setSelectionPath(new TreePath(childNode.getPath()));
		int row = tree.getRowForPath(new TreePath(childNode.getPath()));
		tree.setSelectionRow(row);
		
	}
	
	public static TaskTreeNode getNewRoot(KernelElement root) {
		TaskTreeNode rootNode = new TaskTreeNode(root);
		elements.put(root, rootNode);
		return rootNode;
	}
	 

	public static TreeModel buildModel(KernelElement root) {
		TaskTreeNode treeRoot = getNewRoot(root);
		recursiveBuild(root);		
		return new DefaultTreeModel (treeRoot); 
	}
	
	private static void recursiveBuild(KernelElement parent) {
		List children = parent.getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			KernelElement child = (KernelElement) iter.next();
			insertChild(parent, child);
			recursiveBuild(child);
		}
	}
	
	public static void updateFrom(KernelElement element) {
		
	}
	
	public static void refresh(KernelElement element) {
		TaskTreeNode node = (TaskTreeNode)elements.get(element);
		node.update();
		if (element instanceof Element) {
			Element e = (Element)element;
			TaskTreeNode textNode = node.getTextChild();
			if (!"".equals(ElementUtil.getSafe(e.getText()))) {
				textNode.setTextArea(new JTextArea(e.getText()));
				textNode.update();
			} else {
				node.remove(textNode);
				node.update();
			}
			
			if (element instanceof Parameter) {
				StringBuffer text = new StringBuffer();
				Parameter parameter = (Parameter)element;
				List map = parameter.getValues();
				for (Iterator iter = map.iterator(); iter.hasNext();) {
					KeyValue vd = (KeyValue)iter.next();
					String value = vd.getKey();
					String description = vd.getValue();
					text.append(value);
					if (!"".equals(ElementUtil.getSafe(description))) {
						text.append("  - [" + description + "]");
					}
					text.append("\n");
				}
				String result = "";
				if (text.length() > 0) {
					result = text.substring(0, text.length() - "\n".length());
				} else {
					result = text.toString();
				}
				if (text.length() != 0){
					node.getTextChild().setTextArea(new JTextArea(result));
					node.getTextChild().update();
				}
			}
		}
		((DefaultTreeModel)TaskGenerator.getFrame().getModelTree().getModel()).reload(node);
		//TaskGenerator.getFrame().getModelTree().update(TaskGenerator.getFrame().getModelTree().getGraphics());
		//TaskGenerator.getFrame().getModelTree().repaint();//update(TaskGenerator.getFrame().getModelTree().getGraphics());
		
	}
	
	private static TaskTreeNode getView(KernelElement element) {
		TaskTreeNode newNode = new TaskTreeNode(element);		
		if (element instanceof Element) {
			String text = null;
			
			if (element instanceof Parameter) {
				StringBuffer text1 = new StringBuffer();
				Parameter parameter = (Parameter)element;
				List list = parameter.getValues();
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					KeyValue vd = (KeyValue)iter.next();
					String value = vd.getKey();
					String description = vd.getValue();
					text1.append(value);
					if (!"".equals(ElementUtil.getSafe(description))) {
						text1.append("  - [" + description + "]");
					}
					text1.append("\n");
				}
				String result = "";
				if (text1.length() > 0) {
					result = text1.substring(0, text1.length() - "\n".length());
				} else {
					result = text1.toString();
				}
				text = result;
			} else {
				text = ((Element)element).getText();
			}
			text = ElementUtil.getSafe(text);
			if (!"".equals(text) ) {
				newNode.add(new TaskTreeNode(ElementUtil.getPresentableText(text)));
			}
		}
		return newNode;
	}
	
	public static void unLink(KernelElement element) {
		elements.remove(element);
	}
	
	public static TaskTreeNode getLinkedElement(KernelElement element){
		return (TaskTreeNode)elements.get(element);
	}
}

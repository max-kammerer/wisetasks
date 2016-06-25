package ru.spb.ipo.taskgenerator.util;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;

import org.w3c.dom.Document;

import ru.spb.ipo.taskgenerator.config.Config;
import ru.spb.ipo.taskgenerator.model.Attribute;
import ru.spb.ipo.taskgenerator.model.Comment;
import ru.spb.ipo.taskgenerator.model.Element;
import ru.spb.ipo.taskgenerator.model.KernelElement;
import ru.spb.ipo.taskgenerator.model.Parameter;
import ru.spb.ipo.taskgenerator.model.KeyValue;
import ru.spb.ipo.taskgenerator.ui.AttributeTableModel;
import ru.spb.ipo.taskgenerator.ui.ContextMenu;
import ru.spb.ipo.taskgenerator.ui.GraphicFactory;
import ru.spb.ipo.taskgenerator.ui.ParametersTableModel;
import ru.spb.ipo.taskgenerator.ui.SmartPanel;
import ru.spb.ipo.taskgenerator.ui.TaskGenerator;
import ru.spb.ipo.taskgenerator.ui.TaskTreeNode;
import ru.spb.ipo.taskgenerator.xml.Reader;
import ru.spb.ipo.taskgenerator.xml.Writer;


public class Actions {
    
	
	public static void load(File file) {
		String fileName = file.getAbsolutePath();
		changeModel(fileName);
		updateXml();
		getGenerator().setTitleWithFile(file.getName());
		SmartPanel.smartPanel.updateTabs(SmartPanel.DISABLE, null, null, null, null);
		expand();
	}
	
	public static void save(FileWriter writer) {
		TaskTreeNode node = (TaskTreeNode)TaskGenerator.getFrame().getModelTree().getModel().getRoot();
		Document doc = Writer.generateXmlTree(node.getElement());
		Writer.save(writer, doc);		
	}
	
	public static void save2file(File file) throws IOException {		
		//java.io.Writer writer = new OutputStreamWriter(new FileOutputStream(file), "windows-1251");		
		FileWriter writer = new FileWriter(file);
		save(writer);
		getGenerator().setTitleWithFile(file.getName());
	}
	 
	public static int getActions(Element element) {
		ru.spb.ipo.taskgenerator.config.Element rootElement = Config.getInstance().getRootModelElement(element.getName());
		if (rootElement != null) {
			return rootElement.getIntOperations();
		}
		return Config.NO_OPERATIONS;
	}
	
	//use load
	public static void changeModel(String fileName) {
		TaskGenerator.getFrame().getModelTree().setModel(read(fileName));		
	}
	
	private static TreeModel read(String fileName) {
		Reader reader = new Reader(fileName);
		KernelElement rootElement = reader.getRoot();
		return GraphicFactory.buildModel(rootElement);
	}


	public static void elementSelected(TaskTreeNode node) {
		KernelElement element = node.getElement();
		
		int type = SmartPanel.DISABLE;
		String [][] attributes = null;
		String [][] parameters = null;
		String text = null;
		
		if (element == null ) {
			node = (TaskTreeNode)node.getParent();
			element = node.getElement();
			type = SmartPanel.DESCRIPTION;			
		} else if (element instanceof Comment) {
			type = SmartPanel.DISABLE;
		}
		if (element != null) {
			if (type == SmartPanel.DISABLE) { 
				type = SmartPanel.ELEMENT;
			}
			text = ((Element)element).getText();
			attributes = getTableValues((Element)element, -1);
		} else {
			type = SmartPanel.DISABLE;
		}
		
		if (isDescription((Element)element)) {
			type = SmartPanel.DESCRIPTION;
		}
		if (isParameter((Element)element)){
			parameters = getTableValues((Element)element, SmartPanel.PARAMETER);
			type = SmartPanel.PARAMETER;
		}
		
		SmartPanel.smartPanel.updateTabs(type, node, attributes, text, parameters);
		SmartPanel.smartPanel.getApply().setEnabled(false);
	}
	
	public static void smartModelChanged() {		
		SmartPanel.smartPanel.getApply().setEnabled(true);
	}
	
	
	
	public static String [][] getTableValues(Element element, int type) {
		List attributes = new ArrayList();
		AttributeTableModel model = (AttributeTableModel)SmartPanel.table.getModel();		
		
		if (element == null) {
			return null;			
		} 
		//attributes.clear();
		if (type == SmartPanel.PARAMETER) {
			insertParameters((Parameter)element, attributes);
		} else {
			Collection temp = element.getAttributes();
			for (Iterator iter = temp.iterator(); iter.hasNext();) {
				Attribute attribute = (Attribute) iter.next();
				attributes.add(new KeyValue(attribute.getName(), attribute.getValue()));
			}		
			ru.spb.ipo.taskgenerator.config.Element rootElement = Config.getInstance().getRootModelElement(element.getName());
			if (rootElement != null) {
				Collection attributesFromConfig = rootElement.getAttributes();
				for (Iterator iter = attributesFromConfig.iterator(); iter.hasNext();) {
					KeyValue attribute = (KeyValue) iter.next();
					if (!attributes.contains(attribute)) {
						attributes.add(new KeyValue(attribute.getKey(), null));
					}
				}
			}
			insertExtendedAttributes(element, attributes);
		}
		
		String [][] keys = new String[attributes.size()][2];		
		int index = 0;
		for (Iterator iterator = attributes.iterator(); iterator.hasNext();) {
			KeyValue kv = (KeyValue)iterator.next();
			String name = kv.getKey();
			String value = kv.getValue();			
			if ("type".equals(name) && index != 0) {
				String tempName = keys[0][0]; 
				String tempValue = keys[0][1];
				keys[0][0] = name;
				keys[0][1] = value;
				name = tempName;
				value = tempValue;
			}
			keys[index][0] = name;
			keys[index][1] = value;
			index++;
		}				
		return keys;		
	}
	
	private static void insertExtendedAttributes(Element element, List attributes) {
		if (ElementUtil.getElementType(element) == ElementUtil.E_FUNCTION || ElementUtil.getElementType(element) == ElementUtil.E_SET) {
			ru.spb.ipo.taskgenerator.config.Element rootElement = Config.getInstance().getElementByName(ElementUtil.getElementKind(element));
			if (rootElement != null) {
				Collection attributesFromConfig = rootElement.getAttributes();
				for (Iterator iter = attributesFromConfig.iterator(); iter.hasNext();) {
					KeyValue attribute = (KeyValue) iter.next();
					if (!attributes.contains(attribute)) {
						attributes.add(new KeyValue(attribute.getKey(), null));
					}
				}
			}
		}
	}
	
	private static void insertParameters(Parameter element, List attributes) {		
		Collection attributesFromConfig = element.getValues();
		attributes.addAll(attributesFromConfig);
//		for (Iterator iter = attributesFromConfig.iterator(); iter.hasNext();) {
//			KeyValue vd = (KeyValue)iter.next();			
//			String value = vd.getKey();
//			String description = vd.getValue();
//			attributes.put(value, description);
//		}
	}
	
    public static void showContextMenu(MouseEvent e){
    	TaskTreeNode node = (TaskTreeNode)TaskGenerator.getFrame().getModelTree().getLastSelectedPathComponent();
    	if (node == null) return;        
    	((ContextMenu)TaskGenerator.getFrame().getContextMenu()).show(node.getElement(), e.getComponent(), e.getX(), e.getY());
    }
	
	 
	public static String EMPTY_STRING = "";
	
	public static void setAttributes() {
		SmartPanel.smartPanel.getApply().setEnabled(false);
		SmartPanel.smartPanel.getCurrentNode();
		JTree tree = TaskGenerator.getFrame().getModelTree();
		TaskTreeNode node = SmartPanel.smartPanel.getCurrentNode();
		if (node != null) {
			Element element = (Element)node.getElement();
						
			
			AttributeTableModel model = (AttributeTableModel)SmartPanel.table.getModel();
			int rows = SmartPanel.table.getModel().getRowCount();
			element.removeAttributes();
			for (int i =0; i < rows; i++) {
				String name = (String) model.getValueAt(i,AttributeTableModel.NAME_COLUMN);
				String value = (String) model.getValueAt(i,AttributeTableModel.VALUE_COLUMN); 
				name = ElementUtil.getSafe(name);
				value = ElementUtil.getSafe(value);
				if (!"".equals(name) && !"".equals(value)) {
					element.addAttribute(name, value);
				}
			}		
			
			setText(element);
			
			if (isParameter(element)) {
				model = (AttributeTableModel)SmartPanel.smartPanel.getParametersTableModel();			
				rows = SmartPanel.table.getModel().getRowCount();
				((Parameter)element).clear();
				for (int i =0; i < rows; i++) {
					String value = (String) model.getValueAt(i, ParametersTableModel.VALUE_COLUMN2);
					String description = (String) model.getValueAt(i, ParametersTableModel.DESCRIPTION_COLUMN); 
					value = ElementUtil.getSafe(value);
					description = ElementUtil.getSafe(description);
					if (!"".equals(value)) {
						((Parameter)element).addValue(value, description);
					}
				}		
			}
			GraphicFactory.refresh(element);
			updateXml();
		}
	}	
	
	public static void setText(Element element) {
		String text = SmartPanel.smartPanel.getTextArea().getText();
		element.setText(text);
		GraphicFactory.refresh(element);
	}
	
	public static void updateXml() {
		int pos = TaskGenerator.getFrame().getXmlEditor().getCaretPosition();
		KernelElement root = ((TaskTreeNode)TaskGenerator.getFrame().getModelTree().getModel().getRoot()).getElement();
		Document doc = Writer.generateXmlTree(root);
		String str = Writer.getString(doc);		
		TaskGenerator.getFrame().getXmlEditor().setText(str);
		TaskGenerator.getFrame().getXmlEditor().setCaretPosition(pos);	
	}
	
	public static void insert(int type, String kind) {
		TaskTreeNode node = (TaskTreeNode)TaskGenerator.getFrame().getModelTree().getLastSelectedPathComponent();
		KernelElement parent = node.getElement();
		KernelElement child = ElementFactory.buildElement(type, kind);		
		GraphicFactory.addChild(parent, child);
		GraphicFactory.refresh(parent);
		//GraphicFactory.refresh(child);
		updateXml();
	}
	
	public static void delete() {
		TaskTreeNode node = (TaskTreeNode)TaskGenerator.getFrame().getModelTree().getLastSelectedPathComponent();
		KernelElement element = node.getElement();
		Visitor visitor = new Visitor() {
			public void processElement(KernelElement element) {
				GraphicFactory.unLink(element);				
			}
		};
		visitor.visit(element);
		element.getParent().removeChild(element);
		((DefaultTreeModel)TaskGenerator.getFrame().getModelTree().getModel()).removeNodeFromParent(node);
		updateXml();
		SmartPanel.smartPanel.updateTabs(SmartPanel.DISABLE, null, null, null, null);
	}
	
	public static void exit() {
		//System.exit(0);
		TaskGenerator.getFrame().dispose();
	}
	
	public static boolean isDescription(Element element) {
		return Config.TYPE_DESCRIPTION.equals(ElementUtil.getElementName(element));
	}
	
	public static boolean isParameter(Element element) {
		//return element instanceof Parameter;
		return Config.TYPE_PARAM.equals(ElementUtil.getElementName(element));
	}
	
	public static boolean isParameterExists(boolean isDescription) {
		DefaultTreeModel model = (DefaultTreeModel)TaskGenerator.getFrame().getModelTree().getModel();		
		TaskTreeNode node = (TaskTreeNode)model.getRoot();
		Element root = (Element)node.getElement();
		List children = root.getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {			
			KernelElement element = (KernelElement) iter.next();
			String sample = null;
			if (isDescription) {
				sample = Config.TYPE_DESCPARAMSET;
			} else {
				sample = Config.TYPE_VERPARAMSET;
			}
			if (sample.equals(ElementUtil.getElementName(element))) {
				return true;
			}
		}
		return false;
	}
	
	public static void addParameterSet(boolean isDescription) {
		TaskGenerator.getFrame().getModelTree().setSelectionRow(0);
		if (isDescription && !isParameterExists(isDescription)) {			
			insert(ElementUtil.E_DESC_PARAMETERS, null);
		} else if (!isDescription && !isParameterExists(isDescription)) {			
			insert(ElementUtil.E_VER_PARAMETERS, null);
		}
	}
	
	public static void expand() {
		TaskTreeNode root = getRoot();		
		expand(root);		
	}
	
	private static void expand(MutableTreeNode node) {
		if (node == null) return;
		int row = 0;
		JTree tree = TaskGenerator.getFrame().getModelTree();
		while (row < tree.getRowCount()) {
			TaskGenerator.getFrame().getModelTree().expandRow(row);			
			row++;
		}
	}
	
	private static TaskTreeNode getRoot() {
		return (TaskTreeNode)TaskGenerator.getFrame().getModelTree().getModel().getRoot();
	}
	
	private static DefaultTreeModel getModel() {
		return (DefaultTreeModel)TaskGenerator.getFrame().getModelTree().getModel();
	}
	
	private static TaskGenerator getGenerator() {
		return TaskGenerator.getFrame();
	}
}

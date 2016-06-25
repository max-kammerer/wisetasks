package ru.spb.ipo.taskgenerator.xml;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ru.spb.ipo.taskgenerator.model.Element;
import ru.spb.ipo.taskgenerator.model.KernelElement;
import ru.spb.ipo.taskgenerator.util.ElementFactory;
import ru.spb.ipo.taskgenerator.util.ElementUtil;

public class Reader {

    DocumentBuilder parser;
    KernelElement rootElement;
    Node rootNode;
    ArrayList al = new ArrayList();

    public Reader(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {            
            parser = factory.newDocumentBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadfile(new File(fileName));
    }

//    private boolean isFunction(String name) {
//        if (SimpleNode.TYPE_FUNCTION.equals(name) || "current-set-element".equals(name) || "constElement".equals(name))
//            return true;
//        return false;
//    }

//    private boolean isSet(String name) {
//        if (SimpleNode.TYPE_SET.equals(name))
//            return true;
//        return false;
//    }

//    private boolean isSubElement(Node node) {
//        Element e = null;
//        if (isSF(node.getNodeName()) || isParameter(node.getNodeName())) {
//            return false;
//        } else {
//            e = TaskGenerator.config.getElementByName(node.getNodeName());
//        }
//        if (e != null) return false;
//        return true;
//    }
    
//    private boolean isSF(String name) {
//        return isSet(name) || isFunction(name);
//    }

//    private boolean isParameter(String name) {
//        return "param".equals(name);
//    }

    private String getText(Node n) {
        return n.getChildNodes().item(0).getNodeValue();
    }

    private KernelElement buildTree(Node node) {
    	KernelElement newElement = null;        
        newElement = ElementFactory.buildElement(node);        
        NodeList nl = node.getChildNodes();        
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            short type = n.getNodeType();
            if ((type == Node.TEXT_NODE || type==Node.CDATA_SECTION_NODE)) {
            	String text = n.getNodeValue(); 
            	text = ElementUtil.getSafe(text);
            	if (!"".equals(text)) {
            		((Element)newElement).setText(text);
            	}
            } else {
            	newElement.addChild(buildTree(n));
            }
            
        }
        return newElement;
    }

//    private Element getElement(String name) {
//        return TaskGenerator.config.getElementByName(name);
//    }

    private boolean isEmpty(Node node) {
        if (node == null || "".equals(node.getNodeValue().trim())) return true;
        return false;
    }

    public KernelElement getRoot() {
        return rootElement;
    }

//    public void register(Viewer v) {
//        al.add(v);
//    }

//    public void update() {
//        for (int i = 0; i < al.size(); i++) {
//            ((Viewer)al.get(i)).updateUI();
//        }
//    }

//    public void add(SimpleNode parent, SimpleNode child) {
//        parent.add(child);
//        update();
//    }

//    public void remove(SimpleNode node) {
//        node.remove();
//        update();
//    }

//    public void  addAttribute(SimpleNode node, String name, String value) {
//        node.addAttribute(name, value);
//        update();
//    }

    private void loadfile(File file) {
        try {
        	rootNode = parser.parse(file).getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        rootElement = buildTree(rootNode);
    }

}

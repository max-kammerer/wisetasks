/*
 * This file is part of Wisetasks
 *
 * Copyright (C) 2006-2008, 2012  Michael Bogdanov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.spb.ipo.engine.utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import ru.spb.ipo.engine.exception.NodeNotExistsException;
import ru.spb.ipo.engine.exception.ParameterNotSetException;
import ru.spb.ipo.engine.exception.XmlException;


import java.util.ArrayList;
import java.util.Collections;

public class Utils {

    public static Node getChild(Node parent, String childName) throws XmlException {
        Node res = getChildIfExists(parent, childName);
        if (res == null) {
        	//throw new NodeNotExistsException("Node '" + childName + "' doesn't exist in " + getNodeDesc(parent));
        	throw new NodeNotExistsException("Не могу найти ребенка '" + childName + "' для вершины " + getNodePath(parent) + "!");
        }
        return res;
    }
    
    public static Node getChildIfExists(Node parent, String childName) {
        NodeList nl = parent.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            if (childName.equals(nl.item(i).getNodeName()))
                return nl.item(i);
        }
        return null;
    }

    public static Node [] getNodes(Node parent, String nodeName) {
        NodeList nl = parent.getChildNodes();
        ArrayList<Node> al =new ArrayList();
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeName().equals(nodeName)) al.add(nl.item(i));
        }
        return al.toArray(new Node [al.size()]);
    }

    public static Node [] getAFList(Node parent) {
            NodeList nl = parent.getChildNodes();
            ArrayList<Node> al =new ArrayList();
            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getNodeName().equals("element") || nl.item(i).getNodeName().equals("constElement") || nl.item(i).getNodeName().equals("function") || (nl.item(i).getNodeName().equals("current-set-element"))) al.add(nl.item(i));
            }
            return (Node []) al.toArray();
        }

    public static Node getAttribute(Node node, String name) throws XmlException {
        Node res = getAttributeIfExists(node, name);
        if (res == null) {
        	throw new ParameterNotSetException("Атрибут '" + name + "' для вершины " + getNodePath(node) + " не выставлен!");
        }
        return res;
    }
    
    public static Node getAttributeIfExists(Node node, String name) {
        NamedNodeMap nnm = node.getAttributes();
        return nnm.getNamedItem(name);
    }

    public static Integer getInteger(String value) {
        if (value != null) {
            return new Integer(value);
        }
        return null;
    }

    public static String getText(Node node) {
        Node text = getChildIfExists(node,"#cdata-section");
        if (text != null)  return text.getNodeValue();
        text = getChildIfExists(node,"#text");
        if (text != null)  return text.getNodeValue();
        return "";
    }

    private static String getNodePath(Node node) {
    	return getNodePath(node, node.getNodeName());
    }
    
    private static String getNodePath(Node node, String postfix) {
    	if (node.getParentNode() != null) {
    		return getNodePath(node.getParentNode(), node.getParentNode().getNodeName() + "/" + postfix);
    	} else {
    		return postfix;
    	}    	
    }
}

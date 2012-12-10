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

package ru.spb.ipo.engine.task;

import org.w3c.dom.*;
import ru.spb.ipo.engine.exception.NodeNotExistsException;
import ru.spb.ipo.engine.exception.ParameterNotSetException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mike
 * Date: 09.12.12
 * Time: 12:54
 */
public class NodeImpl implements Node {

    private org.w3c.dom.Node node;

    public NodeImpl(org.w3c.dom.Node node) {
        this.node = node;
    }


    public Node makeCopy() {
        return new NodeImpl(node.cloneNode(true));
    }

    public String getNodeName() {
        return node.getNodeName();
    }

    public List<Node> getChilds(String name) {
        NodeList nl = node.getChildNodes();
        ArrayList<Node> childs = new ArrayList();

        for (int i = 0; i < nl.getLength(); i++) {
            if (name == null || nl.item(i).getNodeName().equals(name)) {
                childs.add(new NodeImpl(nl.item(i)));
            }
        }
        return childs;
    }

    public String getAttr(String name) throws ParameterNotSetException {
        String value =  getAttrIfExists(name, null);
        if (value == null) {
            throw new ParameterNotSetException("Атрибут '" + name + "' для вершины " + getNodePath() + " не выставлен!");
        }
        return value;
    }

    public String getAttrIfExists(String name, String defaultValue) {
        NamedNodeMap nnm = node.getAttributes();
        org.w3c.dom.Node node = nnm.getNamedItem(name);
        if (node != null) {
            return node.getNodeValue();
        } else {
            return defaultValue;
        }
    }

    public String getText() {
        NodeImpl textNode = getChildIfExists("#cdata-section");
        if (textNode != null)  {
            return textNode.node.getNodeValue();
        }

        textNode = getChildIfExists("#text");
        if (textNode != null) {
            return textNode.node.getNodeValue();
        }

        return node.getNodeValue();
    }

    private String getNodePath() {
        return getNodePath(node, node.getNodeName());
    }

    private String getNodePath(org.w3c.dom.Node node, String postfix) {
        if (node.getParentNode() != null) {
            return getNodePath(node.getParentNode(), node.getParentNode().getNodeName() + "/" + postfix);
        } else {
            return postfix;
        }
    }

    public NodeImpl getChildIfExists(String name) {
        NodeList nl = node.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            if (name.equals(nl.item(i).getNodeName()))
                return new NodeImpl(nl.item(i));
        }
        return null;
    }

    public NodeImpl getChild(String name) throws NodeNotExistsException {
        NodeImpl node = getChildIfExists(name);
        if (node == null) {
            throw new NodeNotExistsException("Child node with name " + name + " not fount at " + getNodePath());
        }
        return node;
    }

    public Map<String, Node> getAttrs() {
        HashMap<String, Node> result = new HashMap<String, Node>();
        NamedNodeMap nnm = node.getAttributes();
        if (nnm != null) {
            for (int i = 0; i< nnm.getLength(); i++) {
                org.w3c.dom.Node attr = nnm.item(i);
                result.put(attr.getNodeName(), new NodeImpl(attr));
            }
        }
        return result;
    }


    public void update(String newNodeValue) {
        if (newNodeValue != null) {
            node.setNodeValue(newNodeValue);
        }
    }

    public void addToParent(Node newChild) {
        node.getParentNode().appendChild(((NodeImpl)newChild).node);
    }

    public void removeFromParent(Node oldChild) {
        node.getParentNode().removeChild(((NodeImpl) oldChild).node);
    }

    public boolean isEmptyWrapper() {
        return node == null;
    }

    public List<Node> getFunctionList() {
        NodeList nl = node.getChildNodes();
        ArrayList<Node> functions =new ArrayList();
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeName().equals("element") || nl.item(i).getNodeName().equals("constElement") || nl.item(i).getNodeName().equals("function") || (nl.item(i).getNodeName().equals("current-set-element"))) {
                functions.add(new NodeImpl(nl.item(i)));
            }
        }
        return functions;
    }
}

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

import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.utils.Parser;

import java.util.*;

public class Preprocessor {

    private static java.util.Set hs = new TreeSet(Arrays.asList(new String [] {"for"}));

    public static Node expandMacros(Node node, Map parameters) throws XmlException {
        return generateTask(node.makeCopy(), parameters, true);
    }

    public static Node generateTask(Node node, Map parameters) throws XmlException {
        return generateTask(node.makeCopy(), parameters, false);
    }

    //node already cloned
    private static Node generateTask(Node node, Map parameters, boolean expandMacros) throws XmlException {
        if (node == null) {
            return node;
        }

        Map<String, String> attrs = node.getAttrs();

        String text = node.getText();
        if (text != null) {
            String newText = parse(text, parameters);
            if (false == newText.equals(text)) {
                node.update(node.getText());
            }
        }


        if (attrs != null) {
            for (java.util.Iterator<Map.Entry<String, String>>  iterator = attrs.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, String>  attr = iterator.next();
                String prevValue = attr.getValue();
                String newValue = parse(prevValue, parameters);
                if (prevValue != newValue) {
                    node.updateAttr(attr.getKey(), newValue);
                }
            }

        }

        if (expandMacros && hs.contains(node.getNodeName())) {
            expandMacros(node, parameters, expandMacros);
        } else {
            List<Node> childNodes = node.getChilds(null);

            if (childNodes != null) {
                for(Node child: childNodes) {
                    Node newChild = parse(child, parameters);
                    generateTask(newChild, parameters, expandMacros);
                }
            }
        }

        return node;
    }

    private static Node parse(Node node, Map parameters) {
        if (node.getText() != null) {
            node.update(parse(node.getText(), parameters));
        }
        return node;
    }

    private static void parseAttr(Node attr, Map parameters) {
        attr.update(parse(attr.getText(), parameters));
    }

    private static String parse(String input, Map parameters) {
        Iterator it = parameters.keySet().iterator();
        while (it.hasNext()) {
        	String key = (String) it.next();
        	input = input.replaceAll("[$][{]" + key + "[}]", parameters.get(key).toString());
        }
        return input;
    }

    public static void expandMacros(Node node, Map parameters, boolean forExecution) throws XmlException {
        String forIndexName = node.getAttr("name");

        Object oldValue = parameters.get(forIndexName);

        Parser parser = new Parser();

        int first = parser.parse(node.getAttr("first")).getBigInteger().intValue();
        int last = parser.parse(node.getAttr("last")).getBigInteger().intValue();
        int inc = parser.parse(node.getAttr( "inc")).getBigInteger().intValue();

        if (inc == 0) {
            inc = 1;
        }

        for (int i = first; i <= last; i += inc) {
            parameters.put(forIndexName, new Integer(i));
            List<Node> childs = node.getChilds(null);

            for (Node child: childs) {
                Node temp = generateTask(child.makeCopy(), parameters, forExecution);
                node.addToParent(temp);
            }

        }
        if  (oldValue != null) {
            parameters.put(forIndexName,oldValue);
        } else  {
            parameters.remove(forIndexName);
        }

        node.removeFromParent(node);
    }

    public static String insertParameters2Answer(String answer, Map parameters) {
        Iterator it = parameters.keySet().iterator();
        StringBuffer sb = new StringBuffer(answer);
        while(it.hasNext()) {
            String name = (String)it.next();
            String value = (String)parameters.get(name);
            int i = sb.indexOf(name);
            while(i >= 0) {
                boolean replace = true;
                if (i > 0) {
                    if (Parser.isLetter(sb, i - 1)) replace = false;
                }
                if (i < sb.length()) {
                    if (Parser.isLetter(sb, i + name.length())) replace = false;
                }
                if (replace) sb.replace(i, i + name.length(), value);
                i = sb.indexOf(name, i + name.length());
            }
        }
        return sb.toString();
    }

}
